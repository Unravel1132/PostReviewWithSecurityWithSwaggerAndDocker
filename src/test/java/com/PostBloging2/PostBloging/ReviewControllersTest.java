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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


 public class ReviewControllersTest {

    @Mock
    ReviewServiceImpl reviewService;

    @InjectMocks
    private ReviewController reviewController;

    private List<ReviewDTO> mockReviewList;

    @BeforeEach
    void setUp(){

        MockitoAnnotations.initMocks(this);


    }

    @Test
    void findAllReview(){

        when(reviewService.getAllReviews()).thenReturn(mockReviewList);

        ResponseEntity<List<ReviewDTO>> response = reviewController.getAllReview();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), mockReviewList);


    }

     @Test
     void getReviewById() {
         Long id = 1L;

         ReviewDTO mockReviewDTO = mockReviewList.get(0);

         when(reviewService.getReviewById(id)).thenReturn(mockReviewDTO);

         ResponseEntity<ReviewDTO> response = reviewController.getReviewById(id);

         assertEquals(HttpStatus.OK, response.getStatusCode());
         assertEquals(mockReviewDTO, response.getBody());
     }

     @Test
    void createReview() {
        Long postId = 1L;
        ReviewDTO reviewDTO = new ReviewDTO("Новый отзыв");
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReviewOtziv(reviewDTO.getReviewOtziv());

        when(reviewService.create(postId, reviewDTO)).thenReturn(new ReviewDTO("Новый отзыв"));

        ResponseEntity<ReviewDTO> response = reviewController.createReview(postId, reviewDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode()); // Update to check for 201 CREATED

        assertEquals(reviewDTO, response.getBody());
    }

    @Test
    void createReviewWithException() {

        Long postId = 1L;
        ReviewDTO reviewDTO = new ReviewDTO();

        when(reviewService.create(postId, reviewDTO)).thenThrow(new RuntimeException());

        ResponseEntity<ReviewDTO> response = reviewController.createReview(postId, reviewDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        assertNotNull(response.getBody());
        assertEquals(new ReviewDTO(), response.getBody());
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

    @Test
    void updateReviewWithException() {
        Long postId = 1L;
        ReviewDTO reviewDTO = new ReviewDTO();
        when(reviewService.getReviewById(postId)).thenThrow(new RuntimeException("Error"));

        ResponseEntity<ReviewDTO> response = reviewController.updateReview(postId, reviewDTO);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());


    }

    @Test
    void deleteReview() {
        Long postId = 1L;
        ReviewDTO reviewDTO = new ReviewDTO();
        when(reviewService.getReviewById(postId)).thenReturn(reviewDTO);
        ResponseEntity<Void> response = reviewController.deleteReview(postId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

    }

    @Test
    void deleteReviewWithException() {
        Long postId = 1L;
        ReviewDTO reviewDTO = new ReviewDTO();
        when(reviewService.getReviewById(postId)).thenThrow(new RuntimeException("Error"));
        ResponseEntity<Void> response = reviewController.deleteReview(postId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

}
