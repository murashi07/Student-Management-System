package org.registration.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
public class StudentRegistration {

    @Id
    private String studentId;

    private LocalDate registrationDate;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "regNo")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

    
    
}
