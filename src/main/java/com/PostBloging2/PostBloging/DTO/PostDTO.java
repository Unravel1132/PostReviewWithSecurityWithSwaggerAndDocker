package com.PostBloging2.PostBloging.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    
    @JsonIgnore
    private Long id;
    private String title;
    private String description;


    public PostDTO(String alba, String georgis) {
    }
}



