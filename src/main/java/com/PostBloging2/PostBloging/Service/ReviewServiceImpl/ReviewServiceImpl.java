package com.PostBloging2.PostBloging.Service.ReviewServiceImpl;

import com.PostBloging2.PostBloging.DTO.ReviewDTO;
import com.PostBloging2.PostBloging.Entity.ReviewEntity;
import com.PostBloging2.PostBloging.Repository.ReviewRepository;
import com.PostBloging2.PostBloging.Service.DTO_Mappers.ReviewMapperImpl;
import com.PostBloging2.PostBloging.Service.ReviewMapper;
import com.PostBloging2.PostBloging.Service.ReviewService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewMapper reviewMapper;


    @Override
    public ReviewDTO create(Long postId, ReviewDTO review) {
        ReviewEntity reviewEntity = reviewMapper.toReviewMapper(review);
        ReviewEntity savedReview = reviewRepository.save(reviewEntity);
        return reviewMapper.toDTOReviewMapper(savedReview);
    }

    @Override
    public ReviewDTO updateReview(Long id, ReviewDTO updatedText) {
        ReviewEntity existingReview = reviewRepository.findById(id).orElse(null);
        if (existingReview != null) {
            existingReview.setReviewOtziv(updatedText.getReviewOtziv());
            reviewRepository.save(existingReview);
            return reviewMapper.toDTOReviewMapper(existingReview);
        }
        return null;
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        List<ReviewEntity> reviewDTOS = reviewRepository.findAll();
        return reviewDTOS.stream().map(request -> reviewMapper
                .toDTOReviewMapper(request))
                .collect(Collectors.toList());


    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public ReviewDTO getReviewById(Long id) {
        if(reviewRepository.existsById(id)) {
            return reviewMapper.toDTOReviewMapper(reviewRepository.findById(id).orElse(null));
        }
        return null;
    }


}