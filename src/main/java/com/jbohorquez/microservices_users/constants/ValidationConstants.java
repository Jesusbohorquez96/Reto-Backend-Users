package com.jbohorquez.microservices_users.constants;

import java.util.List;

public class ValidationConstants {

    public static final long TIME = 1000 * 60 * 60 * 24;

    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int DECIMALS = 2;
    public static final int SEVEN = 7;
    public static final int INTEGERS = 10;
    public static final int THREE = 13;
    public static final int MAYOR = 18;
    public static final int MAX_DOCUMENT = 20;
    public static final int NAME_MAX_LENGTH = 50;
    public static final int MAX_LENGTH_SIXTY = 60;
    public static final int EMAIL_MAX_LENGTH = 50;
    public static final int DESCRIPTION_MAX_LENGTH = 90;
    public static final int MAX_LENGTH = 120;

    public static final String AT = "@";
    public static final String SPOT = ".";

    public static final String ID = "id";
    public static final String ROL = "rol";
    public static final String TO_ROL = "toRol";
    public static final String ROLES = "roles";
    public static final String TAG_ROLES = "Roles";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";

    public static final String NUMBERS = "^\\d+$";
    public static final String PHONE_NUMBER = "^\\+[0-9]{1,13}$";
    public static final String PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";
    public static final String NAME = "name";
    public static final String PRIVATE = "294A404E635266556A586E327235753878214125442A472D4B6150645367566B";

    public static final String MESSAGE = "message";
    public static final String STATUS = "status";
    public static final String ERRORS = "errors";
    public static final String FIELD = "field";
    public static final String REJECTED_VALUE = "rejectedValue";
    public static final String NULL = "null";
    public static final String VALIDATION_FAILED = "Validation failed for one or more fields";

    public static final String SPRING = "spring";
    public static final String NAME_REQUIRED = "Name is required";
    public static final String LAST_NAME_REQUIRED = "Last name is required";
    public static final String PASSWORD_REQUIRED = "Password is required";
    public static final String PASSWORD_INVALID = "Password must be at least 8 characters long, and must include at least one number, one uppercase letter, one lowercase letter, and one special character";
    public static final String EMAIL_REQUIRED = "Email is required";
    public static final String EMAIL_INVALID_FORMAT = "Email must have a valid format required: ";
    public static final List<String> MAIL_VALIDATE = List.of("pragma", "gmail", "hotmail", "yahoo");
    public static final String ID_DOCUMENT_REQUIRED = "Identity document is required";
    public static final String ID_DOCUMENT_NUMERIC = "Identity document must be numeric and cannot contain decimals";
    public static final String PHONE_REQUIRED = "Phone number is required";
    public static final String PHONE_INVALID = "Phone number must be a maximum of 13 characters and may include the '+' symbol";
    public static final String BIRTHDATE_REQUIRED = "Birthdate is required";
    public static final String BIRTHDATE_PAST = "Birthdate must be in the past";
    public static final String ROL_REQUIRED = "Rol is required";
    public static final String NAME_LONG = "Name is too long";
    public static final String DESCRIPTION_LONG = "Description is too long";
    public static final String DESCRIPTION_REQUIRED = "Description cannot be blank";
    public static final String DESCRIPTION_MAX_LENGTH_EXCEEDED = "The description must not exceed 120 characters";
    public static final String USER_MUST_BE_ADULT = "User must be at least 18 years old";
    public static final String USER_NOT_FOUND = "User must be an adult";
    public static final String USER_NOT_FOUND_MESSAGE = "User not found";
    public static final String EMPLOYEE_ID_REQUIRED = "Employee id is required";

