package com.bridz.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridz.model.NotesData;

@Repository
public interface NotesRepository extends JpaRepository<NotesData, Long> {

	void deleteByTitle(String title);

	@Query(value = "UPDATE notes_data SET title = ?, description = ? where id = ?", nativeQuery = true)
	void setTitleDescription(String editeTitle, String editeDescription, Long id);

	@Query(value = "UPDATE notes_data SET is_trash = ? where id = ?", nativeQuery = true)
	void setTrash(boolean setingTrashValue, long id);

	@Query(value = "UPDATE notes_data SET is_pined = ? where id = ?", nativeQuery = true)
	void setPined(boolean setingPinedValue, long id);

	@Query(value = "UPDATE notes_data SET is_archive = ? where id = ?", nativeQuery = true)
	void setArchive(boolean setingArchiveValue, long id);

	@Query(value = "SELECT * FROM notes_data ORDER BY title", nativeQuery = true)
	List<NotesData> sortByTitle();

	@Query(value = "SELECT * FROM notes_data ORDER BY description", nativeQuery = true)
	List<NotesData> sortByDescription();

	@Query(value = "SELECT * FROM notes_data WHERE title = ?", nativeQuery = true)
	List<NotesData> findByTitle(String title);

	@Query(value = "SELECT * FROM notes_data WHERE description = ?", nativeQuery = true)
	List<NotesData> findByDescription(String description);

	@Query(value = "UPDATE notes_data SET reminder_date_time = ? where id = ?", nativeQuery = true)
	void setReminder(String reminderDateTime, long id);

	@Query(value = "UPDATE notes_data SET reminder_date_time = null where id = ?", nativeQuery = true)
	void unsetReminder(long id);

}
