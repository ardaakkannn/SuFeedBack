package com.ardakkan.backend.repo;



import com.ardakkan.backend.entity.Course;
import com.ardakkan.backend.entity.Faculty;
import com.ardakkan.backend.entity.Instructor;
import com.ardakkan.backend.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    // Belirli bir fakülteye bağlı dersleri getir
    List<Course> findByFaculty(Faculty faculty);

    // Belirli bir öğretim üyesinin verdiği dersleri getir
    List<Course> findByInstructor(Instructor instructor);

    // Belirli bir dönemde açılan dersleri getir
    List<Course> findBySemester(Semester semester);

    // Belirli bir isimle (title) eşleşen dersleri getir
    List<Course> findByTitleContainingIgnoreCase(String title);
}

