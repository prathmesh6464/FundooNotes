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
import com.bridz.dto.NotesDto;
import com.bridz.dto.ReminderDateTimeDto;
import com.bridz.service.NotesService;

@RestController
@RequestMapping("/notes")
@CrossOrigin(origins = "*")
public class NotesController {

	@Autowired
	NotesService notesService;

	@PostMapping("/add")
	public ResponseEntity<String> add(@RequestBody NotesDto notesDto) {

		// Storing user note data into data base
		return notesService.save(notesDto);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {

		// Deleting user note from data base
		return notesService.delete(id);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable("id") long id, @RequestBody NotesDto notesDto) {

		// Updating user note data into data base
		return notesService.update(notesDto, id);
	}

	@GetMapping("/show")
	public ResponseEntity<Object> show() {

		// Showing user note data from data base
		return notesService.show();
	}

	@PostMapping("/trashOrUnTrash/{id}")
	public ResponseEntity<String> setTrashOrUntrash(@PathVariable("id") long id) {

		return notesService.trashUntrash(id);
	}

	@PostMapping("/archiveOrUnArchive/{id}")
	public ResponseEntity<String> setArchiveOrUnArchive(@PathVariable("id") long id) {

		return notesService.archiveUnArchive(id);
	}

	@PostMapping("/pinedOrUnPined/{id}")
	public ResponseEntity<String> setPinedOrUnPined(@PathVariable("id") long id) {

		return notesService.pinedUnPined(id);
	}

	@GetMapping("/sortByTitle")
	public ResponseEntity<Object> sortByTitle() {

		return notesService.sortByTitle();
	}

	@GetMapping("/sortByDescription")
	public ResponseEntity<Object> sortByDescription() {

		return notesService.sortByDescription();
	}

	@GetMapping("/findByTitle/{title}")
	public ResponseEntity<Object> findByTitle(@PathVariable("title") String title) {

		return notesService.findByTitle(title);
	}

	@GetMapping("/findByDescription/{description}")
	public ResponseEntity<Object> FindByDescription(@PathVariable("description") String description) {

		return notesService.findByDescription(description);
	}

	@PostMapping("/setReminder/{id}")
	public ResponseEntity<String> setReminder(@RequestBody ReminderDateTimeDto reminderDateTimeDto,
			@PathVariable("id") long id) {

		return notesService.setReminder(reminderDateTimeDto, id);
	}

	@PostMapping("/unsetReminder/{id}")
	public ResponseEntity<String> unsetReminder(@PathVariable("id") long id) {

		return notesService.unsetReminder(id);
	}

	@PostMapping("/resetReminder/{id}")
	public ResponseEntity<String> resetReminder(@RequestBody ReminderDateTimeDto reminderDateTimeDto,
			@PathVariable("id") long id) {

		return notesService.resetReminder(reminderDateTimeDto, id);
	}

}
