package com.jbohorquez.microservices_users.infrastructure.output.jpa.entity;

import com.jbohorquez.microservices_users.domain.validation.Adult;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

@Entity
@Table(name = USERS)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = NAME_REQUIRED)
    @Column(length = NAME_MAX_LENGTH, nullable = false)
    private String name;

    @NotBlank(message = LAST_NAME_REQUIRED)
    @Column(length = MAX_LENGTH_SIXTY, nullable = false)
    private String lastName;

    @NotNull(message = ID_DOCUMENT_REQUIRED)
    @Digits(integer = MAX_DOCUMENT, fraction = ZERO, message = ID_DOCUMENT_NUMERIC)
    @Column(nullable = false)
    private Long identityDocument;

    @NotBlank(message = PHONE_REQUIRED)
    @Pattern(regexp = PHONE_NUMBER, message = PHONE_INVALID)
    @Column(length = THREE, nullable = false)
    private String phone;

    @NotNull(message = BIRTHDATE_REQUIRED)
    @Adult(message = USER_MUST_BE_ADULT)
    @Column(nullable = false)
    private LocalDate birthdate;

    @NotBlank(message = EMAIL_REQUIRED)
    @Email(message = EMAIL_INVALID_FORMAT)
    @Column(length =MAX_LENGTH_SIXTY, nullable = false, unique = true)
    private String email;

    @NotBlank(message = PASSWORD_REQUIRED)
    private String password;

    @ManyToOne
    @JoinColumn(name = ROL_ID_LIST, nullable = false)
    private RolEntity rol;

    public UserEntity(long l, String john, String doe, String mail, String password, String number) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(ROLE + rol.getName()));
    }

    @Override
    public String getUsername() {
        return getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
