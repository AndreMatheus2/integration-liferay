package com.arao.andre.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PostDto {
    @JsonProperty("text")
    private String text;
}
