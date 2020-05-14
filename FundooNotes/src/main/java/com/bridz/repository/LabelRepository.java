package com.bridz.repository;

import com.bridz.model.LabelData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<LabelData, Long> {

}
