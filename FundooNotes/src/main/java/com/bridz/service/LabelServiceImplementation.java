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
	LabelData labelDataEntityObject;

	@Autowired
	ModelMapper modelMapperObject;

	@Autowired
	List<LabelDto> listOfLabelDtoObject;

	@Autowired
	private Environment environmentObject;

	@Autowired
	LabelRepository labelRepositoryObject;

	@Override
	public ResponseEntity<String> addLabel(LabelDto labelDtoObject) {

		modelMapperObject.map(labelDtoObject, labelDataEntityObject);
		labelRepositoryObject.save(labelDataEntityObject);

		return new ResponseEntity<String>(environmentObject.getProperty("status.success.label.addlabel"),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> editeLabel(LabelDto labelDtoObject, long id) {

		modelMapperObject.map(labelDtoObject, labelDataEntityObject);

		try {
			labelRepositoryObject.setById(labelDtoObject.getLabelName(), id);
		} catch (Exception exception) {
			return new ResponseEntity<String>(environmentObject.getProperty("status.success.label.editelabel"),
					HttpStatus.OK);
		}

		throw new LabelException(Integer.parseInt(environmentObject.getProperty("status.label.edite.errorCode")),
				environmentObject.getProperty("status.label.edite.errorMessage"));
	}

	@Override
	public ResponseEntity<String> deleteLabel(long id) {

		labelRepositoryObject.deleteById(id);

		return new ResponseEntity<String>(environmentObject.getProperty("status.success.label.deletelabel"),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> showLabel() {

		labelRepositoryObject.findAll().stream().forEach(labelEntityObject -> {

			listOfLabelDtoObject.add(modelMapperObject.map(labelEntityObject, LabelDto.class));
		});

		return new ResponseEntity<Object>(listOfLabelDtoObject, HttpStatus.OK);
	}
}
