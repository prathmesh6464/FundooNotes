package com.bridz.service;

import java.util.Collections;
import javax.management.JMException;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bridz.dto.ResetPasswordDto;
import com.bridz.dto.ForgetPasswordDto;
import com.bridz.dto.LoginDto;
import com.bridz.dto.UserRegistrationDto;
import com.bridz.exception.JmsException;
import com.bridz.exception.JwtTokenException;
import com.bridz.model.UserDetails;
import com.bridz.repository.UserRepository;
import com.bridz.utility.JwtToken;
import com.bridz.utility.EmailService;

@Service
public class UserServiceImplementation implements UserService {

	// Creating Object of model mapper]
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	// Creating Object of user Details Entity
	@Autowired
	private UserDetails userDetailsEntity;

	// User repository object
	@Autowired
	private UserRepository repository;

	@Autowired
	Environment environment;

	// Jwt token object created
	@Autowired
	private JwtToken jwtToken;

	// Email service object created
	@Autowired
	private EmailService emailService;

	// Token variable for verification of token
	private String token;

	// User registration dto object used in user verification
	private UserRegistrationDto userRegistrationDto;

	private String emailId;

	@Override
	public ResponseEntity<String> registerUser(UserRegistrationDto userRegisterDto) {

		// User registration object used in user Verification
		userRegistrationDto = userRegisterDto;

		MessageProperties messageProperties = new MessageProperties();

		// Email related variables
		String to = userRegisterDto.getEmailId();
		String subject = "Authentication of new registered user";
		token = jwtToken.generateToken(userRegisterDto);

		// User verification url
		String userVerificationUrl = environment.getProperty("user.verification") + token;

		Message message = new Message(userVerificationUrl.getBytes(), new MessageProperties());
		;

		// Send email method called
		try {

			emailService.send(to, subject, userVerificationUrl);
		} catch (JMException e) {

			throw new JmsException(Integer.parseInt("status.jms.send.mail.errorCode"),
					environment.getProperty("status.jms.send.mail.errorMessage"));
		}

		rabbitTemplate.send(environment.getProperty("rabbitmq.exchangeName"),
				environment.getProperty("rabbitmq.event.topic"), message);

		return new ResponseEntity<String>(environment.getProperty("status.success.user.register"), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> login(LoginDto userLoginDto) {

		// Using model mapper mapping dto object with user details entity
		modelMapper.map(userLoginDto, userDetailsEntity);

		// Checking user name and password is valid or not
		if (!repository.findByUserName(userDetailsEntity.getUserName()).equals(Collections.<String>emptyList())
				&& (!repository.findByPassword(userDetailsEntity.getPassword()).equals(Collections.<String>emptyList()))
				&& repository.findByUserName(userDetailsEntity.getUserName())
						.equals(repository.findByPassword(userDetailsEntity.getPassword()))) {

			return new ResponseEntity<String>(environment.getProperty("status.success.user.login"), HttpStatus.OK);
		}

		return new ResponseEntity<String>(environment.getProperty("status.user.authentication.errorMessage"),
				HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	}

	@Override
	public ResponseEntity<String> forgetPassword(ForgetPasswordDto forgetPasswordDto) {

		// Email related variables
		String to = forgetPasswordDto.getEmailId();
		String subject = "Authentication";
		String token = jwtToken.generateToken(forgetPasswordDto);
		emailId = forgetPasswordDto.getEmailId();

		// Reset url
		String resetPasswordUrl = environment.getProperty("user.resetpassword") + token + "/" + emailId;

		try {

			// Send email method called
			emailService.send(to, subject, resetPasswordUrl);
		} catch (JMException e) {

			throw new JmsException(

					Integer.parseInt(environment.getProperty("status.jms.send.mail.errorCode")),
					environment.getProperty("status.jms.send.mail.errorMessage"));
		}

		return new ResponseEntity<String>(environment.getProperty("status.success.user.forgetpassword"), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> resetPassword(ResetPasswordDto resetPasswordDto) {

		// Using model mapper mapping dto object with user details entity
		modelMapper.map(resetPasswordDto, userDetailsEntity);

		// Storing user's edited information to data base
		if (resetPasswordDto.getPassword().equals(resetPasswordDto.getConfirmPassword())) {

			try {

				repository.setPassword(userDetailsEntity.getPassword(), emailId);
			} catch (Exception e) {

				return new ResponseEntity<String>(environment.getProperty("status.success.user.resetPassword"),
						HttpStatus.OK);
			}
		}

		return new ResponseEntity<String>(environment.getProperty("status.user.authentication.password.errorMessage"),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> verification(String emailToken) {

		// Checking system generated token and email send token is equal or not
		if (emailToken.equals(token)) {

			// saving user data into database
			repository.save(userDetailsEntity);

			return new ResponseEntity<String>(environment.getProperty("status.success.user.verification"),
					HttpStatus.OK);
		}

		throw new JwtTokenException(Integer.parseInt(environment.getProperty("status.jwt.token.match.errorCode")),
				environment.getProperty("status.jwt.token.match.errorMessage"));
	}

}