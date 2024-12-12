package com.jbohorquez.microservices_users.infrastructure.output.jpa.mapper;

import com.jbohorquez.microservices_users.domain.model.Employee;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.entity.EmployeeEntity;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EmployeeEntityMapper {

    @Mapping(source = USER_ID_TARGET, target = USER_ENTITY)
    EmployeeEntity toEntity(Employee employee);

    @Mapping(source = USER_ENTITY_ID, target = USER_ID_TARGET)
    Employee toDomain(EmployeeEntity employeeEntity);



    default UserEntity mapUserIdToUserEntity(Long userId) {
        if (userId == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        return userEntity;
    }

}
