package com.bridz.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.bridz.dto.NotesDto;
import com.bridz.model.NotesData;

public interface NotesService {

	public ResponseEntity<String> saveNote(NotesDto notesDtoObject);

	public ResponseEntity<String> deleteNote(String title);

	public List<NotesData> showNotes();

	public ResponseEntity<String> updateNote(NotesDto notesDtoObject, String title);
}
