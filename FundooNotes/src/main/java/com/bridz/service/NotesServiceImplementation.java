package com.bridz.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bridz.dto.NotesDto;
import com.bridz.dto.ReminderDateTimeDto;
import com.bridz.exception.NotesException;
import com.bridz.model.NotesData;
import com.bridz.repository.NotesRepository;

@Service
public class NotesServiceImplementation implements NotesService {

	// Creating Object of model mapper
	@Autowired
	private ModelMapper modelMapperObject;

	// Creating Object of notes data entity
	@Autowired
	private NotesData notesDataEntityObject;

	@Autowired
	NotesDto notesDtoObject;

	@Autowired
	List<NotesDto> listOfNotesDto;

	@Autowired
	private Environment environmentObject;

	// notes repository object
	@Autowired
	private NotesRepository notesRepositoryObject;

	@Override
	public ResponseEntity<String> saveNote(NotesDto notesDtoObject) {

		// Using model mapper mapping dto object with user details entity
		modelMapperObject.map(notesDtoObject, notesDataEntityObject);
		notesRepositoryObject.save(notesDataEntityObject);

		return new ResponseEntity<String>(environmentObject.getProperty("status.success.note.add"), HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteNote(Long id) {

		notesRepositoryObject.deleteById(id);

		return new ResponseEntity<String>(environmentObject.getProperty("status.success.note.delete"), HttpStatus.OK);
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

		modelMapperObject.map(notesDtoObject, notesDataEntityObject);

		try {

			notesRepositoryObject.setTitleDescription(notesDataEntityObject.getTitle(), notesDataEntityObject.getDescription(), id);
		} catch (Exception e) {

			return new ResponseEntity<String>(environmentObject.getProperty("status.success.note.update"),
					HttpStatus.OK);
		}

		throw new NotesException(Integer.parseInt(environmentObject.getProperty("status.update.notes.errorCode")),
				environmentObject.getProperty("status.update.notes.errorMessage"));
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

			return new ResponseEntity<String>(environmentObject.getProperty("status.success.note.trash")
					+ !resultOfTrashOrUnTrash.get().isTrash(), HttpStatus.OK);
		}

		throw new NotesException(Integer.parseInt(environmentObject.getProperty("status.notes.trash.errorCode")),
				environmentObject.getProperty("status.notes.trash.errorMessage"));
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

			return new ResponseEntity<String>(environmentObject.getProperty("status.success.note.archive")
					+ !resultOfArchiveOrUnArchive.get().isArchive(), HttpStatus.OK);
		}

		throw new NotesException(Integer.parseInt(environmentObject.getProperty("status.notes.archive.errorCode")),
				environmentObject.getProperty("status.notes.archive.errorMessage"));
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

			return new ResponseEntity<String>(environmentObject.getProperty("status.success.note.pined")
					+ !resultOfPinedOrUnPined.get().isPined(), HttpStatus.OK);
		}

		throw new NotesException(Integer.parseInt(environmentObject.getProperty("status.notes.pined.errorCode")),
				environmentObject.getProperty("status.notes.pined.errorMessage"));
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

			return new ResponseEntity<String>(environmentObject.getProperty("status.success.note.reminder"),
					HttpStatus.OK);
		}

		throw new NotesException(Integer.parseInt(environmentObject.getProperty("status.notes.reminder.set.errorCode")),
				environmentObject.getProperty("status.notes.reminder.set.errorMessage"));
	}

	@Override
	public ResponseEntity<String> unsetReminder(long id) {
		try {
			notesRepositoryObject.unsetReminder(id);
		} catch (Exception exception) {
			return new ResponseEntity<String>(environmentObject.getProperty("status.success.note.removedreminder"),
					HttpStatus.OK);
		}

		throw new NotesException(
				Integer.parseInt(environmentObject.getProperty("status.notes.reminder.removed.errorCode")),
				environmentObject.getProperty("status.notes.reminder.removed.errorMessage"));
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

			return new ResponseEntity<String>(environmentObject.getProperty("status.success.note.reset"),
					HttpStatus.OK);
		}

		throw new NotesException(
				Integer.parseInt(environmentObject.getProperty("status.notes.reminder.reset.errorCode")),
				environmentObject.getProperty("status.notes.reminder.reset.errorMessage"));
	}

}
