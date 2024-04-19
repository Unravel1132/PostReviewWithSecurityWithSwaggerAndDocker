package com.PostBloging2.PostBloging.Controllers;


import com.PostBloging2.PostBloging.DTO.ReviewDTO;
import com.PostBloging2.PostBloging.Entity.ReviewEntity;
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

@Tag(name = "Основные методы отзыв контролерра")
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewServiceImpl reviewServiceImpl;

    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    @Autowired
    public ReviewController(ReviewServiceImpl reviewServiceImpl) {
        this.reviewServiceImpl = reviewServiceImpl;
    }

    @Operation(
            summary = "Добавляет отзыв к посту"
    )
    @PostMapping("/addReview/{postId}")
    public ResponseEntity<ReviewDTO> createReview(@PathVariable Long postId, @RequestBody ReviewDTO reviewDTO) {
        try {
            if (postId != null) {
                ReviewDTO reviewDTO1 = reviewServiceImpl.createReview(postId, reviewDTO);
                if (reviewDTO1 != null) {
                    return ResponseEntity.ok(reviewDTO);
                } else {
                    return ResponseEntity.notFound().build();
                }
            } else return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            logger.error("Ошибка при добавлении отзыва: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }

    }
    @Operation(
            summary = "Изменяет отзыв",
            description = "Изменят текст"
    )
    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable Long id, @RequestBody String updatedText) {
        try {
            if (id != null) {
                ReviewDTO reviewDTO = reviewServiceImpl.updateReview(id, updatedText);
                if (reviewDTO != null) {
                    return ResponseEntity.ok(reviewDTO);
                } else {
                    return ResponseEntity.notFound().build();
                }
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            logger.error("Ошибка при обновлении: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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
