package com.bridz.service;

import org.springframework.http.ResponseEntity;
import com.bridz.dto.NotesDto;
import com.bridz.dto.ReminderDateTimeDto;

public interface NotesService {

	public ResponseEntity<String> save(NotesDto notesDto);

	public ResponseEntity<String> delete(Long id);

	public ResponseEntity<Object> show();

	public ResponseEntity<String> update(NotesDto notesDto, Long id);

	public ResponseEntity<String> trashUntrash(long id);

	public ResponseEntity<String> archiveUnArchive(long id);

	public ResponseEntity<String> pinedUnPined(long id);

	public ResponseEntity<Object> sortByTitle();

	public ResponseEntity<Object> sortByDescription();

	public ResponseEntity<Object> findByTitle(String title);

	public ResponseEntity<Object> findByDescription(String description);

	public ResponseEntity<String> setReminder(ReminderDateTimeDto reminderDateTimeDto, long id);

	public ResponseEntity<String> unsetReminder(long id);

	public ResponseEntity<String> resetReminder(ReminderDateTimeDto reminderDateTimeDto, long id);

}
