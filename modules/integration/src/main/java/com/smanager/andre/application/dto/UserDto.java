package com.arao.andre.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("age")
    private Integer age;

}
