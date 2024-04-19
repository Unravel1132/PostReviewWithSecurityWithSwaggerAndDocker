package com.PostBloging2.PostBloging.Entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "review")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "review_otziv", length = 500)
    private String reviewOtziv;
    @ManyToOne
    @JoinColumn(name ="post_id")
    private PostEntity postEntity;

}
