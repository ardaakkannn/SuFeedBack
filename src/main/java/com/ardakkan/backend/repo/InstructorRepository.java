package com.ardakkan.backend.repo;



import com.ardakkan.backend.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    // Öğretim üyesinin e-mail adresi ile arama yap
    Instructor findByEmailIgnoreCase(String email);
}
