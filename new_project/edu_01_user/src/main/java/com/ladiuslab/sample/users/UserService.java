package com.ladiuslab.sample.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 사용자 저장 및 수정
	 * @param user
	 * @return
	 */
	public User save(User user) {
		
		User newUser = this.userRepository.save(user);
		
		return newUser;
	}
	
	/**
	 * 전체 사용자 조회
	 * @return
	 */
	public List<User> list(){
		List<User> users = this.userRepository.findAll();
		return users;
	}
	
	public User findByEmail( String email) {
		
		return this.userRepository.findByEmail(email);
	}
	
	public void delete(User user) {
		this.userRepository.delete(user);
	}
}
