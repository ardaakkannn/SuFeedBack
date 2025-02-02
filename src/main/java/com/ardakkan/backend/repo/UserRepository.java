package com.ardakkan.backend.repo;


import com.ardakkan.backend.entity.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Özel bir sorgu: Kullanıcıyı email ile bulmak
	Optional<User> findByEmail(String email);
	// Eğer 'firstName' veya 'lastName' gibi alanlar varsa
	Optional<User> findByFirstName(String firstName);
	Optional<User> findByLastName(String lastName);
	boolean existsByFirstName(String firstName);
	boolean existsByLastName(String lastName);
    boolean existsByEmail(String email);
    

}