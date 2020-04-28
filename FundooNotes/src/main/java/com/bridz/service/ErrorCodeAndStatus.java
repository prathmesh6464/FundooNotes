package com.bridz.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import org.springframework.stereotype.Component;

@Component
public class ErrorCodeAndStatus {

	public String getProperty(String propertyKey) {

		Properties propertyObject = new Properties();
		try {
			InputStream inputFile = new FileInputStream(
					"C:\\Users\\King\\Documents\\workspace-spring-tool-suite-4-4.6.0.RELEASE\\"
							+ "FundooNotes\\src\\main\\resources\\ErrorCodeErrorMessage.properties");
			propertyObject.load(inputFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return String.valueOf(propertyObject.getProperty(propertyKey));
	}

}