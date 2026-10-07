package com.clouffy.threelink.words.application;


import com.clouffy.threelink.words.domain.model.Filter;
import com.clouffy.threelink.words.domain.model.Word;
import com.clouffy.threelink.words.domain.repository.WordRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WordService {
    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository){
        this.wordRepository = wordRepository;
    }

    public List<Word> getAllWords(){
        return wordRepository.findAll();
    }

    public Word getWord(Long id){
        return wordRepository.getReferenceById(id);
    }

    @Transactional
    public Word createWord(
            String value,
            List<String> filters
    ){

        Word word = new Word();
        word.setValue(value);

        List<Filter> newFilters = new ArrayList<>();
        for(String filterValue : filters){
            Filter newFilter = new Filter();
            newFilter.setValue(filterValue);
            newFilter.setWord(word);

            newFilters.add(newFilter);
        }
        word.setFilters(newFilters);
        wordRepository.save(word);
        return word;

    }

    public List<Word> getRandomWords(List<String> filters, int limit){
        List<Word> words = new ArrayList<>(wordRepository.findDistinctByFiltersValueIn(filters));

        Collections.shuffle(words);
        return words.stream().limit(limit).toList();
    }
}
