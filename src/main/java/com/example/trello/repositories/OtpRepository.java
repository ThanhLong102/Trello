package com.example.trello.repositories;


import com.example.trello.model.OTP;
import com.example.trello.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<OTP, Long> {
    OTP findOneByUser(User user);
}
