package com.PostBloging2.PostBloging.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import com.PostBloging2.PostBloging.Entity.PostEntity;
import com.PostBloging2.PostBloging.Service.PostEntityImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Основные методы пост контролерра")
@RestController
@RequestMapping("/api/v1")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostEntityImpl postEntityImpl;

    @Autowired
    public PostController(PostEntityImpl postEntity) {
        this.postEntityImpl = postEntity;
    }


    @Operation(
            summary = "Вызывает получение всех постов"
    )
    @GetMapping
    public ResponseEntity<List<PostEntity>> finaAllBooks() {
        List<PostEntity> books = postEntityImpl.findAll();
        try {
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Ошибка получения всхе книг: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Добавляет новый пост на страницу",
            description = "Прошу заметить что оно работает без Анотации" +
                    "так как передает не Json формат, а текст"
    )
    @PostMapping("/posts")
    public ResponseEntity<PostEntity> addPost(PostEntity postEntity) {
        try {
            PostEntity postEntity1 = new PostEntity();
            postEntity1.setTitle(postEntity.getTitle());
            postEntity1.setDescriprtion(postEntity.getDescriprtion());
            PostEntity savedPost = postEntityImpl.save(postEntity1);
            return ResponseEntity.ok(postEntity1);
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
    public ResponseEntity<PostEntity> putPost(@PathVariable Long id, @RequestBody PostEntity updateEntity) {
        try {
            PostEntity postEntity12 = postEntityImpl.findById(id);
            if (postEntity12 != null) {
                return ResponseEntity.notFound().build();
            }
            postEntity12.setTitle(updateEntity.getTitle());
            postEntity12.setDescriprtion(updateEntity.getDescriprtion());
            PostEntity saveBook = postEntityImpl.save(updateEntity);
            return ResponseEntity.ok(saveBook);
        } catch (Exception e) {
            logger.error("Ошибка при измении: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Operation(
            summary = "Удаляет пост"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        try {

            postEntityImpl.deleteById(id);
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
    public ResponseEntity<List<PostEntity>> search(@RequestParam(required = false) String title) {
        List<PostEntity> searchResults = new ArrayList<>();
        if (title != null) {
            searchResults.addAll(postEntityImpl.findByTitle(title));
        }

        if (searchResults.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(searchResults);
        }
    }

}


