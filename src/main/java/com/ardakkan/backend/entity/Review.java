package com.ardakkan.backend.entity;



import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "courseId", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private int contentRating;
    private int teachingRating;
    private int gradingRating;
    private int workLoadRating;
    
    @Column(length = 1000)
    private String comment;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // Yorumu yapan tarih

    @Column(nullable = true)
    private LocalDateTime updatedAt;

    // Constructors
    public Review() {}

    public Review(Course course, User user, int contentRating, int teachingRating, int gradingRating, int workLoadRating, String comment) {
        this.course = course;
        this.user = user;
        this.contentRating = contentRating;
        this.teachingRating = teachingRating;
        this.gradingRating = gradingRating;
        this.workLoadRating = workLoadRating;
        this.comment = comment;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getContentRating() {
        return contentRating;
    }

    public void setContentRating(int contentRating) {
        this.contentRating = contentRating;
    }

    public int getTeachingRating() {
        return teachingRating;
    }

    public void setTeachingRating(int teachingRating) {
        this.teachingRating = teachingRating;
    }

    public int getGradingRating() {
        return gradingRating;
    }

    public void setGradingRating(int gradingRating) {
        this.gradingRating = gradingRating;
    }

    public int getWorkLoadRating() {
        return workLoadRating;
    }

    public void setWorkLoadRating(int workLoadRating) {
        this.workLoadRating = workLoadRating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

