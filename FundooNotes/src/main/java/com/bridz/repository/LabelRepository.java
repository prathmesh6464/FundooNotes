package com.bridz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bridz.dto.LabelDto;
import com.bridz.model.LabelData;

@Repository
public interface LabelRepository extends JpaRepository<LabelData, Long> {

	void save(LabelDto labelDtoObject);

	@Query(value="UPDATE label_data SET label_message = ? where id = ?", nativeQuery = true)
	void setById(String editedlabelMessage, long id);

}
