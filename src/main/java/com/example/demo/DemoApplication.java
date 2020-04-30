package com.example.demo;

import java.util.UUID;
import com.example.demo.repository.user.UserEntity;
import com.example.demo.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var username = "username";
		var password = "password";
		
		var userEntity = new UserEntity();
		userEntity.setId(UUID.randomUUID());
		userEntity.setUsername(username);
		userEntity.setPassword(new BCryptPasswordEncoder().encode(password));

		var user = userRepository.save(userEntity);
		log.info(user);
	}

}
