package com.PostBloging2.PostBloging.Service.ReviewServiceImpl;

import com.PostBloging2.PostBloging.Entity.ReviewEntity;
import com.PostBloging2.PostBloging.Repository.ReviewRepository;
import com.PostBloging2.PostBloging.Service.ReviewService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional
    public ReviewEntity createReview(Long postId, ReviewEntity review) {
        return reviewRepository.save(review);
    }

    @Override
    @Transactional
    public ReviewEntity updateReview(Long id, String updatedText) {
        ReviewEntity existingReview = reviewRepository.findById(id).orElse(null);
        if (existingReview != null) {
            existingReview.setReviewOtziv(updatedText);
            return reviewRepository.save(existingReview);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public ReviewEntity getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }


}