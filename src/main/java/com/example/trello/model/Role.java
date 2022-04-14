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
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String content;

    @OneToMany(fetch = FetchType.LAZY,targetEntity = UserRole.class,cascade = CascadeType.ALL)
    @JoinColumn(name="role_id")
    private List<UserRole> userRoleList;
}

