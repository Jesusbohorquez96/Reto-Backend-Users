package com.jbohorquez.microservices_users.infrastructure.output.jpa.adapter;

import com.jbohorquez.microservices_users.application.dto.OwnerResponse;
import com.jbohorquez.microservices_users.domain.model.User;
import com.jbohorquez.microservices_users.domain.spi.UserPersistencePort;
import com.jbohorquez.microservices_users.infrastructure.exception.NoDataFoundException;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.entity.UserEntity;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class UserJpaAdapter implements UserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public List<User> getAllUser() {
        List<UserEntity> userEntityList = userRepository.findAll();
        if (userEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return userEntityMapper.toUserList(userEntityList);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public OwnerResponse findOwnerById(Long ownerId) {
        return userRepository.findByIdAndRolId(ownerId, 2L)
                .map(userEntityMapper::toOwnerResponse)
                .orElse(null);
    }
}
