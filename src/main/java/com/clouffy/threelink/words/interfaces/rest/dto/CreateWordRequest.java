package com.clouffy.threelink.words.interfaces.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateWordRequest (
        @NotBlank(message = "Word value is mandatory")
        @Size(max = 100, message = "The word value must not exceed 100 characters")
        String value,

        @NotEmpty(message = "The word must have at least one filter")
        @Size(max = 10, message = "There can´t be more than 10 filters per word")
        List<
                @NotBlank(message = "Filter value is mandatory")
                @Size(max=100, message = "The filter value must not exceed 100 characters")
                String> filters
){

}