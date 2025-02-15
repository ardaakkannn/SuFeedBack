package com.ardakkan.backend.entity;



import jakarta.persistence.*;

@Entity
@Table(name = "Faculty")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name; // Fakülte adı

    @Column(length = 255)
    private String description; // Fakülte açıklaması

    @Column(nullable = false, length = 100)
    private String deanName; // Fakülte Dekanı

    @Column(length = 100)
    private String contactEmail; // Fakülte iletişim maili

    @Column(length = 100)
    private String websiteUrl; // Fakülte web sitesi

    // Constructors
    public Faculty() {}

    public Faculty(String name, String description, String deanName, String contactEmail, String websiteUrl) {
        this.name = name;
        this.description = description;
        this.deanName = deanName;
        this.contactEmail = contactEmail;
        this.websiteUrl = websiteUrl;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long idFaculty) {
        this.id = idFaculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeanName() {
        return deanName;
    }

    public void setDeanName(String deanName) {
        this.deanName = deanName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }
}

