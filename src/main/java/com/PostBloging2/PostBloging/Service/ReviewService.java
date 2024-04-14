package com.PostBloging2.PostBloging.Service;

import com.PostBloging2.PostBloging.Entity.ReviewEntity;

public interface ReviewService {

    ReviewEntity createReview(ReviewEntity review);

    ReviewEntity updateReview(Long id, String updatedText);

    void deleteReview(Long id);

    ReviewEntity getReviewById(Long id);

}
