package com.ladiuslab.sample.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "new_user2")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@ToString
public class User {

	@Id @GeneratedValue
	private Long id;
	
	private String name;
	private String email;
	private String password;
	private Integer age;
}
