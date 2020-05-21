package com.bridz.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Optional;

import com.bridz.exception.LabelException;
import com.bridz.exception.NotesException;
import com.bridz.repository.NotesRepository;
import com.bridz.dto.JwtResponseToken;
import com.bridz.dto.NotesDto;
import com.bridz.dto.ReminderDateTimeDto;

import com.bridz.model.NotesData;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotesServiceImplementation implements NotesService {

	// Creating Object of model mapper
	@Autowired
	private ModelMapper modelMapper;

	// Creating Object of notes data entity
	@Autowired
	private NotesData entity;

	@Autowired
	NotesDto notesDto;

	@Autowired
	List<NotesDto> listOfNotesDto;

	@Autowired
	private Environment environment;

	@Autowired
	UserService userService;

	// notes repository object
	@Autowired
	private NotesRepository repository;

	@Override
	public ResponseEntity<String> save(NotesDto notesDto, JwtResponseToken token) {

		if (!token.getJwtToken().equals(userService.getJwtResponseToken())) {

			throw new LabelException(Integer.parseInt(environment.getProperty("status.user.loginErrorCode")),
					environment.getProperty("status.user.loginErrorMessage"));
		}

		// Using model mapper mapping dto object with user details entity
		modelMapper.map(notesDto, entity);
		repository.save(entity);

		return new ResponseEntity<String>(environment.getProperty("status.success.note.add"), HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<String> delete(Long id, JwtResponseToken token) {

		if (!token.getJwtToken().equals(userService.getJwtResponseToken())) {

			throw new LabelException(Integer.parseInt(environment.getProperty("status.user.loginErrorCode")),
					environment.getProperty("status.user.loginErrorMessage"));
		}

		repository.deleteById(id);

		return new ResponseEntity<String>(environment.getProperty("status.success.note.delete"), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> show(JwtResponseToken token) {

		if (!token.getJwtToken().equals(userService.getJwtResponseToken())) {

			throw new LabelException(Integer.parseInt(environment.getProperty("status.user.loginErrorCode")),
					environment.getProperty("status.user.loginErrorMessage"));
		}
		
		listOfNotesDto.clear();
		
		repository.findAll().stream().forEach(notesDataObject -> {

			listOfNotesDto.add(modelMapper.map(notesDataObject, NotesDto.class));
		});

		modelMapper.map(repository.findAll().toArray(), listOfNotesDto);

		return new ResponseEntity<Object>(listOfNotesDto, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<String> update(NotesDto notesDto, Long id, JwtResponseToken token) {

		if (!token.getJwtToken().equals(userService.getJwtResponseToken())) {

			throw new LabelException(Integer.parseInt(environment.getProperty("status.user.loginErrorCode")),
					environment.getProperty("status.user.loginErrorMessage"));
		}

		modelMapper.map(notesDto, entity);
		Optional<NotesData> notesData = repository.findById(id);

		if (notesData.isPresent()) {

			modelMapper.map(entity, notesData.get());
		} else {

			throw new NotesException(Integer.parseInt(environment.getProperty("status.update.notes.errorCode")),
					environment.getProperty("status.update.notes.errorMessage"));
		}

		repository.save(notesData.get());

		return new ResponseEntity<String>(environment.getProperty("status.success.note.update"), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<String> trashUntrash(long id, JwtResponseToken token) {

		if (!token.getJwtToken().equals(userService.getJwtResponseToken())) {

			throw new LabelException(Integer.parseInt(environment.getProperty("status.user.loginErrorCode")),
					environment.getProperty("status.user.loginErrorMessage"));
		}

		Optional<NotesData> resultOfTrashOrUnTrash = repository.findById(id);

		switch (resultOfTrashOrUnTrash.get().isTrash() ? "true" : "false") {

		case "true":
			resultOfTrashOrUnTrash.get().setTrash(false);
			repository.save(resultOfTrashOrUnTrash.get());
			return new ResponseEntity<String>(
					environment.getProperty("status.success.note.trash") + resultOfTrashOrUnTrash.get().isTrash(),
					HttpStatus.OK);

		case "false":
			resultOfTrashOrUnTrash.get().setTrash(true);
			repository.save(resultOfTrashOrUnTrash.get());
			return new ResponseEntity<String>(
					environment.getProperty("status.success.note.trash") + resultOfTrashOrUnTrash.get().isTrash(),
					HttpStatus.OK);
		default:
			throw new NotesException(Integer.parseInt(environment.getProperty("status.notes.trash.errorCode")),
					environment.getProperty("status.notes.trash.errorMessage"));
		}

	}

	@Override
	public ResponseEntity<String> archiveUnArchive(long id, JwtResponseToken token) {

		if (!token.getJwtToken().equals(userService.getJwtResponseToken())) {

			throw new LabelException(Integer.parseInt(environment.getProperty("status.user.loginErrorCode")),
					environment.getProperty("status.user.loginErrorMessage"));
		}

		Optional<NotesData> resultOfArchiveOrUnArchive = repository.findById(id);

		switch (resultOfArchiveOrUnArchive.get().isArchive() ? "true" : "false") {

		case "true":
			resultOfArchiveOrUnArchive.get().setArchive(false);
			repository.save(resultOfArchiveOrUnArchive.get());
			return new ResponseEntity<String>(environment.getProperty("status.success.note.archive")
					+ resultOfArchiveOrUnArchive.get().isArchive(), HttpStatus.OK);

		case "false":
			resultOfArchiveOrUnArchive.get().setArchive(true);
			repository.save(resultOfArchiveOrUnArchive.get());
			return new ResponseEntity<String>(environment.getProperty("status.success.note.archive")
					+ resultOfArchiveOrUnArchive.get().isArchive(), HttpStatus.OK);
		default:
			throw new NotesException(Integer.parseInt(environment.getProperty("status.notes.archive.errorCode")),
					environment.getProperty("status.notes.archive.errorMessage"));
		}

	}

	@Override
	public ResponseEntity<String> pinedUnPined(long id, JwtResponseToken token) {

		if (!token.getJwtToken().equals(userService.getJwtResponseToken())) {

			throw new LabelException(Integer.parseInt(environment.getProperty("status.user.loginErrorCode")),
					environment.getProperty("status.user.loginErrorMessage"));
		}

		Optional<NotesData> resultOfPinedOrUnPined = repository.findById(id);

		switch (resultOfPinedOrUnPined.get().isPined() ? "true" : "false") {

		case "true":
			resultOfPinedOrUnPined.get().setPined(false);
			repository.save(resultOfPinedOrUnPined.get());
			return new ResponseEntity<String>(
					environment.getProperty("status.success.note.pined") + resultOfPinedOrUnPined.get().isPined(),
					HttpStatus.OK);

		case "false":
			resultOfPinedOrUnPined.get().setPined(true);
			repository.save(resultOfPinedOrUnPined.get());
			return new ResponseEntity<String>(
					environment.getProperty("status.success.note.pined") + resultOfPinedOrUnPined.get().isPined(),
					HttpStatus.OK);
		default:
			throw new NotesException(Integer.parseInt(environment.getProperty("status.notes.pined.errorCode")),
					environment.getProperty("status.notes.pined.errorMessage"));
		}

	}

	@Override
	public ResponseEntity<Object> sortByTitle(JwtResponseToken token) {

		if (!token.getJwtToken().equals(userService.getJwtResponseToken())) {

			throw new LabelException(Integer.parseInt(environment.getProperty("status.user.loginErrorCode")),
					environment.getProperty("status.user.loginErrorMessage"));
		}

		repository.findByOrderByTitleAsc().stream().forEach(notesData -> {

			listOfNotesDto.add(modelMapper.map(notesData, NotesDto.class));
		});

		return new ResponseEntity<Object>(listOfNotesDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> sortByDescription(JwtResponseToken token) {

		if (!token.getJwtToken().equals(userService.getJwtResponseToken())) {

			throw new LabelException(Integer.parseInt(environment.getProperty("status.user.loginErrorCode")),
					environment.getProperty("status.user.loginErrorMessage"));
		}

		repository.findByOrderByDescriptionAsc().stream().forEach(notesData -> {

			listOfNotesDto.add(modelMapper.map(notesData, NotesDto.class));
		});

		return new ResponseEntity<Object>(listOfNotesDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> findByTitle(String title, JwtResponseToken token) {

		if (!token.getJwtToken().equals(userService.getJwtResponseToken())) {

			throw new LabelException(Integer.parseInt(environment.getProperty("status.user.loginErrorCode")),
					environment.getProperty("status.user.loginErrorMessage"));
		}

		repository.findByTitle(title).stream().forEach(notesData -> {

			listOfNotesDto.add(modelMapper.map(notesData, NotesDto.class));
		});

		return new ResponseEntity<Object>(listOfNotesDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> findByDescription(String description, JwtResponseToken token) {

		if (!token.getJwtToken().equals(userService.getJwtResponseToken())) {

			throw new LabelException(Integer.parseInt(environment.getProperty("status.user.loginErrorCode")),
					environment.getProperty("status.user.loginErrorMessage"));
		}

		repository.findByDescription(description).stream().forEach(notesData -> {

			listOfNotesDto.add(modelMapper.map(notesData, NotesDto.class));
		});

		return new ResponseEntity<Object>(listOfNotesDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> setReminder(ReminderDateTimeDto reminderDateTimeDto, long id,
			JwtResponseToken token) {

		LocalDateTime reminderDateTime = LocalDateTime.of(reminderDateTimeDto.getYear(), reminderDateTimeDto.getMonth(),
				reminderDateTimeDto.getDay(), reminderDateTimeDto.getHour(), reminderDateTimeDto.getMinute());

		if (!token.getJwtToken().equals(userService.getJwtResponseToken())) {

			throw new LabelException(Integer.parseInt(environment.getProperty("status.user.loginErrorCode")),
					environment.getProperty("status.user.loginErrorMessage"));
		}

		// Inbuilt format
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

		// Format LocalDateTime
		String formattedDateTime = reminderDateTime.format(formatter);

		Optional<NotesData> notesData = repository.findById(id);

		if (notesData.isPresent()) {

			notesData.get().setReminderDateTime(formattedDateTime);
		} else {

			throw new NotesException(Integer.parseInt(environment.getProperty("status.notes.reminder.set.errorCode")),
					environment.getProperty("status.notes.reminder.set.errorMessage"));
		}

		repository.save(notesData.get());

		return new ResponseEntity<String>(environment.getProperty("status.success.note.reminder"), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<String> unsetReminder(long id, JwtResponseToken token) {

		if (!token.getJwtToken().equals(userService.getJwtResponseToken())) {

			throw new LabelException(Integer.parseInt(environment.getProperty("status.user.loginErrorCode")),
					environment.getProperty("status.user.loginErrorMessage"));
		}

		Optional<NotesData> notesData = repository.findById(id);

		if (notesData.isPresent()) {

			notesData.get().setReminderDateTime(null);
		} else {

			throw new NotesException(
					Integer.parseInt(environment.getProperty("status.notes.reminder.removed.errorCode")),
					environment.getProperty("status.notes.reminder.removed.errorMessage"));
		}

		repository.save(notesData.get());

		return new ResponseEntity<String>(environment.getProperty("status.success.note.removedreminder"),
				HttpStatus.OK);

	}

	@Override
	public ResponseEntity<String> resetReminder(ReminderDateTimeDto reminderDateTimeDto, long id,
			JwtResponseToken token) {

		LocalDateTime reminderDateTime = LocalDateTime.of(reminderDateTimeDto.getYear(), reminderDateTimeDto.getMonth(),
				reminderDateTimeDto.getDay(), reminderDateTimeDto.getHour(), reminderDateTimeDto.getMinute());

		if (!token.getJwtToken().equals(userService.getJwtResponseToken())) {

			throw new LabelException(Integer.parseInt(environment.getProperty("status.user.loginErrorCode")),
					environment.getProperty("status.user.loginErrorMessage"));
		}

		// Inbuilt format
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

		// Format LocalDateTime
		String formattedDateTime = reminderDateTime.format(formatter);

		Optional<NotesData> notesData = repository.findById(id);

		if (notesData.isPresent()) {

			notesData.get().setReminderDateTime(formattedDateTime);
		} else {

			throw new NotesException(Integer.parseInt(environment.getProperty("status.notes.reminder.reset.errorCode")),
					environment.getProperty("status.notes.reminder.reset.errorMessage"));
		}

		repository.save(notesData.get());

		return new ResponseEntity<String>(environment.getProperty("status.success.note.reset"), HttpStatus.OK);

	}

}
