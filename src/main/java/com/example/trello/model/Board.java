package com.example.trello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @OneToMany(fetch = FetchType.LAZY,targetEntity = Card.class,cascade = CascadeType.ALL)
    @JoinColumn(name="board_id")
    private List<Card> cardList;

    @OneToMany(fetch = FetchType.LAZY,targetEntity = Board_UserRole.class,cascade = CascadeType.ALL)
    @JoinColumn(name="board_id")
    private List<Board_UserRole> boardUserRoleList;

}
