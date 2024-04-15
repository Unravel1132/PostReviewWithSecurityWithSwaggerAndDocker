package com.PostBloging2.PostBloging.Service;


import com.PostBloging2.PostBloging.Entity.PostEntity;
import com.PostBloging2.PostBloging.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostEntityService {


    List<PostEntity> findAll();

    PostEntity findById(Long id);

    PostEntity save(PostEntity postEntity);

    void deleteById(Long id);

    List<PostEntity> findByTitle(String title);


}