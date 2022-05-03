package com.example.trello.model;

import com.example.trello.model.enumeration.ViewPermission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "board")
public class Board implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Board_SEQ")
    @SequenceGenerator(name = "Board_SEQ")
    private Long id;

    private String title;

    private String background;

    @ManyToOne
    @JoinColumn(name = "workspace_id", referencedColumnName = "id")
    private Workspace workspace;

    @Enumerated(EnumType.STRING)
    @Column(name = "view_permission")
    private ViewPermission viewPermission;
}
