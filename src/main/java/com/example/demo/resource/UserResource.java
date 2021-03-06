package com.example.demo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Request;
import com.example.demo.domain.User;
import com.example.demo.dto.UserLogindto;
import com.example.demo.service.RequestService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value = "users")
public class UserResource {
	@Autowired private UserService userService;
	@Autowired private RequestService requestService;
	
	//save
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user){
		User createdUser = userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	//update
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(name = "id") Long id, @RequestBody User user){
		user.setId(id);
//		return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
		return ResponseEntity.ok(userService.save(user));
	}
	
	//getbyid
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable(name = "id") Long id){
		User user = userService.getById(id);
		return ResponseEntity.ok(user);
		
	}
	
	//list
	@GetMapping
	public ResponseEntity<List<User>> listAll(){
		List<User> users = userService.listAll();
		return ResponseEntity.ok(users);
	}
	
	//login
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserLogindto user){
		User loggedUser = userService.login(user.getEmail(), user.getPassword());
		return ResponseEntity.ok(loggedUser);
	}
	
	@GetMapping("/{id}/requests")
	public ResponseEntity<List<Request>> listAllRequestsById(@PathVariable(name="id") Long id){
		List<Request> requests = requestService.listAllByOwnerId(id);
		return ResponseEntity.ok(requests);
	}
	
}
