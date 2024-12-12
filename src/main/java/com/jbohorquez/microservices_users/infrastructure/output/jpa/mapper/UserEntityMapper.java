package com.jbohorquez.microservices_users.infrastructure.output.jpa.mapper;

import com.jbohorquez.microservices_users.application.dto.OwnerResponse;
import com.jbohorquez.microservices_users.domain.model.User;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

    User toUser(UserEntity userEntity);

    List<User> toUserList(List<UserEntity> userEntityList);

    OwnerResponse toOwnerResponse(UserEntity userEntity);
}
