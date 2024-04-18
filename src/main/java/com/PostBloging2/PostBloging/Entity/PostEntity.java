package com.PostBloging2.PostBloging.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "postEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReviewEntity> reviewEntity;
}
