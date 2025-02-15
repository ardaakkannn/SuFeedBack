package com.ardakkan.backend.service;



import com.ardakkan.backend.entity.Faculty;
import com.ardakkan.backend.repo.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    // ✅ Tüm fakülteleri getir
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    // ✅ ID’ye göre fakülte getir
    public Optional<Faculty> getFacultyById(Long id) {
        return facultyRepository.findById(id);
    }

    // ✅ Yeni fakülte ekle veya güncelle
    public Faculty saveOrUpdateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    // ✅ ID’ye göre fakülte sil
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    // ✅ Fakülte ismine göre fakülte getir
    public Optional<Faculty> getFacultyByName(String name) {
        return Optional.ofNullable(facultyRepository.findByNameIgnoreCase(name));
    }
}

