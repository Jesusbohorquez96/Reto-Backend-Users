package com.jbohorquez.microservices_users.infrastructure.output.jpa.entity;

import lombok.*;
import javax.persistence.*;
import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

@Entity
@Table(name = EMPLOYEES)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = ID_USER, nullable = false)
    private UserEntity userEntity;

    @Column(name = RESTAURANT_ID, nullable = false)
    private Long restaurantId;
}
