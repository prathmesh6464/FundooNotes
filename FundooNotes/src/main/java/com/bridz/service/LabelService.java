package com.bridz.service;

import org.springframework.http.ResponseEntity;
import com.bridz.dto.LabelDto;

public interface LabelService {

	ResponseEntity<String> add(LabelDto labelDto);

	ResponseEntity<String> edite(LabelDto labelDto, long id);

	ResponseEntity<String> delete(long id);

	ResponseEntity<Object> show();

}
