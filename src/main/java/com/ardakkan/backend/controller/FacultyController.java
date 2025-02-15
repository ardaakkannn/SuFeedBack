package com.ardakkan.backend.controller;


import com.ardakkan.backend.entity.Faculty;
import com.ardakkan.backend.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/faculties")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    // ✅ Tüm fakülteleri getir
    @GetMapping("/all")
    public ResponseEntity<List<Faculty>> getAllFaculties() {
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    // ✅ ID’ye göre fakülte getir
    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long id) {
        Optional<Faculty> faculty = facultyService.getFacultyById(id);
        return faculty.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Yeni fakülte ekle veya güncelle
    @PostMapping("/add")
    public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty faculty) {
        Faculty savedFaculty = facultyService.saveOrUpdateFaculty(faculty);
        return ResponseEntity.ok(savedFaculty);
    }

    // ✅ ID’ye göre fakülte sil
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Fakülte ismine göre fakülte getir
    @GetMapping("/search")
    public ResponseEntity<Faculty> getFacultyByName(@RequestParam String name) {
        Optional<Faculty> faculty = facultyService.getFacultyByName(name);
        return faculty.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

