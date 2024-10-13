package org.registration.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
@Entity
public class Course {

    @Id
    private String code;

    private String name;

    @ManyToOne
    @JoinColumn(name = "definition_id")
    private CourseDefinition definition;

    @ManyToMany(mappedBy = "courses")
    private Set<Teacher> teachers = new HashSet<>();

    @OneToMany(mappedBy = "course")
    private Set<StudentCourse> studentCourses = new HashSet<>();

}
