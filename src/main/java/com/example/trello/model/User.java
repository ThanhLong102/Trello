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
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    private Boolean isEnabled;

    @OneToMany(fetch = FetchType.LAZY,targetEntity = UserRole.class,cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private List<UserRole> userRoleList;

}
