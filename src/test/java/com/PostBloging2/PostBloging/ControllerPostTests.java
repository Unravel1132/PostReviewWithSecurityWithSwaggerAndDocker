package com.PostBloging2.PostBloging;


import com.PostBloging2.PostBloging.Controllers.PostController;
import com.PostBloging2.PostBloging.DTO.PostDTO;
import com.PostBloging2.PostBloging.Service.PostServiceImpl.PostServiceImpl;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class ControllerPostTests {

    @Mock
    PostServiceImpl postService;

    @InjectMocks
    PostController postController;

    private List<PostDTO> mockPostList;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.initMocks(this);
        mockPostList = new ArrayList<>();
        mockPostList.add(new PostDTO("Albert", "Георгис"));
    }

    @Test
     void findAllPosts() {
        //when
        when(postService.findAll()).thenReturn(mockPostList);
        ResponseEntity<List<PostDTO>> listResponseEntity = postController.finaAllBooks();

        //then
        assertEquals(listResponseEntity.getStatusCodeValue(), 200);
        assertEquals(listResponseEntity.getBody(), mockPostList);
    }

    @Test
     void findById_shouldReturnPostById() {
        //given
        Long id = 1L;
        PostDTO mockPost = new PostDTO("Georgis", "Stal X-Men");
        //when
        when(postService.findById(id)).thenReturn(mockPost);

        ResponseEntity<PostDTO> postResponseEntity = postController.findById(id);

        assertEquals(postResponseEntity.getStatusCodeValue(), 200);
        assertEquals(mockPost, postResponseEntity.getBody());

    }

    @Test
     void findById_shouldReturnNotFoundWhenPostNotFound() {
        //given
        Long id = 10L;
        //when
        when(postService.findById(id)).thenReturn(null);
        ResponseEntity<PostDTO> response = postController.findById(id);
        //then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
     void addPost_ReturnOk() {
        //given
        PostDTO postDTO = new PostDTO();
        PostDTO savedDTO = new PostDTO("Alba", "Georgis");

        //when
        when(postService.save(postDTO)).thenReturn(savedDTO);

        ResponseEntity<PostDTO> response = postController.addPost(postDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedDTO, response.getBody());
    }

    @Test
     void addPost_ExceptionThrown() {
        //given
        PostDTO postDTO = new PostDTO();

        //when
        when(postService.save(postDTO)).thenThrow(new RuntimeException("Error"));
        ResponseEntity<PostDTO> response = postController.addPost(postDTO);
        //then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());

    }

    @Test
    void putPostTest() {
        // given
        Long id = 1L;
        PostDTO existed = new PostDTO(id, "Alba", "Georgis");
        PostDTO updateDescription = new PostDTO(id, "Nikos", "Dima");
        // When
        when(postService.findById(id)).thenReturn(existed);
        when(postService.update(existed)).thenReturn(updateDescription);
        ResponseEntity<PostDTO> response = postController.putPost(id, updateDescription);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updateDescription, response.getBody());

        verify(postService, times(1)).findById(id);
        verify(postService, times(1)).update(existed);
    }

    @Test
    void  putPost_ExceptionThrown() {
        Long id = 1L;
        PostDTO existed = new PostDTO(id, "Alba", "Dima");
        when(postService.findById(id)).thenThrow(new RuntimeException("Error"));

        ResponseEntity<PostDTO> response = postController.putPost(id, existed);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
        verify(postService, times(1)).findById(id);
        verify(postService, never()).update(any());
    }

    @Test
     void deletePost() {
        //given
        Long id = 1L;
        //when
        ResponseEntity<Void> response = postController.deletePost(id);
        //then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(postService, times(1)).deleteById(id);
    }

}
