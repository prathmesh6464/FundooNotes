package com.bridz.service;

import org.springframework.http.ResponseEntity;
import com.bridz.dto.LabelDto;

public interface LabelService {

	ResponseEntity<String> addLabel(LabelDto labelDtoObject);

	ResponseEntity<String> editeLabel(LabelDto labelDtoObject, long id);

	ResponseEntity<String> deleteLabel(long id);

	ResponseEntity<Object> showLabel();

}
