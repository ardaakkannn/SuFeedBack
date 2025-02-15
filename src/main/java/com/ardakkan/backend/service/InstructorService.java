package com.ardakkan.backend.service;


import com.ardakkan.backend.entity.Instructor;
import com.ardakkan.backend.repo.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    // ✅ Tüm öğretim üyelerini getir
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    // ✅ ID'ye göre öğretim üyesi getir
    public Optional<Instructor> getInstructorById(Long id) {
        return instructorRepository.findById(id);
    }

    // ✅ Yeni öğretim üyesi ekle veya güncelle
    public Instructor saveOrUpdateInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    // ✅ ID'ye göre öğretim üyesini sil
    public void deleteInstructor(Long id) {
        instructorRepository.deleteById(id);
    }

    // ✅ Email'e göre öğretim üyesi getir
    public Optional<Instructor> getInstructorByEmail(String email) {
        return Optional.ofNullable(instructorRepository.findByEmailIgnoreCase(email));
    }
}

