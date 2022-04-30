package com.example.trello.service.impl;

import com.example.trello.dto.MessageDto;
import com.example.trello.model.Role;
import com.example.trello.model.User;
import com.example.trello.repositories.RoleRepository;
import com.example.trello.repositories.UserRepository;
import com.example.trello.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;

    public final RoleRepository roleRepository;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOneById(id);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findOneByUserName(userName);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public List<User> findUserByRole(String role) {
       List<User> users = userRepository.findAll();
       List<User> userList = new ArrayList<>();
       for(User u: users){
           for (Role role1: u.getRoles()){
               if (role1.getContent().equals(role)){
                   userList.add(u);
                   break;
               }
           }
       }
        return userList;
    }

    @Override
    public User save(User user){
        System.out.println("user input: "+ user.toString());
        return userRepository.save(user);
    }

    @Override
    public MessageDto updateUser(User user) {
        MessageDto message=new MessageDto();
        if(userRepository.findByUserName(user.getUserName()).isPresent()){
            User userDb=userRepository.findByUserName(user.getUserName()).get();
            user.setPassword(userDb.getPassword());
            user.setUserName(userDb.getUserName());
            user.setId(userDb.getId());
            userRepository.save(user);
            message.setMessage("Sửa thông tin người dùng thành công");
            message.setObj(true);
        }else{
            message.setMessage("Sửa thông tin người dùng thất bại");
            message.setObj(false);
        }
        return message;
    }


    @Override
    public boolean detective(Long userId) {
        User user;
        if(userRepository.findById(userId).isPresent()){
            user=userRepository.findById(userId).get();
            user.setActive(!user.isActive());
            userRepository.save(user);
            return true;
        }else{
            return false;
        }

    }
}
