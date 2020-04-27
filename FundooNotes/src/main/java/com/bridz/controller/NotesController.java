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

	@DeleteMapping("/deleteNote/{id}")
	public ResponseEntity<String> deleteUserNote(@PathVariable("id") Long id) {

		// Deleting user note from data base
		return notesServiceObject.deleteNote(id);
	}

	@PutMapping("/updateNote/{id}")
	public ResponseEntity<String> updateUserNote(@PathVariable("id") long id, @RequestBody NotesDto notesDtoObject) {

		// Updating user note data into data base
		return notesServiceObject.updateNote(notesDtoObject, id);
	}

	@GetMapping("/showNotes")
	public ResponseEntity<Object> showeUserNotes() {

		// Showing user note data from data base
		return notesServiceObject.showNotes();
	}

	@PostMapping("/trashOrUnTrashNote/{id}")
	public ResponseEntity<String> setTrashOrUntrash(@PathVariable("id") long id) {

		return notesServiceObject.trashUntrash(id);
	}

	@PostMapping("/archiveOrUnArchiveNote/{id}")
	public ResponseEntity<String> setArchiveOrUnArchive(@PathVariable("id") long id) {

		return notesServiceObject.archiveUnArchive(id);
	}

	@PostMapping("/pinedOrUnPinedNote/{id}")
	public ResponseEntity<String> setPinedOrUnPined(@PathVariable("id") long id) {

		return notesServiceObject.pinedUnPined(id);
	}

	@GetMapping("/sortByTitle")
	public ResponseEntity<Object> sortByTitle() {

		return notesServiceObject.sortByTitle();
	}

	@GetMapping("/sortByDescription")
	public ResponseEntity<Object> sortByDescription() {

		return notesServiceObject.sortByDescription();
	}

	@GetMapping("/findByTitle/{title}")
	public ResponseEntity<Object> findByTitle(@PathVariable("title") String title) {

		return notesServiceObject.findByTitle(title);
	}

	@GetMapping("/findByDescription/{description}")
	public ResponseEntity<Object> FindByDescription(@PathVariable("description") String description) {

		return notesServiceObject.findByDescription(description);
	}

}
