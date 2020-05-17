package com.bridz.controller;

import com.bridz.dto.JwtResponseToken;
import com.bridz.dto.NotesDto;
import com.bridz.dto.ReminderDateTimeDto;
import com.bridz.service.NotesService;

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

@RestController
@RequestMapping("/notes")
@CrossOrigin(origins = "*")
public class NotesController {

	@Autowired
	NotesService notesService;

	@PostMapping("/add/{token}")
	public ResponseEntity<String> add(@RequestBody NotesDto notesDto, @PathVariable JwtResponseToken token) {

		// Storing user note data into data base
		return notesService.save(notesDto, token);
	}

	@DeleteMapping("/delete/{id}/{token}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id, @PathVariable JwtResponseToken token) {

		// Deleting user note from data base
		return notesService.delete(id, token);
	}

	@PutMapping("/update/{id}/{token}")
	public ResponseEntity<String> update(@PathVariable("id") long id, @RequestBody NotesDto notesDto,
			@PathVariable JwtResponseToken token) {

		// Updating user note data into data base
		return notesService.update(notesDto, id, token);
	}

	@GetMapping("/show/{token}")
	public ResponseEntity<Object> show(@PathVariable JwtResponseToken token) {

		// Showing user note data from data base
		return notesService.show(token);
	}

	@PutMapping("/trashOrUnTrash/{id}/{token}")
	public ResponseEntity<String> setTrashOrUntrash(@PathVariable("id") long id, @PathVariable JwtResponseToken token) {

		return notesService.trashUntrash(id, token);
	}

	@PutMapping("/archiveOrUnArchive/{id}/{token}")
	public ResponseEntity<String> setArchiveOrUnArchive(@PathVariable("id") long id,
			@PathVariable JwtResponseToken token) {

		return notesService.archiveUnArchive(id, token);
	}

	@PutMapping("/pinedOrUnPined/{id}/{token}")
	public ResponseEntity<String> setPinedOrUnPined(@PathVariable("id") long id, @PathVariable JwtResponseToken token) {

		return notesService.pinedUnPined(id, token);
	}

	@GetMapping("/sortByTitle/{token}")
	public ResponseEntity<Object> sortByTitle(@PathVariable JwtResponseToken token) {

		return notesService.sortByTitle(token);
	}

	@GetMapping("/sortByDescription/{token}")
	public ResponseEntity<Object> sortByDescription(@PathVariable JwtResponseToken token) {

		return notesService.sortByDescription(token);
	}

	@GetMapping("/findByTitle/{title}/{token}")
	public ResponseEntity<Object> findByTitle(@PathVariable("title") String title,
			@PathVariable JwtResponseToken token) {

		return notesService.findByTitle(title, token);
	}

	@GetMapping("/findByDescription/{description}/{token}")
	public ResponseEntity<Object> FindByDescription(@PathVariable("description") String description,
			@PathVariable JwtResponseToken token) {

		return notesService.findByDescription(description, token);
	}

	@PostMapping("/setReminder/{id}/{token}")
	public ResponseEntity<String> setReminder(@RequestBody ReminderDateTimeDto reminderDateTimeDto,
			@PathVariable("id") long id, @PathVariable JwtResponseToken token) {

		return notesService.setReminder(reminderDateTimeDto, id, token);
	}

	@PutMapping("/unsetReminder/{id}/{token}")
	public ResponseEntity<String> unsetReminder(@PathVariable("id") long id, @PathVariable JwtResponseToken token) {

		return notesService.unsetReminder(id, token);
	}

	@PutMapping("/resetReminder/{id}/{token}")
	public ResponseEntity<String> resetReminder(@RequestBody ReminderDateTimeDto reminderDateTimeDto,
			@PathVariable("id") long id, @PathVariable JwtResponseToken token) {

		return notesService.resetReminder(reminderDateTimeDto, id, token);
	}

}
