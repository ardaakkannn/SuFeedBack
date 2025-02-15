package com.ardakkan.backend.repo;



import com.ardakkan.backend.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {

    // Yıla göre dönemleri getir
    List<Semester> findByYear(int year);

    
}

