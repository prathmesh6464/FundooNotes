package com.bridz.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bridz.dto.JwtResponseToken;
import com.bridz.dto.LabelDto;
import com.bridz.exception.LabelException;
import com.bridz.model.LabelData;
import com.bridz.repository.LabelRepository;
import com.bridz.repository.UserRepository;

@Service
public class LabelServiceImplementation implements LabelService {

	@Autowired
	LabelData entity;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	List<LabelDto> listOfLabelDto;

	@Autowired
	private Environment environment;

	@Autowired
	LabelRepository repository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@Override
	public ResponseEntity<String> add(LabelDto labelDto, JwtResponseToken token) {

		if (!userService.getJwtResponseToken().equals(token.getJwtToken())) {

			throw new LabelException(Integer.parseInt(environment.getProperty("status.user.loginErrorCode")),
					environment.getProperty("status.user.loginErrorMessage"));
		}

		modelMapper.map(labelDto, entity);
		repository.save(entity);

		return new ResponseEntity<String>(environment.getProperty("status.success.label.addlabel"), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> edite(LabelDto labelDto, long id, JwtResponseToken token) {

		if (!userService.getJwtResponseToken().equals(token.getJwtToken())) {

			throw new LabelException(Integer.parseInt(environment.getProperty("status.notes.user.errorCode")),
					environment.getProperty("status.notes.user.loginErrorMessage"));
		}

		modelMapper.map(labelDto, entity);

		Optional<LabelData> labelData = repository.findById(id);

		if (labelData.isPresent()) {

			labelData.get().setLabelName(entity.getLabelName());
		} else {

			throw new LabelException(Integer.parseInt(environment.getProperty("status.label.edite.errorCode")),
					environment.getProperty("status.label.edite.errorMessage"));
		}

		repository.save(labelData.get());

		return new ResponseEntity<String>(environment.getProperty("status.success.label.editelabel"), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<String> delete(long id, JwtResponseToken token) {

		if (!userService.getJwtResponseToken().equals(token.getJwtToken())) {

			throw new LabelException(Integer.parseInt(environment.getProperty("status.notes.user.errorCode")),
					environment.getProperty("status.notes.user.loginErrorMessage"));
		}

		repository.deleteById(id);

		return new ResponseEntity<String>(environment.getProperty("status.success.label.deletelabel"), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> show(JwtResponseToken token) {

		if (!userService.getJwtResponseToken().equals(token.getJwtToken())) {

			throw new LabelException(Integer.parseInt(environment.getProperty("status.notes.user.errorCode")),
					environment.getProperty("status.notes.user.loginErrorMessage"));
		}

		repository.findAll().stream().forEach(labelEntityObject -> {

			listOfLabelDto.add(modelMapper.map(labelEntityObject, LabelDto.class));
		});

		return new ResponseEntity<Object>(listOfLabelDto, HttpStatus.OK);
	}
}
