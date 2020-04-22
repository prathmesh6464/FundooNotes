package com.bridz.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bridz.model.UserDetails;

//Interface
public interface UserRepository extends JpaRepository<UserDetails, Integer> {

	// Method for finding data by using user name and password
	List<UserDetails> findByUserName(String userName);

	List<UserDetails> findByPassword(String password);

	List<UserDetails> findByEmailId(String emailId);

	@Query(value = "UPDATE user_details SET password = ? WHERE secret_emergency_word = ?", nativeQuery = true)
	@Modifying
	List<UserDetails> setPassword(String resetPassword, String emailId);
}
