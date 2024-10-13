package org.registration.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
@Entity
public class Teacher {

    @Id

    private String code;

    private String names;

    private String qualification;

    @ManyToMany
    @JoinTable(
        name = "course_teacher",
        joinColumns = @JoinColumn(name = "teacher_code"),
        inverseJoinColumns = @JoinColumn(name = "course_code")
    )
    private Set<Course> courses = new HashSet<>();

    
}

