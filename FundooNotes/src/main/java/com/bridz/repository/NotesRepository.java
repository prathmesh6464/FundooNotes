package com.bridz.repository;

import com.bridz.model.NotesData;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<NotesData, Long> {

	void deleteByTitle(String title);

	List<NotesData> findByTitle(String title);

	List<NotesData> findByDescription(String description);

	List<NotesData> findByOrderByTitleAsc();

	List<NotesData> findByOrderByDescriptionAsc();

}
