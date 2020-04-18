package com.bridz.controller;

import com.bridz.model.UserDetails;
import com.bridz.model.LoginData;
import com.bridz.model.ResetPasswordData;
import com.bridz.model.SecretInformation;
import com.bridz.response.Response;
import com.bridz.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginControl {

	@Autowired
	UserService userServiceObject;

	String secretWord;

	@GetMapping("/userLogin")
	@ResponseBody
	public List<UserDetails> userLogin(@RequestBody LoginData userLoginObject) {

		return userServiceObject.userLogin(userLoginObject);
	}

	@RequestMapping(value = "/userRegistration", method = { RequestMethod.POST })
	@ResponseBody
	public Response userRegistration(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("userName") String userName,
			@RequestParam("password") String password, @RequestParam("mobileNumber") long mobileNumber,
			@RequestParam("secretEmergencyWord") String secretEmergencyWord,
			@RequestParam("firstMobileNumber") long firstMobileNumber) {

		UserDetails userDetailsObject = new UserDetails(firstName, lastName, userName, password, mobileNumber,
				secretEmergencyWord, firstMobileNumber);

		// Storing user data into data base
		Response response = userServiceObject.registerUser(userDetailsObject);

		return response;
	}

	@GetMapping("/forgetPassword")
	@ResponseBody
	public Response forgetPassword(@RequestBody SecretInformation secretInformationData) {

		secretWord = secretInformationData.getSecretEmergencyWord();
		Response response = userServiceObject.forgetPassword(secretInformationData);

		return response;
	}

	@RequestMapping(value = "/resetPassword", method = { RequestMethod.PUT })
	@ResponseBody
	public Response resetPassword(@RequestBody ResetPasswordData resetPassword) {

		return userServiceObject.resetPassword(resetPassword, secretWord);
	}

	//	@RequestMapping(value = "/addNote", method = { RequestMethod.POST })
	//	public String addUserNote(NotesData userNote) {
	//
	//		// Storing user note data into data base
	//		notesRepository.save(userNote);
	//		return "Note added successfully";
	//	}
	//
	//	@RequestMapping(value = "/deleteNote", method = { RequestMethod.DELETE })
	//	public String deleteUserNote(NotesData userNote) {
	//
	//		// Storing user note data into data base
	//		// notesRepository.delete();
	//		return "Note deleted successfully";
	//	}
}