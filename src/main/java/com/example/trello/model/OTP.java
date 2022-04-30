package com.example.trello.model;

import com.example.trello.core.Constants;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Random;

@Entity
@Data
public class OTP implements Serializable {

    private final static Long EXPIRED_TIME = Constants.OTP.EXPIRED_TIME;

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    private String code;

    private Long issueAt;

    @OneToOne
    private User user;

    public OTP(User user){
        this.user = user;
        Random r = new Random( System.currentTimeMillis() );
        int randomNumber=(10000 + r.nextInt(20000));
        code= String.valueOf(randomNumber);
        issueAt = System.currentTimeMillis();
    }

    public OTP(){
        Random r = new Random( System.currentTimeMillis() );
        int randomNumber=(10000 + r.nextInt(20000));
        code= String.valueOf(randomNumber);
        issueAt = System.currentTimeMillis();
    }

    public boolean isExpired(){
        return this.issueAt + EXPIRED_TIME < System.currentTimeMillis();
    }
}
