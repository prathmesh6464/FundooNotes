package com.bridz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bridz.dto.LabelDto;
import com.bridz.service.LabelService;

@RestController
public class LabelController {

	@Autowired
	LabelService labelServiceObject;

	@PostMapping("/addLabel")
	ResponseEntity<String> addLabel(@RequestBody LabelDto labelDtoObject) {

		return labelServiceObject.addLabel(labelDtoObject);
	}

	@PutMapping("/editeLabel/{id}")
	ResponseEntity<String> editeLabel(@PathVariable("id") long id, @RequestBody LabelDto labelDtoObject) {

		return labelServiceObject.editeLabel(labelDtoObject, id);
	}

	@DeleteMapping("/deleteLabel/{id}")
	ResponseEntity<String> deleteLabel(@PathVariable("id") long id) {

		return labelServiceObject.deleteLabel(id);
	}

	@GetMapping("/showLabel")
	ResponseEntity<Object> showLabel() {

		return labelServiceObject.showLabel();
	}
}
