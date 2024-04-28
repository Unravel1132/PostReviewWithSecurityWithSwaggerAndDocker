package com.PostBloging2.PostBloging.Controllers;


import com.PostBloging2.PostBloging.DTO.ReviewDTO;
import com.PostBloging2.PostBloging.Entity.PostEntity;
import com.PostBloging2.PostBloging.Entity.ReviewEntity;
import com.PostBloging2.PostBloging.Service.DTO_Mappers.ReviewMapperImpl;
import com.PostBloging2.PostBloging.Service.ReviewServiceImpl.ReviewServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Основные методы отзыв контролерра")
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewServiceImpl reviewServiceImpl;
    private final ReviewMapperImpl reviewMapperImpl;
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    public ReviewController(ReviewServiceImpl reviewServiceImpl, ReviewMapperImpl reviewMapperImpl) {

        this.reviewServiceImpl = reviewServiceImpl;
        this.reviewMapperImpl = reviewMapperImpl;
    }


    @GetMapping("/all/Review")
    public ResponseEntity<List<ReviewDTO>> getAllReview() {
        try{
            List<ReviewDTO> lis1 = reviewServiceImpl.getAllReviews();
            return new ResponseEntity<>(lis1, HttpStatus.OK);
        }catch (Exception e){
            logger.error("Ошибка", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @Operation(
            summary = "Добавляет отзыв к посту"
    )
    @PostMapping("/addReview/{postId}")
    public ResponseEntity<ReviewDTO> createReview(@PathVariable Long postId, @RequestBody ReviewDTO review) {

        try{
            if(postId == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ReviewDTO());
            }
            return ResponseEntity.ok(reviewServiceImpl.create(postId, review));
        }catch (Exception e){
            logger.error("Ошибка добваления отзыва: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ReviewDTO());

        }


    }

    @Operation(
            summary = "Изменяет отзыв",
            description = "Изменят текст"
    )
    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable Long id, @RequestBody ReviewDTO updatedText) {
        ReviewDTO updatedReview = reviewServiceImpl.updateReview(id, updatedText);

        if (updatedReview != null) {
            return ResponseEntity.ok(updatedReview);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Удаляет отзыв"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewServiceImpl.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(
            summary = "Поиск отзыва чтобы посмотреть на него"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id) {
        ReviewDTO review = reviewServiceImpl.getReviewById(id);
        if (review != null) {
            return ResponseEntity.ok(review);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
