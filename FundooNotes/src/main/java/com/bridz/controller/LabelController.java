package com.bridz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridz.dto.JwtResponseToken;
import com.bridz.dto.LabelDto;
import com.bridz.service.LabelService;

@RestController
@RequestMapping("/label")
@CrossOrigin(origins = "*")
public class LabelController {

	@Autowired
	LabelService labelService;

	@PostMapping("/add/{tokenObject}")
	ResponseEntity<String> add(@RequestBody LabelDto labelDto, @PathVariable("tokenObject") JwtResponseToken token) {

		return labelService.add(labelDto, token);
	}

	@PutMapping("/edite/{id}/{tokenObject}")
	ResponseEntity<String> edite(@PathVariable("id") long id, @RequestBody LabelDto labelDto,
			@PathVariable("tokenObject") JwtResponseToken token) {

		return labelService.edite(labelDto, id, token);
	}

	@DeleteMapping("/delete/{id}/{tokenObject}")
	ResponseEntity<String> delete(@PathVariable("id") long id, @PathVariable("tokenObject") JwtResponseToken token) {

		return labelService.delete(id, token);
	}

	@GetMapping("/show/{tokenObject}")
	ResponseEntity<Object> show(@PathVariable("tokenObject") JwtResponseToken token) {

		return labelService.show(token);
	}
}
