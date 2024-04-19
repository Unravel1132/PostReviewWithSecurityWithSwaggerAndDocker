package com.PostBloging2.PostBloging.Service;


import com.PostBloging2.PostBloging.DTO.PostDTO;
import com.PostBloging2.PostBloging.Entity.PostEntity;

import java.util.List;

public interface PostService {


    List<PostDTO> findAll();

    PostDTO findById(Long id);

    PostDTO save(PostDTO postDTO);
    void deleteById(Long id);

    List<PostDTO> findByTitle(String title);


}