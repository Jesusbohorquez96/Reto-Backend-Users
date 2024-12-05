package com.jbohorquez.microservices_users.domain.usecase;

import com.jbohorquez.microservices_users.application.dto.AuthenticationRequest;
import com.jbohorquez.microservices_users.application.dto.AuthenticationResponse;
import com.jbohorquez.microservices_users.application.dto.RegisterRequest;
import com.jbohorquez.microservices_users.constants.ValidationConstants;
import com.jbohorquez.microservices_users.domain.api.IUserServicePort;
import com.jbohorquez.microservices_users.domain.model.User;
import com.jbohorquez.microservices_users.domain.spi.UserPersistencePort;
import com.jbohorquez.microservices_users.infrastructure.adapters.securityconfig.IAuthenticationService;

import java.util.List;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

public abstract class UserUseCase implements IUserServicePort {

    private final UserPersistencePort userPersistencePort;
    private final IAuthenticationService authenticationService;

    public UserUseCase(UserPersistencePort userPersistencePort, IAuthenticationService authenticationService) {
        this.userPersistencePort = userPersistencePort;
        this.authenticationService = authenticationService;

    }

    @Override
    public List<User> getAllUser() {
        return userPersistencePort.getAllUser();
    }

    @Override
    public void deleteUser(Long userId) {
        userPersistencePort.deleteUser(userId);
    }

    @Override
    public AuthenticationResponse validateUser(AuthenticationRequest authenticationRequest) {
        return authenticationService.authenticate(authenticationRequest);
    }

    @Override
    public void registerUser(RegisterRequest registerRequest) {

        validateMandatoryFields(registerRequest);
        validateEmailStructure(registerRequest.getEmail());
        validatePhoneNumber(registerRequest.getPhone());
        validateIdDocument(registerRequest.getIdDocument());

        authenticationService.register(registerRequest);
    }

    private void validateMandatoryFields(RegisterRequest registerRequest) {
        validateRequiredField(registerRequest.getName(), NAME_REQUIRED);
        validateRequiredField(registerRequest.getLastName(), LAST_NAME_REQUIRED);
        validateRequiredField(registerRequest.getPassword(), PASSWORD_REQUIRED);
        validateRequiredField(registerRequest.getEmail(), EMAIL_REQUIRED);
        validateRequiredField(registerRequest.getPhone(), PHONE_REQUIRED);
        validateRequiredField(registerRequest.getBirthdate(), BIRTHDATE_REQUIRED);
        validateRequiredField(registerRequest.getIdDocument(), ID_DOCUMENT_REQUIRED);
    }

    private void validateRequiredField(Object field, String errorMessage) {
        if (field == null || (field instanceof String && ((String) field).isBlank())) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private void validateEmailStructure(String email) {
        List<String> allowedDomains = ValidationConstants.MAIL_VALIDATE;
        if (!email.contains(AT) || !email.contains(SPOT)) {
            throw new IllegalArgumentException("Email must be a valid format (e.g., user@example.com)");
        }
        String domain = email.substring(email.indexOf(AT) + ONE, email.lastIndexOf(SPOT));
        if (!allowedDomains.contains(domain)) {
            throw new IllegalArgumentException(EMAIL_INVALID_FORMAT + allowedDomains);
        }
    }

    private void validatePhoneNumber(String phone) {
        if (!phone.matches(PHONE_NUMBER)) {
            throw new IllegalArgumentException(PHONE_INVALID);
        }
    }

    private void validateIdDocument(Long idDocument) {
        String idDocumentString = idDocument.toString();
        if (!idDocumentString.matches(NUMBERS)) {
            throw new IllegalArgumentException(ID_DOCUMENT_NUMERIC);
        }
    }
}
