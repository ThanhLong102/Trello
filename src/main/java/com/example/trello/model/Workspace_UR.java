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
@Table(name = "workspace_user_role")
public class Workspace_UR implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Workspace_User_Role_SEQ")
    @SequenceGenerator(name = "Workspace_User_Role_SEQ")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workspace_id", referencedColumnName = "id")
    private Workspace workspace;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;
}
