package com.ardakkan.backend.service;

import com.ardakkan.backend.entity.Review;
import com.ardakkan.backend.entity.Course;
import com.ardakkan.backend.entity.User;
import com.ardakkan.backend.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    // ✅ Tüm yorumları getir
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // ✅ ID'ye göre yorumu getir
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    // ✅ Yeni yorum ekle veya güncelle
    public Review saveOrUpdateReview(Review review) {
        return reviewRepository.save(review);
    }

    // ✅ ID'ye göre yorumu sil
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    // ✅ Belirli bir dersin tüm yorumlarını getir
    public List<Review> getReviewsByCourse(Course course) {
        return reviewRepository.findByCourse(course);
    }

    // ✅ Belirli bir öğrencinin yaptığı yorumları getir
    public List<Review> getReviewsByUser(User user) {
        return reviewRepository.findByUser(user);
    }
}

