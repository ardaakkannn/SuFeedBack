package com.ardakkan.backend.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    
    @ManyToOne
    @JoinColumn(name = "facultyId")
    private Faculty faculty;

    private String description;
    private String crn;

    @ManyToOne
    @JoinColumn(name = "semesterId")
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "instructosId")
    private List<Instructor> instructors;


    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Review> reviews;


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long idCourse) {
        this.id = idCourse;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

   
    public List<Instructor> getInstructors() {
		return instructors;
	}

	public void setInstructors(List<Instructor> instructors) {
		this.instructors = instructors;
	}

	public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

	public String getCrn() {
		return crn;
	}

	public void setCrn(String crn) {
		this.crn = crn;
	}


}

