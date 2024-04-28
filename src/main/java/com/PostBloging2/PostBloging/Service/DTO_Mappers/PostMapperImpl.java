package com.PostBloging2.PostBloging.Service.PostMapperImpl;

import com.PostBloging2.PostBloging.DTO.PostDTO;
import com.PostBloging2.PostBloging.Entity.PostEntity;
import com.PostBloging2.PostBloging.Service.PostMapper;
import org.springframework.stereotype.Service;

@Service
public class PostMapperImpl implements PostMapper {
    @Override
    public PostEntity toEntity(PostDTO postDTO) {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postDTO.getTitle());
        postEntity.setDescription(postDTO.getDescription());
         return postEntity;
    }

    @Override
    public PostDTO toDTO(PostEntity postEntity) {
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle(postEntity.getTitle());
        postDTO.setDescription(postEntity.getDescription());
         return postDTO;
    }
}
