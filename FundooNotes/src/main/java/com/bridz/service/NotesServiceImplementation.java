package com.bridz.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bridz.dto.NotesDto;
import com.bridz.dto.ReminderDateTimeDto;
import com.bridz.exception.JmsException;
import com.bridz.exception.NotesException;
import com.bridz.model.NotesData;
import com.bridz.repository.NotesRepository;

@Service
public class NotesServiceImplementation implements NotesService {

	// Creating Object of model mapper
	private ModelMapper modelMapperObject = new ModelMapper();

	// Creating Object of notes data entity
	private NotesData notesDataObject = new NotesData();

	NotesDto notesDtoObject = new NotesDto();

	List<NotesDto> listOfNotesDto = new ArrayList<NotesDto>();

	@Autowired
	ErrorCodeAndStatus errorCodeAndStatusObject;

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
	public ResponseEntity<Object> showNotes() {

		notesRepositoryObject.findAll().stream().forEach(notesDataObject -> {

			listOfNotesDto.add(modelMapperObject.map(notesDataObject, NotesDto.class));
		});

		return new ResponseEntity<Object>(listOfNotesDto, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<String> updateNote(NotesDto notesDtoObject, Long id) {

		modelMapperObject.map(notesDtoObject, notesDataObject);

		try {

			notesRepositoryObject.setTitleDescription(notesDataObject.getTitle(), notesDataObject.getDescription(), id);
		} catch (Exception e) {

			return new ResponseEntity<String>("Updated note successfully", HttpStatus.OK);
		}

		throw new NotesException(
				Integer.parseInt(errorCodeAndStatusObject.getProperty("status.update.notes.errorCode")),
				errorCodeAndStatusObject.getProperty("status.update.notes.errorMessage"));
	}

	@Override
	public ResponseEntity<String> trashUntrash(long id) {

		Optional<NotesData> resultOfTrashOrUnTrash = notesRepositoryObject.findById(id);

		try {

			if (resultOfTrashOrUnTrash.get().isTrash()) {

				notesRepositoryObject.setTrash(false, id);
			} else {

				notesRepositoryObject.setTrash(true, id);
			}
		} catch (Exception e) {

			return new ResponseEntity<String>("Value set for trash : " + !resultOfTrashOrUnTrash.get().isTrash(),
					HttpStatus.OK);
		}

		throw new NotesException(Integer.parseInt(errorCodeAndStatusObject.getProperty("status.notes.trash.errorCode")),
				errorCodeAndStatusObject.getProperty("status.notes.trash.errorMessage"));
	}

	@Override
	public ResponseEntity<String> archiveUnArchive(long id) {

		Optional<NotesData> resultOfArchiveOrUnArchive = notesRepositoryObject.findById(id);

		try {

			if (resultOfArchiveOrUnArchive.get().isArchive()) {

				notesRepositoryObject.setArchive(false, id);
			} else {

				notesRepositoryObject.setArchive(true, id);
			}
		} catch (Exception e) {

			return new ResponseEntity<String>(
					"Value set for archive : " + !resultOfArchiveOrUnArchive.get().isArchive(), HttpStatus.OK);
		}

		throw new NotesException(
				Integer.parseInt(errorCodeAndStatusObject.getProperty("status.notes.archive.errorCode")),
				errorCodeAndStatusObject.getProperty("status.notes.archive.errorMessage"));
	}

	@Override
	public ResponseEntity<String> pinedUnPined(long id) {

		Optional<NotesData> resultOfPinedOrUnPined = notesRepositoryObject.findById(id);

		try {

			if (resultOfPinedOrUnPined.get().isPined()) {

				notesRepositoryObject.setPined(false, id);
			} else {

				notesRepositoryObject.setPined(true, id);
			}
		} catch (Exception e) {

			return new ResponseEntity<String>("Value set for pined : " + !resultOfPinedOrUnPined.get().isPined(),
					HttpStatus.OK);
		}

		throw new NotesException(Integer.parseInt(errorCodeAndStatusObject.getProperty("status.notes.pined.errorCode")),
				errorCodeAndStatusObject.getProperty("status.notes.pined.errorMessage"));
	}

	@Override
	public ResponseEntity<Object> sortByTitle() {

		notesRepositoryObject.sortByTitle().stream().forEach(notesDataObject -> {

			listOfNotesDto.add(modelMapperObject.map(notesDataObject, NotesDto.class));
		});

		return new ResponseEntity<Object>(listOfNotesDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> sortByDescription() {

		notesRepositoryObject.sortByDescription().stream().forEach(notesDataObject -> {

			listOfNotesDto.add(modelMapperObject.map(notesDataObject, NotesDto.class));
		});

		return new ResponseEntity<Object>(listOfNotesDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> findByTitle(String title) {

		notesRepositoryObject.findByTitle(title).stream().forEach(notesDataObject -> {

			listOfNotesDto.add(modelMapperObject.map(notesDataObject, NotesDto.class));
		});

		return new ResponseEntity<Object>(listOfNotesDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> findByDescription(String description) {

		notesRepositoryObject.findByDescription(description).stream().forEach(notesDataObject -> {

			listOfNotesDto.add(modelMapperObject.map(notesDataObject, NotesDto.class));
		});

		return new ResponseEntity<Object>(listOfNotesDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> setReminder(ReminderDateTimeDto reminderDateTimeDtoObject, long id) {

		LocalDateTime reminderDateTime = LocalDateTime.of(reminderDateTimeDtoObject.getYear(),
				reminderDateTimeDtoObject.getMonth(), reminderDateTimeDtoObject.getDay(),
				reminderDateTimeDtoObject.getHour(), reminderDateTimeDtoObject.getMinute());

		// Inbuilt format
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

		// Format LocalDateTime
		String formattedDateTime = reminderDateTime.format(formatter);

		try {
			notesRepositoryObject.setReminder(formattedDateTime, id);
		} catch (Exception exception) {

			return new ResponseEntity<String>("Reminder set successfully", HttpStatus.OK);
		}

		throw new NotesException(
				Integer.parseInt(errorCodeAndStatusObject.getProperty("status.notes.reminder.set.errorCode")),
				errorCodeAndStatusObject.getProperty("status.notes.reminder.set.errorMessage"));
	}

	@Override
	public ResponseEntity<String> unsetReminder(long id) {
		try {
			notesRepositoryObject.unsetReminder(id);
		} catch (Exception exception) {
			return new ResponseEntity<String>("Reminder removed successfully", HttpStatus.OK);
		}

		throw new NotesException(
				Integer.parseInt(errorCodeAndStatusObject.getProperty("status.notes.reminder.removed.errorCode")),
				errorCodeAndStatusObject.getProperty("status.notes.reminder.removed.errorMessage"));
	}

	@Override
	public ResponseEntity<String> resetReminder(ReminderDateTimeDto reminderDateTimeDtoObject, long id) {

		LocalDateTime reminderDateTime = LocalDateTime.of(reminderDateTimeDtoObject.getYear(),
				reminderDateTimeDtoObject.getMonth(), reminderDateTimeDtoObject.getDay(),
				reminderDateTimeDtoObject.getHour(), reminderDateTimeDtoObject.getMinute());

		// Inbuilt format
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

		// Format LocalDateTime
		String formattedDateTime = reminderDateTime.format(formatter);

		try {
			notesRepositoryObject.setReminder(formattedDateTime, id);
		} catch (Exception exception) {

			return new ResponseEntity<String>("Reminder reset successfully", HttpStatus.OK);
		}

		throw new NotesException(
				Integer.parseInt(errorCodeAndStatusObject.getProperty("status.notes.reminder.reset.errorCode")),
				errorCodeAndStatusObject.getProperty("status.notes.reminder.reset.errorMessage"));
	}

}
