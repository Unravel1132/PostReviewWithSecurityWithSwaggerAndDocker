package com.PostBloging2.PostBloging.Service.ReviewServiceImpl;

import com.PostBloging2.PostBloging.DTO.ReviewDTO;
import com.PostBloging2.PostBloging.Entity.PostEntity;
import com.PostBloging2.PostBloging.Entity.ReviewEntity;
import com.PostBloging2.PostBloging.Repository.PostRepository;
import com.PostBloging2.PostBloging.Repository.ReviewRepository;
import com.PostBloging2.PostBloging.Service.ReviewMapper;
import com.PostBloging2.PostBloging.Service.ReviewService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private PostRepository postRepository;
    private final ReviewMapper reviewMapper;
    @Autowired
    public ReviewServiceImpl(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @Override
    public ReviewDTO createReview(Long postId, ReviewDTO reviewDTO) {
        if (reviewDTO.getReviewOtziv() == null) {
            return null;
        }
        PostEntity postEntity = postRepository.findById(postId).orElse(null);
        if (postEntity == null) {
            return null;
        }
        ReviewEntity reviewEntity = reviewMapper.toEntity(reviewDTO);
        reviewEntity.setPostEntity(postEntity);
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
    public List<ReviewDTO> finaAllReview() {
        List<ReviewEntity> reviewEntityDTO = reviewRepository.findAll();
        return  reviewEntityDTO.stream().map(reviewMapper::toDTO).collect(Collectors.toList());
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