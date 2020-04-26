package com.bridz.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridz.dto.NotesDto;
import com.bridz.model.NotesData;
import com.bridz.repository.NotesRepository;

@Service
public class NotesServiceImplementation implements NotesService {

	// Creating Object of model mapper
	private ModelMapper modelMapperObject = new ModelMapper();

	// Creating Object of notes data entity
	private NotesData notesDataObject = new NotesData();

	// notes repository object
	@Autowired
	private NotesRepository notesRepositoryObject;

	@Override
	public ResponseEntity<String> saveNote(NotesDto notesDtoObject) {

		// Using model mapper mapping dto object with user details entity
		modelMapperObject.map(notesDtoObject, notesDataObject);
		notesRepositoryObject.save(notesDataObject);

		return new ResponseEntity<String>("Added note successfully", HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteNote(Long id) {

		notesRepositoryObject.deleteById(id);
		return new ResponseEntity<String>("Deleted note successfully", HttpStatus.OK);
	}

	@Override
	public List<NotesData> showNotes() {
		
		return notesRepositoryObject.findAll();
	}

	@Override
	public ResponseEntity<String> updateNote(NotesDto notesDtoObject, Long id) {
		try {
			notesRepositoryObject.setTitleDescription(notesDtoObject.getTitle(), notesDtoObject.getDescription(),
					id);
		} catch (Exception e) {
			return new ResponseEntity<String>("Updated note successfully", HttpStatus.OK);
		}
		return null;
	}

}
