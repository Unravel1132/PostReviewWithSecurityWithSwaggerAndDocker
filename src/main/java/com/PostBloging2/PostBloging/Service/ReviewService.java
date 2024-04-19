package com.PostBloging2.PostBloging.Service;

import com.PostBloging2.PostBloging.DTO.ReviewDTO;
import com.PostBloging2.PostBloging.Entity.ReviewEntity;

import java.util.List;

public interface ReviewService {

    ReviewDTO createReview(Long id, ReviewDTO reviewDTO);
    ReviewDTO updateReview(Long id, String updatedText);
    List<ReviewDTO> finaAllReview();
    void deleteReview(Long id);

    ReviewDTO getReviewById(Long id);

}
