package com.bridz.configuration;

import java.util.ArrayList;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import com.bridz.dto.LabelDto;
import com.bridz.dto.NotesDto;
import com.bridz.model.LabelData;
import com.bridz.model.NotesData;
import com.bridz.model.UserDetails;

@Configuration
@PropertySources({ @PropertySource("classpath:ErrorCodeErrorMessage.properties"),
@PropertySource("classpath:RabbitMq.properties") })
public class ErrorCodeAndStatusConfiguration {

	@Bean
	public NotesData getNotesData() {

		return new NotesData();
	}

	@Bean
	public ArrayList<NotesDto> getListOfNotesDto() {

		return new ArrayList<NotesDto>();
	}

	@Bean
	public ArrayList<LabelDto> getListOfLabelDto() {

		return new ArrayList<LabelDto>();
	}

	@Bean
	public LabelData getLableData() {

		return new LabelData();
	}

	@Bean
	public NotesDto getNotesDto() {

		return new NotesDto();
	}

	@Bean
	public UserDetails getUserDetails() {

		return new UserDetails();
	}

	@Bean
	public ModelMapper getModelMapperObject() {

		return new ModelMapper();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer property() {

		return new PropertySourcesPlaceholderConfigurer();
	}

}