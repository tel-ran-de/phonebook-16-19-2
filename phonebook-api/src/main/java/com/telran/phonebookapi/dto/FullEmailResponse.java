package com.telran.phonebookapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FullEmailResponse {
    private long id;
    private String email;
    @JsonProperty("isFavorite")
    private boolean isFavorite;
}
