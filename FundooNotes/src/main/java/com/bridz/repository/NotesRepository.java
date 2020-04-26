package com.bridz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bridz.model.NotesData;

@Repository
public interface NotesRepository extends JpaRepository<NotesData, Long> {
 
	void deleteByTitle(String title);

	@Query(value = "UPDATE notes_data SET title = ?, description = ? where id = ?", nativeQuery = true)
	void setTitleDescription(String editeTitle, String editeDescription, Long id);

}
