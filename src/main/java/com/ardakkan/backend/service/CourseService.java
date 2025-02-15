package com.ardakkan.backend.service;



import com.ardakkan.backend.entity.Course;
import com.ardakkan.backend.entity.Faculty;
import com.ardakkan.backend.entity.Instructor;
import com.ardakkan.backend.entity.Semester;
import com.ardakkan.backend.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // Tüm dersleri getir
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // ID'ye göre dersi getir
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    // Yeni ders ekle veya güncelle
    public Course saveOrUpdateCourse(Course course) {
        return courseRepository.save(course);
    }

    // ID'ye göre ders sil
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    // Belirli bir fakülteye ait dersleri getir
    public List<Course> getCoursesByFaculty(Faculty faculty) {
        return courseRepository.findByFaculty(faculty);
    }

    // Belirli bir eğitmenin verdiği dersleri getir
    public List<Course> getCoursesByInstructor(Instructor instructor) {
        return courseRepository.findByInstructor(instructor);
    }

    // Belirli bir dönemde açılan dersleri getir
    public List<Course> getCoursesBySemester(Semester semester) {
        return courseRepository.findBySemester(semester);
    }

    // Ders adına göre arama yap
    public List<Course> searchCoursesByTitle(String title) {
        return courseRepository.findByTitleContainingIgnoreCase(title);
    }
}

