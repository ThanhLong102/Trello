package com.example.trello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card")
public class Card implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Card_SEQ")
    @SequenceGenerator(name = "Card_SEQ")
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "board_id", referencedColumnName = "id")
    private Board board;

}
