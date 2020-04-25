package com.bridz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bridz.dto.NotesDto;
import com.bridz.service.NotesService;

@RestController
public class NotesController {

	@Autowired
	NotesService notesServiceObject;

	@PostMapping("/addNote")
	public ResponseEntity<String> addUserNote(@RequestBody NotesDto notesDtoObject) {

		// Storing user note data into data base
		return notesServiceObject.saveNote(notesDtoObject);
	}

	@DeleteMapping("/deleteNote/{title}")
	public ResponseEntity<String> deleteUserNote(@PathVariable("title") String title) {

		// Deleting user note from data base
		return notesServiceObject.deleteNote(title);
	}

}
