package com.bridz.service;

import java.util.Optional;

import com.bridz.utility.JwtToken;
import com.bridz.utility.EmailService;

import com.bridz.exception.JmsException;
import com.bridz.exception.JwtTokenException;

import com.bridz.dto.ResetPasswordDto;
import com.bridz.dto.ForgetPasswordDto;
import com.bridz.dto.LoginDto;
import com.bridz.dto.UserRegistrationDto;

import com.bridz.model.UserDetails;
import com.bridz.repository.UserRepository;

//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageProperties;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.management.JMException;
import org.modelmapper.ModelMapper;

@Service
public class UserServiceImplementation implements UserService {

	// Creating Object of model mapper]
	@Autowired
	private ModelMapper modelMapper;

	// @Autowired
	// private RabbitTemplate rabbitTemplate;

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

	private String emailId;

	@Override
	public ResponseEntity<String> registerUser(UserRegistrationDto userRegisterDto) {

		modelMapper.map(userRegisterDto, userDetailsEntity);

		if (repository.findByUserName(userDetailsEntity.getUserName()).isPresent()) {

			return new ResponseEntity<String>(environment.getProperty("status.user.registration.userName"),
					HttpStatus.OK);
		}

		if (repository.findByEmailId(userDetailsEntity.getEmailId()).isPresent()) {

			return new ResponseEntity<String>(environment.getProperty("status.user.registration.emailId"),
					HttpStatus.OK);
		}

		if (repository.findByPassword(userDetailsEntity.getPassword()).isPresent()) {

			return new ResponseEntity<String>(environment.getProperty("status.user.registration.password"),
					HttpStatus.OK);
		}

		repository.save(userDetailsEntity);

		this.sendMail(userRegisterDto);

		return new ResponseEntity<String>(environment.getProperty("status.success.user.register"), HttpStatus.OK);
	}

	public void sendMail(UserRegistrationDto userRegistrationDto) {

		// MessageProperties messageProperties = new MessageProperties();

		// Email related variables
		String to = userRegistrationDto.getEmailId();
		String subject = "Authentication of new registered user";
		token = jwtToken.generateToken(userRegistrationDto);
		emailId = to;

		// User verification url
		String userVerificationUrl = environment.getProperty("user.verification") + token;

		// Message message = new Message(userVerificationUrl.getBytes(), new
		// MessageProperties());

		// Send email method called
		try {

			emailService.send(to, subject, userVerificationUrl);
		} catch (JMException e) {

			throw new JmsException(Integer.parseInt("status.jms.send.mail.errorCode"),
					environment.getProperty("status.jms.send.mail.errorMessage"));
		}

		// rabbitTemplate.send(environment.getProperty("rabbitmq.exchangeName"),
		// environment.getProperty("rabbitmq.event.topic"), message);
	}

	@Override
	public ResponseEntity<String> login(LoginDto userLoginDto) {

		// Using model mapper mapping dto object with user details entity
		modelMapper.map(userLoginDto, userDetailsEntity);

		// Checking user name and password is valid or not
		if (repository.findByUserName(userDetailsEntity.getUserName()).isPresent()
				&& (repository.findByPassword(userDetailsEntity.getPassword()).isPresent())
				&& repository.findByUserName(userDetailsEntity.getUserName()).get()
						.equals(repository.findByPassword(userDetailsEntity.getPassword()).get())
				&& repository.findByPassword(userDetailsEntity.getPassword()).get().getIsEmailVeriefied() == true) {

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

		if (repository.findByPassword(userDetailsEntity.getPassword()).isPresent()) {

			return new ResponseEntity<String>(environment.getProperty("status.user.registration.password"),
					HttpStatus.OK);
		}

		// Storing user's edited information to data base
		if (resetPasswordDto.getPassword().equals(resetPasswordDto.getConfirmPassword())) {

			Optional<UserDetails> userDetailsInfo = repository.findByEmailId(emailId);

			if (userDetailsInfo.isPresent()) {

				userDetailsInfo.get().setPassword(userDetailsEntity.getPassword());
			} else {

				throw new JwtTokenException(
						Integer.parseInt(environment.getProperty("status.user.emailNotFoundErrorCode")),
						environment.getProperty("status.user.emailNotFoundErrorMessage"));
			}

			repository.saveAndFlush(userDetailsInfo.get());

			return new ResponseEntity<String>(environment.getProperty("status.success.user.resetPassword"),
					HttpStatus.OK);
		}
		return new ResponseEntity<String>(environment.getProperty("status.user.authentication.password.errorMessage"),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> verification(String emailToken) {

		Optional<UserDetails> userDetails = repository.findByEmailId(emailId);

		// Checking system generated token and email send token is equal or not
		if (emailToken.equals(token)) {

			userDetails.get().setIsEmailVeriefied(true);
			repository.save(userDetails.get());
			return new ResponseEntity<String>(environment.getProperty("status.success.user.verification"),
					HttpStatus.OK);
		}

		throw new JwtTokenException(Integer.parseInt(environment.getProperty("status.jwt.token.match.errorCode")),
				environment.getProperty("status.jwt.token.match.errorMessage"));
	}

}