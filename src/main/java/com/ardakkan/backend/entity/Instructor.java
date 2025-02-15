package com.ardakkan.backend.entity;


import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 255)
    private String bio; // Kısa biyografi

    @Column(length = 100, unique = true)
    private String email;

    @Column(length = 100)
    private String department; // Örn: "Computer Science"

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    private List<Course> courses; // Öğrettiği dersler

    // Constructors
    public Instructor() {}

    public Instructor(String name, String bio, String email, String department) {
        this.name = name;
        this.bio = bio;
        this.email = email;
        this.department = department;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setIdInstructor(Long idInstructor) {
        this.id = idInstructor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}

