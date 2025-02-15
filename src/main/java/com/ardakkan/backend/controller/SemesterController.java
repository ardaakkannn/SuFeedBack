package com.ardakkan.backend.controller;


import com.ardakkan.backend.entity.Semester;
import com.ardakkan.backend.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/semesters")
public class SemesterController {

    @Autowired
    private SemesterService semesterService;

    // ✅ Tüm dönemleri getir
    @GetMapping("/all")
    public ResponseEntity<List<Semester>> getAllSemesters() {
        return ResponseEntity.ok(semesterService.getAllSemesters());
    }

    // ✅ ID'ye göre dönemi getir
    @GetMapping("/{id}")
    public ResponseEntity<Semester> getSemesterById(@PathVariable Long id) {
        Optional<Semester> semester = semesterService.getSemesterById(id);
        return semester.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Yeni dönem ekle veya güncelle
    @PostMapping("/add")
    public ResponseEntity<Semester> addSemester(@RequestBody Semester semester) {
        Semester savedSemester = semesterService.saveOrUpdateSemester(semester);
        return ResponseEntity.ok(savedSemester);
    }

    // ✅ ID'ye göre dönemi sil
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSemester(@PathVariable Long id) {
        semesterService.deleteSemester(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Belirli bir yılın dönemlerini getir
    @GetMapping("/year/{year}")
    public ResponseEntity<List<Semester>> getSemestersByYear(@PathVariable int year) {
        return ResponseEntity.ok(semesterService.getSemestersByYear(year));
    }
}

