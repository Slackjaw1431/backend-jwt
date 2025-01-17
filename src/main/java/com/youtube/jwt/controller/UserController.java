package com.youtube.jwt.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.youtube.jwt.entity.User;
import com.youtube.jwt.repo.UserRepository;
import com.youtube.jwt.service.UserServiceImp;

@CrossOrigin()
@RestController
public class UserController {

	@Autowired
	private UserServiceImp userService;
	
	@Autowired
	UserRepository userRepo;

	@PostConstruct
	public void initRoleAndUser() {
		userService.initRoleAndUser();
	}

	@PostMapping({ "/registerNewUser" })
	public User registerNewUser(@RequestBody User user) {
		return userService.registerNewUser(user);
	}

	@DeleteMapping({ "/deleteUser" })
	@PreAuthorize("hasRole('Admin')")
	public void deleteUser(@RequestBody User user) {
		userService.deleteUser(user);
	}
	
	@GetMapping({"/allUsers"})
	@PreAuthorize("hasRole('Admin')")
	public List<User> getAllUsers(){
		return userService.fetchUsers();
	}
	
	@GetMapping({"/allUsersPage"})
	@PreAuthorize("hasRole('Admin')")
	public Page<User> getAllUsersPage(Pageable pageable){
		return userRepo.findAll(pageable);
	}

	@DeleteMapping({"/deleteUser/{userName}"})
	@PreAuthorize("hasRole('Admin')")
	public void deleteUserByUserName(@PathVariable String userName) {
		User user = userService.get(userName);
		userService.deleteUser(user);
//		userRepo.delete(user);
	}

	@GetMapping("/userById/{id}")
	public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) {
		Optional<User> user = userService.get(id);
		return ResponseEntity.ok(user);
	}

	@PutMapping({ "/updateUser" })
	@PreAuthorize("hasRole('Admin')")
	public void updateUser(@RequestBody User user) {
		userService.updateUser(user);
	}

	@GetMapping({ "/forAdmin" })
	@PreAuthorize("hasRole('Admin')")
	public String forAdmin() {
//		System.out.println("For Admin");
		return "This URL is only accessible to the admin";
	}

	@GetMapping({ "/forUser" })
	@PreAuthorize("hasRole('User')")
	public String forUser() {
//		System.out.println("For User");
		return "This URL is only accessible to the user";
	}

}
