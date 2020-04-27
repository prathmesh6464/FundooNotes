package com.bridz.service;

import org.springframework.http.ResponseEntity;
import com.bridz.dto.NotesDto;

public interface NotesService {

	public ResponseEntity<String> saveNote(NotesDto notesDtoObject);

	public ResponseEntity<String> deleteNote(Long id);

	public ResponseEntity<Object> showNotes();

	public ResponseEntity<String> updateNote(NotesDto notesDtoObject, Long id);

	public ResponseEntity<String> trashUntrash(long id);

	public ResponseEntity<String> archiveUnArchive(long id);

	public ResponseEntity<String> pinedUnPined(long id);

	public ResponseEntity<Object> sortByTitle();

	public ResponseEntity<Object> sortByDescription();

	public ResponseEntity<Object> findByTitle(String title);

	public ResponseEntity<Object> findByDescription(String description);

}
