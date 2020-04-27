package com.bridz.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bridz.dto.LabelDto;
import com.bridz.model.LabelData;
import com.bridz.repository.LabelRepository;

@Service
public class LabelServiceImplementation implements LabelService {

	LabelData labelDataObject = new LabelData();

	ModelMapper modelMapperObject = new ModelMapper();

	List<LabelDto> listOfLabelDtoObject = new ArrayList<>();

	@Autowired
	LabelRepository labelRepositoryObject;

	@Override
	public ResponseEntity<String> addLabel(LabelDto labelDtoObject) {

		modelMapperObject.map(labelDtoObject, labelDataObject);
		labelRepositoryObject.save(labelDataObject);

		return new ResponseEntity<String>("Added label", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> editeLabel(LabelDto labelDtoObject, long id) {

		modelMapperObject.map(labelDtoObject, labelDataObject);

		try {
			labelRepositoryObject.setById(labelDtoObject.getLabelName(), id);
		} catch (Exception exception) {
			return new ResponseEntity<String>("Edited label", HttpStatus.OK);
		}

		return null;
	}

	@Override
	public ResponseEntity<String> deleteLabel(long id) {

		labelRepositoryObject.deleteById(id);

		return new ResponseEntity<String>("Deleted label", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> showLabel() {

		labelRepositoryObject.findAll().stream().forEach(labelEntityObject -> {

			listOfLabelDtoObject.add(modelMapperObject.map(labelEntityObject, LabelDto.class));
		});

		return new ResponseEntity<Object>(listOfLabelDtoObject, HttpStatus.OK);
	}
}
