package com.PostBloging2.PostBloging;

import com.PostBloging2.PostBloging.Controllers.ReviewController;
import com.PostBloging2.PostBloging.DTO.PostDTO;
import com.PostBloging2.PostBloging.DTO.ReviewDTO;
import com.PostBloging2.PostBloging.Entity.ReviewEntity;
import com.PostBloging2.PostBloging.Service.ReviewService;
import com.PostBloging2.PostBloging.Service.ReviewServiceImpl.ReviewServiceImpl;
import org.hibernate.sql.ast.tree.expression.CaseSimpleExpression;
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
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


//ТЕСТЫ В РАЗБОТКе. ИСПЫТАЮТСЯ ТРУДНОСТИ!!!!!!!!!!!!!!!!!!!
@ExtendWith(MockitoExtension.class)
public class ReviewControllersTest {

    @Mock
    ReviewServiceImpl reviewService;

    @InjectMocks
    private ReviewController reviewController;

    private List<ReviewDTO> mockReviewList;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        mockReviewList = new ArrayList<>();
        mockReviewList.add(new ReviewDTO("Новый отзыв"));
        when(reviewService.getAllReviews()).thenReturn(Collections.emptyList());

    }

    @Test
    void findAllReview(){

        when(reviewService.getAllReviews()).thenReturn(mockReviewList);

        ResponseEntity<List<ReviewDTO>> response = reviewController.getAllReview();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), mockReviewList);


    }


    @Test
     void createReview() {
        Long postId = 1L;
        ReviewDTO reviewDTO = new ReviewDTO("Новый отзыв");
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReviewOtziv(reviewDTO.getReviewOtziv());
        when(reviewService.create(postId, reviewDTO)).thenReturn(new ReviewDTO());

        ResponseEntity<ReviewDTO> response = reviewController.createReview(postId, reviewDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(reviewDTO.getReviewOtziv(), response.getBody().getReviewOtziv());


    }
    @Test
    void createReviewWithException() {
        Long postId = 1L;
        ReviewDTO reviewDTO = new ReviewDTO();
        when(reviewService.create(postId, reviewDTO)).thenThrow(new RuntimeException());
        ResponseEntity<ReviewDTO> response = reviewController.createReview(postId, reviewDTO);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void updateReview() {
        Long postId = 1L;
        String newText = "This is an updated review text.";

        ReviewDTO existingReviewDTO = new ReviewDTO();
        ReviewDTO updatedReviewDTO = new ReviewDTO();

        when(reviewService.getReviewById(postId)).thenReturn(existingReviewDTO);
        when(reviewService.updateReview(postId, updatedReviewDTO)).thenReturn(updatedReviewDTO);

        ResponseEntity<ReviewDTO> response = reviewController.updateReview(postId, updatedReviewDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedReviewDTO, response.getBody());

    }


}
