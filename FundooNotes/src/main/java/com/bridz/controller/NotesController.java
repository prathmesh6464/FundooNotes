package com.bridz.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bridz.dto.NotesDto;
import com.bridz.model.NotesData;
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

	@PutMapping("/updateNote/{title}")
	public ResponseEntity<String> updateUserNote(@PathVariable("title") String title,
			@RequestBody NotesDto notesDtoObject) {

		// Updating user note data into data base
		return notesServiceObject.updateNote(notesDtoObject, title);
	}

	@GetMapping("/showNotes")
	public List<NotesData> showeUserNotes() {

		// Showing user note data from data base
		return notesServiceObject.showNotes();
	}

}
