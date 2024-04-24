package com.PostBloging2.PostBloging;

import com.PostBloging2.PostBloging.Controllers.ReviewController;
import com.PostBloging2.PostBloging.DTO.PostDTO;
import com.PostBloging2.PostBloging.Entity.ReviewEntity;
import com.PostBloging2.PostBloging.Service.ReviewService;
import com.PostBloging2.PostBloging.Service.ReviewServiceImpl.ReviewServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewControllersTest {

    @Mock
    ReviewServiceImpl reviewService;

    @InjectMocks
    private ReviewController reviewController;

    @Test
    public void createReview(){
        Long postId = 1L;

        ReviewEntity review1 = new ReviewEntity("This is my review");

        when(reviewService.createReview(postId, review1)).thenReturn(review1);

        ResponseEntity<ReviewEntity> responseEntity = reviewController.createReview(postId, review1);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(review1, responseEntity.getBody());

    }
}
