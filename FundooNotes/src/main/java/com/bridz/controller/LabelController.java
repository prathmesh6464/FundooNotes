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
import com.bridz.dto.LabelDto;
import com.bridz.service.LabelService;

@RestController
@RequestMapping("/label")
@CrossOrigin(origins = "*")
public class LabelController {

	@Autowired
	LabelService labelService;

	@PostMapping("/add")
	ResponseEntity<String> add(@RequestBody LabelDto labelDto) {

		return labelService.add(labelDto);
	}

	@PutMapping("/edite/{id}")
	ResponseEntity<String> edite(@PathVariable("id") long id, @RequestBody LabelDto labelDto) {

		return labelService.edite(labelDto, id);
	}

	@DeleteMapping("/delete/{id}")
	ResponseEntity<String> delete(@PathVariable("id") long id) {

		return labelService.delete(id);
	}

	@GetMapping("/show")
	ResponseEntity<Object> show() {

		return labelService.show();
	}
}
