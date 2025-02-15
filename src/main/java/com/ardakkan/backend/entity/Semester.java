package com.ardakkan.backend.entity;



import jakarta.persistence.*;

@Entity
@Table(name = "Semester")
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name; // Örn: "Spring 2024", "Fall 2023"

    @Column(nullable = false)
    private int year; // Örn: 2024, 2023

    

    // Constructors
    public Semester() {}

    public Semester(String name, int year) {
        this.name = name;
        this.year = year;
        
    }

    // Getters & Setters
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    
}
