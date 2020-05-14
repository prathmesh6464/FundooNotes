package com.bridz.repository;

import com.bridz.model.UserDetails;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Interface
@Repository
public interface UserRepository extends JpaRepository<UserDetails, Integer> {

	// Method for finding data by using user name and password
	Optional<UserDetails> findByUserName(String userName);

	Optional<UserDetails> findByPassword(String password);

	Optional<UserDetails> findByEmailId(String emailId);

}
