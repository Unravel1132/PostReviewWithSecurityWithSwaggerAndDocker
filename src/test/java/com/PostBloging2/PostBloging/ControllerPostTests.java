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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class ControllerPostTests {

    @Mock
    PostServiceImpl postService;

    @InjectMocks
    PostController postController;

    private List<PostDTO> mockPostList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockPostList = new ArrayList<>();
        mockPostList.add(new PostDTO("Albert", "Георгис"));
    }

    @Test
    public void findAllPosts(){
        //when
        when(postService.findAll()).thenReturn(mockPostList);
        ResponseEntity<List<PostDTO>> listResponseEntity = postController.finaAllBooks();

        //then
         assertEquals(listResponseEntity.getStatusCodeValue(), 200);
         assertEquals(listResponseEntity.getBody(), mockPostList);
    }

    @Test
    public void findById_shouldReturnPostById(){
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
    public void findById_shouldReturnNotFoundWhenPostNotFound() {
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
    public void Post_post(){
        //given
        PostDTO postDTO = new PostDTO("Alba", "Georgis");
        //when
        when(postService.save(postDTO)).thenReturn(mockPostList.get(0));
        ResponseEntity<PostDTO> response = postController.addPost(postDTO);
        //then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(postDTO, response.getBody());

    }

    @Test
    public void Post_post_null(){
        //given
        PostDTO postDTO = new PostDTO("Alba", "Georgis");
        //when
        when(postService.save(postDTO)).thenReturn(mockPostList.get(0));
        ResponseEntity<PostDTO> response = postController.addPost(postDTO);
        //then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());

    }

//    @Test
//    public void putPostTest() {
//        // given
//        Long existingId = 1L;
//        String existingTitle = "existingTitle";
//        String updatedTitle = "updatedTitle";
//        String updatedDescription = "updatedDescription";
//        PostDTO existingPostDTO = new PostDTO(existingTitle, "existingDescription");
//
//        PostDTO updatedPostDTO = new PostDTO(updatedTitle, updatedDescription);
//
//        when(postService.findByTitle(existingTitle)).thenReturn((List<PostDTO>) existingPostDTO);
//
//
//    }

    @Test
    public void deletePost(){
        //given
        Long id = 1L;
        //when
        ResponseEntity<Void> response = postController.deletePost(id);
        //then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(postService, times(1)).deleteById(id);
    }
}
