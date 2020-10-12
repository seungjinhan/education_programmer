package com.ladiuslab.sample.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
	
	public User findByEmailAndPassword( String email, String password);
}
