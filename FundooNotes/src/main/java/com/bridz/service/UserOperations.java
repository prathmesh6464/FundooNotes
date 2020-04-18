package com.bridz.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bridz.model.UserDetails;
import com.bridz.repository.UserRepository;

@Service
@Transactional 
public class RegisterUser implements UserService {

	private UserRepository userRepository;

	public RegisterUser(UserRepository userRepository) {

		super();
		this.userRepository = userRepository;
	}

	@Override
	public void registerUser(UserDetails userDetails) {

		userRepository.save(userDetails);

	}
}
