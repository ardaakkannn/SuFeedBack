package com.ardakkan.backend.service;


import com.ardakkan.backend.entity.Semester;
import com.ardakkan.backend.repo.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;

    // ✅ Tüm dönemleri getir
    public List<Semester> getAllSemesters() {
        return semesterRepository.findAll();
    }

    // ✅ ID'ye göre dönem getir
    public Optional<Semester> getSemesterById(Long id) {
        return semesterRepository.findById(id);
    }

    // ✅ Yeni dönem ekle veya güncelle
    public Semester saveOrUpdateSemester(Semester semester) {
        return semesterRepository.save(semester);
    }

    // ✅ ID'ye göre dönemi sil
    public void deleteSemester(Long id) {
        semesterRepository.deleteById(id);
    }

    // ✅ Yıla göre dönemleri getir
    public List<Semester> getSemestersByYear(int year) {
        return semesterRepository.findByYear(year);
    }
}

