package com.bridz.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bridz.model.UserDetails;


//Interface
public interface UserRepository extends JpaRepository<UserDetails, Integer>  {
	
	//Method for finding data by using user name and password
	List<UserDetails> findByUserName(String userName);
	List<UserDetails> findByPassword(String password);
	List<UserDetails> findBySecretEmergencyWord(String secretEmergencyWord);
	List<UserDetails> findByFirstMobileNumber(long firstMobileNumber);
}
