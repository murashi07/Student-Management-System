package org.registration.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class AcademicUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "academicUnit")
    private Set<Student> students;

    @ManyToOne
    @JoinColumn(name = "parent_unit_id")
    private AcademicUnit parent;

    @OneToMany(mappedBy = "parent")
    private Set<AcademicUnit> children;

}
