package com.PostBloging2.PostBloging.Repository;

import com.PostBloging2.PostBloging.Entity.PostEntity;
import com.PostBloging2.PostBloging.Entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

}
