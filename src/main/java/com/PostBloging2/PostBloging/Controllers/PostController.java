package com.PostBloging2.PostBloging.Controllers;

import com.PostBloging2.PostBloging.DTO.PostDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import com.PostBloging2.PostBloging.Service.PostServiceImpl.PostServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Tag(name = "Основные методы пост контролерра")
@RestController
@RequestMapping("/api/v1")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostServiceImpl postServiceImpl;

    @Autowired
    public PostController(PostServiceImpl postEntity) {
        this.postServiceImpl = postEntity;
    }


    @Operation(
            summary = "Вызывает получение всех постов"
    )
    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> finaAllBooks() {
        try {
            return ResponseEntity.ok(postServiceImpl.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Ошибка получения всхе книг: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable Long id) {
        try {

            PostDTO postDTO = postServiceImpl.findById(id);
            if (postDTO != null) {
                return ResponseEntity.ok(postDTO);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            logger.error("Ошибка при поиске: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Добавляет новый пост на страницу",
            description = "Прошу заметить что оно работает без Анотации" +
                    "так как передает не Json формат, а текст"
    )
    @PostMapping("/posts")
    public ResponseEntity<PostDTO> addPost(@RequestBody PostDTO postDTO) {
        try {
            return ResponseEntity.ok(postServiceImpl.save(postDTO));
        } catch (Exception e) {
            logger.error("Ошибка при добавление поста: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Изменяет пост по желанию",
            description = "Прошу заметить что оно работает без Анотации" +
                    "так как передает не Json формат, а текст"
    )

    @PutMapping("/post/{id}")
    public ResponseEntity<PostDTO> putPost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        try {
            PostDTO existsDTO = postServiceImpl.findById(id);
            if (existsDTO == null) {
                return ResponseEntity.notFound().build();
            }

            existsDTO.setId(id);
            existsDTO.setTitle(postDTO.getTitle());
            existsDTO.setDescription(postDTO.getDescription());

            PostDTO updatedPost = postServiceImpl.update(existsDTO);
            if (updatedPost != null) {
                return ResponseEntity.ok(updatedPost);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception e) {
            logger.error("Ошибка при изменении: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @Operation(
            summary = "Удаляет пост"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        try {
            postServiceImpl.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Ошибка при удалении: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Operation(
            summary = "Ищет пост в поисковой строке",
            description = "Ищет по названию или по автору поста," +
                    "можно выбирать каким образом искать так как у нас required = false"
    )
    @GetMapping("/search")
    public ResponseEntity<List<PostDTO>> search(@RequestParam(required = false) String title) {

        if (title == null) {
            return ResponseEntity.badRequest().build();
        }
        List<PostDTO> searchResults = postServiceImpl.findByTitle(title);

        if (!searchResults.isEmpty()) {
            return ResponseEntity.ok(searchResults);

        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }

    }

}


