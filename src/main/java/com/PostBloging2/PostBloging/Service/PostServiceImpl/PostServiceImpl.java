package com.PostBloging2.PostBloging.Service.PostServiceImpl;

import com.PostBloging2.PostBloging.DTO.PostDTO;
import com.PostBloging2.PostBloging.Entity.PostEntity;
import com.PostBloging2.PostBloging.Repository.PostRepository;
import com.PostBloging2.PostBloging.Service.PostMapper;
import com.PostBloging2.PostBloging.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public List<PostDTO> findAll() {
        List<PostEntity> postDTOList = postRepository.findAll();
        return  postDTOList.stream().map(postMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO findById(Long id) {
       PostEntity postEntity=  postRepository.findById(id).orElse(null);
        return postMapper.toDTO(postEntity);

    }

    @Override
    public PostDTO save(PostDTO postDTO) {
        PostEntity postEntity = postMapper.toEntity(postDTO);
        PostEntity savedPostEntity = postRepository.save(postEntity);
        return postMapper.toDTO(savedPostEntity);
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<PostDTO> findByTitle(String title) {
        List<PostEntity> postEntities = postRepository.findByTitle(title);
        return postEntities.stream()
                .map(postMapper::toDTO)
                .collect(Collectors.toList());
    }
}
