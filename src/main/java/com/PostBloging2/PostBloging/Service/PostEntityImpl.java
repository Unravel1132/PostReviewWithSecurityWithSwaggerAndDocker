package com.PostBloging2.PostBloging.Service;

import com.PostBloging2.PostBloging.Entity.PostEntity;
import com.PostBloging2.PostBloging.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostEntityImpl implements PostEntityService {

    @Autowired
    private PostRepository postEntityRepository;

    @Override
    public List<PostEntity> findAll() {
        return postEntityRepository.findAll();
    }

    @Override
    public PostEntity findById(Long id) {
        return postEntityRepository.findById(id).orElse(null);
    }

    @Override
    public PostEntity save(PostEntity postEntity) {
        return postEntityRepository.save(postEntity);
    }

    @Override
    public void deleteById(Long id) {
        postEntityRepository.deleteById(id);
    }

    @Override
    public List<PostEntity> findByTitle(String title) {
        return postEntityRepository.findByTitle(title);
    }

}
