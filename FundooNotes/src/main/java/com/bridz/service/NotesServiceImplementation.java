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
	private ModelMapper modelMapper;

	// Creating Object of notes data entity
	@Autowired
	private NotesData notesDataEntity;

	@Autowired
	NotesDto notesDto;

	@Autowired
	List<NotesDto> listOfNotesDto;

	@Autowired
	private Environment environment;

	// notes repository object
	@Autowired
	private NotesRepository notesRepository;

	@Override
	public ResponseEntity<String> save(NotesDto notesDto) {

		// Using model mapper mapping dto object with user details entity
		modelMapper.map(notesDto, notesDataEntity);
		notesRepository.save(notesDataEntity);

		return new ResponseEntity<String>(environment.getProperty("status.success.note.add"), HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<String> delete(Long id) {

		notesRepository.deleteById(id);

		return new ResponseEntity<String>(environment.getProperty("status.success.note.delete"), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> show() {

		notesRepository.findAll().stream().forEach(notesDataObject -> {

			listOfNotesDto.add(modelMapper.map(notesDataObject, NotesDto.class));
		});

		return new ResponseEntity<Object>(listOfNotesDto, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<String> update(NotesDto notesDto, Long id) {

		modelMapper.map(notesDto, notesDataEntity);

		try {

			notesRepository.setTitleDescription(notesDataEntity.getTitle(), notesDataEntity.getDescription(), id);
		} catch (Exception e) {

			return new ResponseEntity<String>(environment.getProperty("status.success.note.update"),
					HttpStatus.OK);
		}

		throw new NotesException(Integer.parseInt(environment.getProperty("status.update.notes.errorCode")),
				environment.getProperty("status.update.notes.errorMessage"));
	}

	@Override
	public ResponseEntity<String> trashUntrash(long id) {

		Optional<NotesData> resultOfTrashOrUnTrash = notesRepository.findById(id);

		try {

			if (resultOfTrashOrUnTrash.get().isTrash()) {

				notesRepository.setTrash(false, id);
			} else {

				notesRepository.setTrash(true, id);
			}
		} catch (Exception e) {

			return new ResponseEntity<String>(environment.getProperty("status.success.note.trash")
					+ !resultOfTrashOrUnTrash.get().isTrash(), HttpStatus.OK);
		}

		throw new NotesException(Integer.parseInt(environment.getProperty("status.notes.trash.errorCode")),
				environment.getProperty("status.notes.trash.errorMessage"));
	}

	@Override
	public ResponseEntity<String> archiveUnArchive(long id) {

		Optional<NotesData> resultOfArchiveOrUnArchive = notesRepository.findById(id);

		try {

			if (resultOfArchiveOrUnArchive.get().isArchive()) {

				notesRepository.setArchive(false, id);
			} else {

				notesRepository.setArchive(true, id);
			}
		} catch (Exception e) {

			return new ResponseEntity<String>(environment.getProperty("status.success.note.archive")
					+ !resultOfArchiveOrUnArchive.get().isArchive(), HttpStatus.OK);
		}

		throw new NotesException(Integer.parseInt(environment.getProperty("status.notes.archive.errorCode")),
				environment.getProperty("status.notes.archive.errorMessage"));
	}

	@Override
	public ResponseEntity<String> pinedUnPined(long id) {

		Optional<NotesData> resultOfPinedOrUnPined = notesRepository.findById(id);

		try {

			if (resultOfPinedOrUnPined.get().isPined()) {

				notesRepository.setPined(false, id);
			} else {

				notesRepository.setPined(true, id);
			}
		} catch (Exception e) {

			return new ResponseEntity<String>(environment.getProperty("status.success.note.pined")
					+ !resultOfPinedOrUnPined.get().isPined(), HttpStatus.OK);
		}

		throw new NotesException(Integer.parseInt(environment.getProperty("status.notes.pined.errorCode")),
				environment.getProperty("status.notes.pined.errorMessage"));
	}

	@Override
	public ResponseEntity<Object> sortByTitle() {

		notesRepository.sortByTitle().stream().forEach(notesData -> {

			listOfNotesDto.add(modelMapper.map(notesData, NotesDto.class));
		});

		return new ResponseEntity<Object>(listOfNotesDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> sortByDescription() {

		notesRepository.sortByDescription().stream().forEach(notesData -> {

			listOfNotesDto.add(modelMapper.map(notesData, NotesDto.class));
		});

		return new ResponseEntity<Object>(listOfNotesDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> findByTitle(String title) {

		notesRepository.findByTitle(title).stream().forEach(notesData -> {

			listOfNotesDto.add(modelMapper.map(notesData, NotesDto.class));
		});

		return new ResponseEntity<Object>(listOfNotesDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> findByDescription(String description) {

		notesRepository.findByDescription(description).stream().forEach(notesData -> {

			listOfNotesDto.add(modelMapper.map(notesData, NotesDto.class));
		});

		return new ResponseEntity<Object>(listOfNotesDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> setReminder(ReminderDateTimeDto reminderDateTimeDto, long id) {

		LocalDateTime reminderDateTime = LocalDateTime.of(reminderDateTimeDto.getYear(),
				reminderDateTimeDto.getMonth(), reminderDateTimeDto.getDay(),
				reminderDateTimeDto.getHour(), reminderDateTimeDto.getMinute());

		// Inbuilt format
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

		// Format LocalDateTime
		String formattedDateTime = reminderDateTime.format(formatter);

		try {
			notesRepository.setReminder(formattedDateTime, id);
		} catch (Exception exception) {

			return new ResponseEntity<String>(environment.getProperty("status.success.note.reminder"),
					HttpStatus.OK);
		}

		throw new NotesException(Integer.parseInt(environment.getProperty("status.notes.reminder.set.errorCode")),
				environment.getProperty("status.notes.reminder.set.errorMessage"));
	}

	@Override
	public ResponseEntity<String> unsetReminder(long id) {
		try {
			notesRepository.unsetReminder(id);
		} catch (Exception exception) {
			return new ResponseEntity<String>(environment.getProperty("status.success.note.removedreminder"),
					HttpStatus.OK);
		}

		throw new NotesException(
				Integer.parseInt(environment.getProperty("status.notes.reminder.removed.errorCode")),
				environment.getProperty("status.notes.reminder.removed.errorMessage"));
	}

	@Override
	public ResponseEntity<String> resetReminder(ReminderDateTimeDto reminderDateTimeDto, long id) {

		LocalDateTime reminderDateTime = LocalDateTime.of(reminderDateTimeDto.getYear(),
				reminderDateTimeDto.getMonth(), reminderDateTimeDto.getDay(),
				reminderDateTimeDto.getHour(), reminderDateTimeDto.getMinute());

		// Inbuilt format
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

		// Format LocalDateTime
		String formattedDateTime = reminderDateTime.format(formatter);

		try {
			notesRepository.setReminder(formattedDateTime, id);
		} catch (Exception exception) {

			return new ResponseEntity<String>(environment.getProperty("status.success.note.reset"),
					HttpStatus.OK);
		}

		throw new NotesException(
				Integer.parseInt(environment.getProperty("status.notes.reminder.reset.errorCode")),
				environment.getProperty("status.notes.reminder.reset.errorMessage"));
	}

}