    public static final String USER_ID_TARGET = "userId";
    public static final String USER_NAME_TARGET = "userName";
    public static final String USER_LAST_NAME_TARGET = "userLastName";
    public static final String USER_IDENTITY_DOCUMENT_TARGET = "userIdentityDocument";
    public static final String USER_PHONE_TARGET = "userPhone";
    public static final String USER_BIRTHDATE_TARGET = "userBirthdate";
    public static final String USER_EMAIL_TARGET = "userEmail";
    public static final String USER_PASSWORD_TARGET = "userPassword";
    public static final String USER_ROL_TARGET = "userRol";
    public static final String LAST_NAME = "lastName";
    public static final String IDENTITY_DOCUMENT= "identityDocument";
    public static final String PHONE = "phone";
    public static final String BIRTHDATE = "birthdate";
    public static final String EMAIL = "email";
    public static final String PASSWORD_SOURCE= "password";
    public static final String DESCRIPTION = "description";
    public static final String ROL_ID_TARGET = "rolId";
    public static final String ROL_ID_LIST = "rol_id";
    public static final String USERS = "users";
    public static final String TAG_USERS = "Users";
    public static final String ROLE =  "ROLE_";
    public static final String ROL_NAME_TARGET = "rolName";
    public static final String ROL_DESCRIPTION_TARGET = "rolDescription";

    public static final String TITLE = "Reto Backend pragma:";
    public static final String TERMS_OF_SERVICE_URL = "http://swagger.io/terms/";
    public static final String LICENSE_NAME = "Apache 2.0";
    public static final String LICENSE_URL = "http://springdoc.org";

    public static final String APP_DESCRIPTION = "${appdescription}";
    public static final String APP_VERSION = "${appversion}";
    public static final String INVALID_JWT = "Invalid or malformed JWT token";
    public static final String INTERNAL_SERVER = "Internal server error occurred";
    public static final String OCCURRED_UNEXPECTED = "An unexpected error occurred";
    public static final String EMAIL_VALID_FORMAT = "Email must be a valid format (e.g., user@example.com)";
    public static final String API_USERS = "API for user management";
    public static final String API_ROLES = "API for rol management";

    public static final String V3_API = "/v3/api-docs/**";
    public static final String SWAGGER_UI = "/swagger-ui.html";
    public static final String SWAGGER_UI_RESOURCES = "/swagger-ui/**";
    public static final String AUTH = "/auth/**";
    public static final String ALL_API = "/api/**";

    public static final String ROOT = "/";
    public static final String ROLES_API = "/roles";
    public static final String LOGIN = "/login";
    public static final String AUTH_API = "/auth";
    public static final String VALIDATE_OWNER = "/validate-owner/{ownerId}";

    public static final String USERS_API = "/users";
    public static final String REGISTER_ADMIN = "/register_admin";
    public static final String REGISTER_CUSTOMER = "/register_customer";
    public static final String REGISTER_OWNER = "/register_owner";
    public static final String REGISTER_EMPLOYEE = "/register_employee";
    public static final String USER_ID = "/{userId}";
    public static final String ROL_ID = "/{rolId}";

    public static final String MICROSERVICE_PLAZOLETA = "plazoleta-service";
    public static final String HTTP_RESTAURANTS = "http://localhost:8086/restaurants";
    public static final String EMPLOYEE_ID = "/{employeeId}";
    public static final String EMPLOYEES = "employees";
    public static final String RESTAURANT_ID = "restaurant_id";

    //"userEntity"
    public static final String USER_ENTITY = "userEntity";
    public static final String USER_ENTITY_ID = "userEntity.id";

    public static final String ID_USER = "user_id";

    public static final String ROL_ADMIN = "hasAnyRole('admin')";
    public static final String ROL_OWNER = "hasAnyRole('propietario')";

    public static final Long ADMIN = 1L;
    public static final Long OWNER = 2L;
    public static final Long EMPLOYEE = 3L;
    public static final Long CUSTOMER = 4L;

    public static final String JSON = "application/json";
    public static final String ERROR_JWT = "{ \"error\": \"Access denied: Invalid or malformed JWT token\" }";

    private ValidationConstants() {
        throw new IllegalStateException("Utility class");
    }
}
