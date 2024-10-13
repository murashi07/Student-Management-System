package org.registration.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Data
@Entity
public class Semester {

    @Id
    private String id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL)
    private Set<StudentRegistration> registrations = new HashSet<>();

    
    
}
