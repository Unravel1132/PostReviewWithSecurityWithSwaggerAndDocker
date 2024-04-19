package com.PostBloging2.PostBloging.Service.ReviewServiceImpl;

import com.PostBloging2.PostBloging.DTO.ReviewDTO;
import com.PostBloging2.PostBloging.Entity.ReviewEntity;
import com.PostBloging2.PostBloging.Repository.ReviewRepository;
import com.PostBloging2.PostBloging.Service.ReviewMapper;
import com.PostBloging2.PostBloging.Service.ReviewService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    private final ReviewMapper reviewMapper;
    @Autowired
    public ReviewServiceImpl(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @Override
    public ReviewDTO createReview(Long postId, ReviewDTO review) {
        ReviewEntity reviewEntity = reviewMapper.toEntity(review);
        ReviewEntity createdEntity = reviewRepository.save(reviewEntity);
        return reviewMapper.toDTO(createdEntity);

    }

    @Override
    public ReviewDTO updateReview(Long id, String updatedText) {
        Optional<ReviewEntity> optionalReviewEntity = reviewRepository.findById(id);
        if(optionalReviewEntity.isPresent()) {
            ReviewEntity existingReviewEntity = optionalReviewEntity.get();
            existingReviewEntity.setReviewOtziv(updatedText);
            ReviewEntity saveReviewEntity = reviewRepository.save(existingReviewEntity);
            return reviewMapper.toDTO(saveReviewEntity);
         }
        return null;
      }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public ReviewDTO getReviewById(Long id) {
        ReviewEntity reviewEntity = reviewRepository.findById(id).orElse(null);
        return reviewMapper.toDTO(reviewEntity);
     }


}