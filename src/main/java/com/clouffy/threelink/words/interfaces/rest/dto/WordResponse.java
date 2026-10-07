package com.clouffy.threelink.words.interfaces.rest.dto;


import java.util.List;

public record WordResponse(
        Long id,
        String value,
        List<String> filters
) {
}
