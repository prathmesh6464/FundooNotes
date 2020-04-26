package com.bridz.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.bridz.dto.LabelDto;
import com.bridz.model.LabelData;

public interface LabelService {

	ResponseEntity<String> addLabel(LabelDto labelDtoObject);

	ResponseEntity<String> editeLabel(LabelDto labelDtoObject, long id);

	List<LabelData> showLabel(LabelDto labelDtoObject);

	ResponseEntity<String> deleteLabel(long id);

}
