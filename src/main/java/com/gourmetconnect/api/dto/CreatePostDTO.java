package com.gourmetconnect.api.dto;

import com.gourmetconnect.api.model.valueobjects.PostContent;
import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Data
public class CreatePostDTO {
    private String authorId;
    private String type;
    private PostContent content;
    private GeoJsonPoint location;
}
