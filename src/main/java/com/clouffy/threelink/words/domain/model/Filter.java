package com.clouffy.threelink.words.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "filters")
@Getter
@Setter
@NoArgsConstructor
public class Filter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String value;

    @ManyToOne
    @JoinColumn(name = "word_id", nullable = false)
    private Word word;
}
