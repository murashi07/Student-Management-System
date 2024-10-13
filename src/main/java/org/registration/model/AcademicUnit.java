package org.registration.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class AcademicUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)  // Store the enum as a String in the database
    private EAcademicUnit type;   // New field for academic unit type

    @OneToMany(mappedBy = "academicUnit")
    private Set<Student> students;

    @ManyToOne
    @JoinColumn(name = "parent_unit_id")
    private AcademicUnit parent;

    @OneToMany(mappedBy = "parent")
    private Set<AcademicUnit> children;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EAcademicUnit getType() {
        return type;
    }

    public void setType(EAcademicUnit type) {
        this.type = type;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public AcademicUnit getParent() {
        return parent;
    }

    public void setParent(AcademicUnit parent) {
        this.parent = parent;
    }

    public Set<AcademicUnit> getChildren() {
        return children;
    }

    public void setChildren(Set<AcademicUnit> children) {
        this.children = children;
    }
}
