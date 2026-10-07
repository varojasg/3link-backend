package com.clouffy.threelink.words.domain.repository;

import com.clouffy.threelink.words.domain.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<Word,Long> {
    java.util.Optional<Word> findByValue(String value);

    List<Word> findDistinctByFiltersValueIn(List<String> filters);
}
