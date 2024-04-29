package com.PostBloging2.PostBloging.Service;

import com.PostBloging2.PostBloging.DTO.ReviewDTO;
import com.PostBloging2.PostBloging.Entity.ReviewEntity;

import java.util.List;

public interface ReviewService {

    ReviewDTO create(Long postId, ReviewDTO reviewDTO);
    ReviewDTO updateReview(Long id, ReviewDTO updatedText);
    List<ReviewDTO> getAllReviews();
    void deleteReview(Long id);
     ReviewDTO getReviewById(Long id);

}
