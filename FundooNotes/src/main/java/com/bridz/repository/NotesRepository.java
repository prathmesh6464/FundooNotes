package com.bridz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bridz.model.NotesData;

public interface NotesRepository extends JpaRepository<NotesData, Integer> {

}
