package org.registration.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CourseDefinition {

    @Id
    private String code;

    private String name;

    private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    // Getters and setters
    
    
}
