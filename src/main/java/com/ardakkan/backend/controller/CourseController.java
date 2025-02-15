package com.ardakkan.backend.controller;

import com.ardakkan.backend.entity.Course;
import com.ardakkan.backend.entity.Faculty;
import com.ardakkan.backend.entity.Instructor;
import com.ardakkan.backend.entity.Semester;
import com.ardakkan.backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // ✅ Tüm dersleri getir
    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    // ✅ Belirli bir ID'ye sahip dersi getir
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Yeni ders ekle veya güncelle
    @PostMapping("/add")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course savedCourse = courseService.saveOrUpdateCourse(course);
        return ResponseEntity.ok(savedCourse);
    }

    // ✅ ID'ye göre ders sil
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Belirli bir fakülteye ait dersleri getir
    @GetMapping("/faculty/{facultyId}")
    public ResponseEntity<List<Course>> getCoursesByFaculty(@PathVariable Long facultyId) {
        Faculty faculty = new Faculty();
        faculty.setId(facultyId);
        return ResponseEntity.ok(courseService.getCoursesByFaculty(faculty));
    }

    // ✅ Belirli bir eğitmenin verdiği dersleri getir
    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<Course>> getCoursesByInstructor(@PathVariable Long instructorId) {
        Instructor instructor = new Instructor();
        instructor.setIdInstructor(instructorId);
        return ResponseEntity.ok(courseService.getCoursesByInstructor(instructor));
    }

    // ✅ Belirli bir döneme ait dersleri getir
    @GetMapping("/semester/{semesterId}")
    public ResponseEntity<List<Course>> getCoursesBySemester(@PathVariable Long semesterId) {
        Semester semester = new Semester();
        semester.setId(semesterId);
        return ResponseEntity.ok(courseService.getCoursesBySemester(semester));
    }

    // ✅ Ders adına göre arama yap
    @GetMapping("/search")
    public ResponseEntity<List<Course>> searchCoursesByTitle(@RequestParam String title) {
        return ResponseEntity.ok(courseService.searchCoursesByTitle(title));
    }
}

