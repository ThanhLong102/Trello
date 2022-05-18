package com.example.trello.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Users_SEQ")
    @SequenceGenerator(name = "Users_SEQ")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "email",unique=true)
    String email;

    @Column(name = "user_name",unique=true)
    String userName;

    @Column(name = "password")
    String password;

    @Column(name = "phone_number",unique=true)
    String phoneNumber;

    @Column(name = "avatar")
    String avatarName;

    @Column(name = "gender")
    String gender;

    @Column(name = "birth_day")
    Instant birthday;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "permission",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<Role> roles = new HashSet<>();

    @Column(name = "activate")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isActive;
}