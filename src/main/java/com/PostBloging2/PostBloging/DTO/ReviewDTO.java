package com.PostBloging2.PostBloging.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

    @JsonIgnore
    private Long id;
    private String reviewOtziv;

    public ReviewDTO(String reviewOtziv) {
        this.reviewOtziv = reviewOtziv;
    }
}
