package com.PostBloging2.PostBloging.Service;

import com.PostBloging2.PostBloging.DTO.ReviewDTO;
import com.PostBloging2.PostBloging.Entity.ReviewEntity;

public interface ReviewMapper {

    ReviewEntity toEntity(ReviewDTO reviewDTO);
    ReviewDTO toDTO(ReviewEntity reviewEntity);

}
