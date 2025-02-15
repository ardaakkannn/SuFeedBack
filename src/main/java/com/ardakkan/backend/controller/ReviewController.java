package com.ardakkan.backend.controller;

import com.ardakkan.backend.entity.Review;
import com.ardakkan.backend.entity.Course;
import com.ardakkan.backend.entity.User;
import com.ardakkan.backend.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // ✅ Tüm yorumları getir
    @GetMapping("/all")
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    // ✅ ID'ye göre yorumu getir
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Optional<Review> review = reviewService.getReviewById(id);
        return review.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Yeni yorum ekle veya güncelle
    @PostMapping("/add")
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        Review savedReview = reviewService.saveOrUpdateReview(review);
        return ResponseEntity.ok(savedReview);
    }

    // ✅ ID'ye göre yorumu sil
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Belirli bir dersin yorumlarını getir
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Review>> getReviewsByCourse(@PathVariable Long courseId) {
        Course course = new Course();
        course.setId(courseId);
        return ResponseEntity.ok(reviewService.getReviewsByCourse(course));
    }

    // ✅ Belirli bir kullanıcının yaptığı yorumları getir
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        return ResponseEntity.ok(reviewService.getReviewsByUser(user));
    }
}

