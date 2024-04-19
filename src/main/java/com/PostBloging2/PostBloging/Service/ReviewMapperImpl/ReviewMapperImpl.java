package com.PostBloging2.PostBloging.Service.ReviewMapperImpl;


import com.PostBloging2.PostBloging.DTO.ReviewDTO;
import com.PostBloging2.PostBloging.Entity.ReviewEntity;
import com.PostBloging2.PostBloging.Service.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewMapperImpl implements ReviewMapper{

    @Override
    public ReviewEntity toEntity(ReviewDTO reviewDTO) {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReviewOtziv(reviewEntity.getReviewOtziv());
        return reviewEntity;
    }

    @Override
    public ReviewDTO toDTO(ReviewEntity reviewEntity) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setReviewOtziv(reviewEntity.getReviewOtziv());
        return reviewDTO;
    }
}
