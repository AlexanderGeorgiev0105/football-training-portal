package io.hattrick.backend.mapper;

import io.hattrick.backend.entity.UserEntity;
import io.hattrick.backend.payload.request.RegisterUserRequest;
import io.hattrick.backend.payload.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
	@Mapping(target = "password", source = "password")
	UserEntity map(RegisterUserRequest request, String password);
	RegisterUserResponse mapRegisterUserResponse(UserEntity entity, String token);
	LoginUserResponse mapLoginUserResponse(UserEntity entity, String token);
}
