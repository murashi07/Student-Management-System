package org.registration.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class CourseDefinition {

    @Id
    private String code;

    private String name;

    private String description;

    
    
}
