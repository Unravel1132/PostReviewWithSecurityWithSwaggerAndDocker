package com.PostBloging2.PostBloging.Service;

import com.PostBloging2.PostBloging.DTO.PostDTO;
import com.PostBloging2.PostBloging.Entity.PostEntity;

public interface PostMapper {

    PostEntity toEntity(PostDTO postDTO);
    PostDTO toDTO(PostEntity postEntity);
}
