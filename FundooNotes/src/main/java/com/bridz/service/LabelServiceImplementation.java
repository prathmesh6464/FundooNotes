package com.bridz.service;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bridz.dto.LabelDto;
import com.bridz.exception.LabelException;
import com.bridz.model.LabelData;
import com.bridz.repository.LabelRepository;

@Service
public class LabelServiceImplementation implements LabelService {

	@Autowired
	LabelData labelDataEntity;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	List<LabelDto> listOfLabelDto;

	@Autowired
	private Environment environment;

	@Autowired
	LabelRepository labelRepository;

	@Override
	public ResponseEntity<String> add(LabelDto labelDto) {

		modelMapper.map(labelDto, labelDataEntity);
		labelRepository.save(labelDataEntity);

		return new ResponseEntity<String>(environment.getProperty("status.success.label.addlabel"),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> edite(LabelDto labelDto, long id) {

		modelMapper.map(labelDto, labelDataEntity);

		try {
			labelRepository.setById(labelDto.getLabelName(), id);
		} catch (Exception exception) {
			return new ResponseEntity<String>(environment.getProperty("status.success.label.editelabel"),
					HttpStatus.OK);
		}

		throw new LabelException(Integer.parseInt(environment.getProperty("status.label.edite.errorCode")),
				environment.getProperty("status.label.edite.errorMessage"));
	}

	@Override
	public ResponseEntity<String> delete(long id) {

		labelRepository.deleteById(id);

		return new ResponseEntity<String>(environment.getProperty("status.success.label.deletelabel"),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> show() {

		labelRepository.findAll().stream().forEach(labelEntityObject -> {

			listOfLabelDto.add(modelMapper.map(labelEntityObject, LabelDto.class));
		});

		return new ResponseEntity<Object>(listOfLabelDto, HttpStatus.OK);
	}
}
