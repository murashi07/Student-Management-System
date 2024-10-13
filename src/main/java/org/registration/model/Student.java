package org.registration.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {

    @Id
    private String regNo;

    private String firstName;

    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<StudentRegistration> registrations = new HashSet<>();

    // Add the missing ManyToOne relationship
    @ManyToOne
    @JoinColumn(name = "academic_unit_id")  // Specifies the foreign key column in the Student table
    private AcademicUnit academicUnit;

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<StudentRegistration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Set<StudentRegistration> registrations) {
        this.registrations = registrations;
    }

    public AcademicUnit getAcademicUnit() {
        return academicUnit;
    }

    public void setAcademicUnit(AcademicUnit academicUnit) {
        this.academicUnit = academicUnit;
    }
}
