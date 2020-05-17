package com.bridz.service;

import org.springframework.http.ResponseEntity;

import com.bridz.dto.JwtResponseToken;
import com.bridz.dto.LabelDto;

public interface LabelService {

	ResponseEntity<String> add(LabelDto labelDto, JwtResponseToken token);

	ResponseEntity<String> edite(LabelDto labelDto, long id, JwtResponseToken token);

	ResponseEntity<String> delete(long id, JwtResponseToken token);

	ResponseEntity<Object> show(JwtResponseToken token);	

}
