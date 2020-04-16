package com.bridz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bridz.model.Notes;

public interface NotesRepository extends JpaRepository<Notes, Integer> {

}
