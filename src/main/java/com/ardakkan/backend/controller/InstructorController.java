package com.ardakkan.backend.controller;

import com.ardakkan.backend.entity.Instructor;
import com.ardakkan.backend.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    // ✅ Tüm öğretim üyelerini getir
    @GetMapping("/all")
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        return ResponseEntity.ok(instructorService.getAllInstructors());
    }

    // ✅ ID'ye göre öğretim üyesini getir
    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable Long id) {
        Optional<Instructor> instructor = instructorService.getInstructorById(id);
        return instructor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Yeni öğretim üyesi ekle veya güncelle
    @PostMapping("/add")
    public ResponseEntity<Instructor> addInstructor(@RequestBody Instructor instructor) {
        Instructor savedInstructor = instructorService.saveOrUpdateInstructor(instructor);
        return ResponseEntity.ok(savedInstructor);
    }

    // ✅ ID'ye göre öğretim üyesini sil
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Email'e göre öğretim üyesini getir
    @GetMapping("/search")
    public ResponseEntity<Instructor> getInstructorByEmail(@RequestParam String email) {
        Optional<Instructor> instructor = instructorService.getInstructorByEmail(email);
        return instructor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

