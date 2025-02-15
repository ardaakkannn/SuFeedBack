package com.ardakkan.backend.repo;



import com.ardakkan.backend.entity.Review;
import com.ardakkan.backend.entity.Course;
import com.ardakkan.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Belirli bir kursa ait tüm yorumları getir
    List<Review> findByCourse(Course course);

    // Belirli bir kullanıcıya ait tüm yorumları getir
    List<Review> findByUser(User user);
}

