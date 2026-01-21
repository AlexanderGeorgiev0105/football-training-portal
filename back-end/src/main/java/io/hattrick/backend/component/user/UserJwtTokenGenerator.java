package io.hattrick.backend.component.user;

import io.hattrick.backend.component.jwt.JwtGenerator;
import io.hattrick.backend.entity.UserEntity;
import java.util.HashMap;
import org.springframework.stereotype.Component;

@Component
public class UserJwtTokenGenerator {
	private final JwtGenerator jwtGenerator;

	public UserJwtTokenGenerator(JwtGenerator jwtGenerator) {
		this.jwtGenerator = jwtGenerator;
	}

	public String generateToken(UserEntity userEntity) {
		HashMap<String, Object> claims = new HashMap<>();
		claims.put("email", userEntity.getEmail());
		claims.put("username", userEntity.getUsername());
		claims.put("role", "USER");
		return this.jwtGenerator.generateToken(userEntity.getId(), claims);
	}

	public String getUserIdFromToken(String token) {
		return this.jwtGenerator.verifyToken(token).getSubject();
	}
}
