package io.hattrick.backend.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.hattrick.backend.component.user.UserJwtTokenGenerator;
import io.hattrick.backend.entity.UserEntity;
import io.hattrick.backend.exception.UserAlreadyExistsException;
import io.hattrick.backend.exception.UserNotExistsException;
import io.hattrick.backend.exception.UserPasswordIncorrectException;
import io.hattrick.backend.mapper.UserMapper;
import io.hattrick.backend.payload.request.LoginUserRequest;
import io.hattrick.backend.payload.request.RegisterUserRequest;
import io.hattrick.backend.payload.response.LoginUserResponse;
import io.hattrick.backend.payload.response.RegisterUserResponse;
import io.hattrick.backend.repository.UserRepository;
import io.hattrick.backend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserJwtTokenGenerator userJwtTokenGenerator;

	public UserServiceImpl(UserMapper userMapper, UserRepository userRepository,
		PasswordEncoder passwordEncoder,
		UserJwtTokenGenerator userJwtTokenGenerator
	) {
		this.userMapper = userMapper;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.userJwtTokenGenerator = userJwtTokenGenerator;
	}

	@Override
	public RegisterUserResponse register(RegisterUserRequest request) {
		if (this.userRepository.existsByEmail(request.email())) {
			throw new UserAlreadyExistsException(request.email());
		}
		String password = this.passwordEncoder.encode(request.password());
		UserEntity userEntity = this.userMapper.map(request, password);
		this.userRepository.save(userEntity);
		String token = this.userJwtTokenGenerator.generateToken(userEntity);
		return this.userMapper.mapRegisterUserResponse(userEntity, token);
	}

	@Override
	public LoginUserResponse login(LoginUserRequest request) {
		Optional<UserEntity> optionalUserEntity = this.userRepository.findByEmail(request.email());
		UserEntity userEntity = optionalUserEntity.orElseThrow(() -> new UserNotExistsException(request.email()));
		if (!this.passwordEncoder.matches(request.password(), userEntity.getPassword())) {
			throw new UserPasswordIncorrectException(request.email());
		}
		String token = this.userJwtTokenGenerator.generateToken(userEntity);
		return this.userMapper.mapLoginUserResponse(userEntity, token);
	}


	@Override
	public UserEntity getUserById(String id) {
		return this.userRepository.findById(id).orElse(null);

	}

}
