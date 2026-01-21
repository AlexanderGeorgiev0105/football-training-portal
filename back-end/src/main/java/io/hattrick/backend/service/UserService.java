package io.hattrick.backend.service;

import io.hattrick.backend.entity.UserEntity;
import io.hattrick.backend.payload.request.*;
import io.hattrick.backend.payload.response.*;

public interface UserService {
	RegisterUserResponse register(RegisterUserRequest request);

	LoginUserResponse login(LoginUserRequest request);

	UserEntity getUserById(String id);
}
