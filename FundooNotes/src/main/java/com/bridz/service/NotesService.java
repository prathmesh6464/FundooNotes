package com.bridz.service;

import org.springframework.http.ResponseEntity;

import com.bridz.dto.JwtResponseToken;
import com.bridz.dto.NotesDto;
import com.bridz.dto.ReminderDateTimeDto;

public interface NotesService {

	public ResponseEntity<String> save(NotesDto notesDto, JwtResponseToken token);

	public ResponseEntity<String> delete(Long id, JwtResponseToken token);

	public ResponseEntity<Object> show(JwtResponseToken token);

	public ResponseEntity<String> update(NotesDto notesDto, Long id, JwtResponseToken token);

	public ResponseEntity<String> trashUntrash(long id, JwtResponseToken token);

	public ResponseEntity<String> archiveUnArchive(long id, JwtResponseToken token);

	public ResponseEntity<String> pinedUnPined(long id, JwtResponseToken token);

	public ResponseEntity<Object> sortByTitle(JwtResponseToken token);

	public ResponseEntity<Object> sortByDescription(JwtResponseToken token);

	public ResponseEntity<Object> findByTitle(String title, JwtResponseToken token);

	public ResponseEntity<Object> findByDescription(String description, JwtResponseToken token);

	public ResponseEntity<String> setReminder(ReminderDateTimeDto reminderDateTimeDto, long id, JwtResponseToken token);

	public ResponseEntity<String> unsetReminder(long id, JwtResponseToken token);

	public ResponseEntity<String> resetReminder(ReminderDateTimeDto reminderDateTimeDto, long id, JwtResponseToken token);

}
