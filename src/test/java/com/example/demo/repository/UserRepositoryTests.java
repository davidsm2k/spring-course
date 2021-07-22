package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.domain.User;
import com.example.demo.domain.enums.Role;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class) // Executar teste na ordem que indicar em cada metodo
@SpringBootTest
public class UserRepositoryTests {
	
	@Autowired 
	private UserRepository userRepository;
	
	@Test
	@Order(1)
	public void saveTest() {
		User user = new User(null, "David", "david.santos@gmail.com", "123", Role.ADMINISTRATOR, null, null);
		User createdUser = userRepository.save(user);
		
		assertThat(createdUser.getId()).isEqualTo(1L);
	}

	@Test
	public void updateTest() {
		User user = new User(1L, "David Santos", "david.santos@gmail.com", "123", Role.ADMINISTRATOR, null, null);
		User updatedUser = userRepository.save(user);
		
		assertThat(updatedUser.getName()).isEqualTo("David Santos");
	}

	@Test
	public void getByIdTest() {
		Optional<User> result = userRepository.findById(1L);
		User user = result.get();
		
		assertThat(user.getPassword()).isEqualTo("123");
	}
	
	@Test
	public void listTest() {
		List<User> users = userRepository.findAll();
		
		assertThat(users.size()).isEqualTo(1);
	}
	
	@Test
	public void loginTest() {
		Optional<User> result = userRepository.login("david.santos@gmail.com", "123");
		User loggedUser = result.get();
		
		assertThat(loggedUser.getId()).isEqualTo(1L);
	}
}
