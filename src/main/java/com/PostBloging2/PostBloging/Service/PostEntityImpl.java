package com.PostBloging2.PostBloging.Service;

import com.PostBloging2.PostBloging.Entity.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostEntityImpl implements PostEntityService{


    private final PostEntityService postEntityService;
    @Autowired
    public PostEntityImpl(PostEntityService postEntityService) {
        this.postEntityService = postEntityService;
    }

    @Override
    public List<PostEntity> findAll() {
        return postEntityService.findAll();
    }

    @Override
    public PostEntity findById(Long id) {
        return postEntityService.findById(id);
    }

    @Override
    public PostEntity save(PostEntity postEntity) {
        return postEntityService.save(postEntity);
    }

    @Override
    public void deleteById(Long id) {
        postEntityService.deleteById(id);
    }

    @Override
    public List<PostEntity> findByTitle(String title) {
        return postEntityService.findByTitle(title);
    }

    @Override
    public List<PostEntity> findByAuthor(String author) {
        return postEntityService.findByAuthor(author);
    }
}
