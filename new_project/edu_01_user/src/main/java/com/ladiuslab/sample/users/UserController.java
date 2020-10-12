package com.ladiuslab.sample.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User> save( @RequestBody User user) {
		
		User newUser = this.userService.save(user);
		return new ResponseEntity<User>( newUser, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity< List<User>> findall(){
		List<User> users = this.userService.list();
		return new ResponseEntity<List<User>>( users, HttpStatus.OK);
	}
	
	@GetMapping("{email}")
	public ResponseEntity< User> findByemail( String email){
		User user = this.userService.findByEmail(email);
		return new ResponseEntity<User>( user, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<User> update( @RequestBody User user) {
		
		User newUser = this.userService.save(user);
		return new ResponseEntity<User>( newUser, HttpStatus.CREATED);
	} 
	
	@DeleteMapping
	public ResponseEntity<User> delete( @RequestBody User user) {
		
		this.userService.delete(user);
		return new ResponseEntity<User>( HttpStatus.CREATED);
	}  
}
