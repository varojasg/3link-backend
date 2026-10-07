package com.clouffy.threelink.words.interfaces.rest;


import com.clouffy.threelink.words.application.WordService;
import com.clouffy.threelink.words.domain.model.Filter;
import com.clouffy.threelink.words.domain.model.Word;
import com.clouffy.threelink.words.interfaces.rest.dto.CreateWordRequest;
import com.clouffy.threelink.words.interfaces.rest.dto.WordResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/words")
public class WordController {
    private final WordService wordService;

    public  WordController(WordService wordService){
        this.wordService=wordService;
    }

    @GetMapping
    public List<WordResponse> getAllWords(){
        return wordService.getAllWords().
                stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{wordId}")
    public WordResponse getWord(@PathVariable Long wordId){
        return toResponse(wordService.getWord(wordId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WordResponse createWord(@Valid @RequestBody CreateWordRequest request){
        return toResponse(wordService.createWord(
                request.value(),
                request.filters()
        ));
    }

    @GetMapping("/random")
    public List<WordResponse> getRandomWords(
            @RequestParam List<String> filters,
            @RequestParam(defaultValue = "3") int limit){
        return wordService.getRandomWords(filters,limit).stream().map(this::toResponse).toList();
    }


    private WordResponse toResponse(Word word){
        List<String> filtersValues = new ArrayList<>();
        for(Filter filter : word.getFilters()){
            filtersValues.add(filter.getValue());
        }
        return new WordResponse(
                word.getId(),
                word.getValue(),
                filtersValues

        );
    }
}
