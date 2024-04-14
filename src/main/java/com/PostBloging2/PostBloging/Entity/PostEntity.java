package com.PostBloging2.PostBloging.Entity;


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

    private String name;
    @Column(name = "descriprtion", length = 500)
    private String descriprtion;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
   private Set<ReviewEntity> reviewEntity;
}
