package com.PostBloging2.PostBloging.Service.DTO_Mappers;


import com.PostBloging2.PostBloging.DTO.ReviewDTO;
import com.PostBloging2.PostBloging.Entity.ReviewEntity;
import com.PostBloging2.PostBloging.Service.PostMapper;
import com.PostBloging2.PostBloging.Service.ReviewMapper;
import org.springframework.stereotype.Service;

@Service
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public ReviewEntity toReviewMapper(ReviewDTO reviewDTO) {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReviewOtziv(reviewDTO.getReviewOtziv());
     return reviewEntity;
    }
    @Override
    public ReviewDTO toDTOReviewMapper(ReviewEntity reviewEntity) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setReviewOtziv(reviewEntity.getReviewOtziv());
        return reviewDTO;
    }

}
