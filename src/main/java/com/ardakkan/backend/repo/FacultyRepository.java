package com.ardakkan.backend.repo;



import com.ardakkan.backend.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    // Fakülte adından arama yap
    Faculty findByNameIgnoreCase(String name);
}
