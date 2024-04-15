package com.PostBloging2.PostBloging.Repository;

import com.PostBloging2.PostBloging.Entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findByTitle(String title);
}