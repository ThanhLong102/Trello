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
@Table(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @OneToMany(fetch = FetchType.LAZY,targetEntity = Comment.class,cascade = CascadeType.ALL)
    @JoinColumn(name="user_role_id")
    private List<Comment> commentList;

    @OneToMany(fetch = FetchType.LAZY,targetEntity = Board_UserRole.class,cascade = CascadeType.ALL)
    @JoinColumn(name="user_role_id")
    private List<Board_UserRole> boardUserRoleList;
}
