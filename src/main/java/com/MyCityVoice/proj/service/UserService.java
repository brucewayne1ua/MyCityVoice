package com.MyCityVoice.proj.service;

import com.MyCityVoice.proj.model.User;
import org.springframework.stereotype.Service;
import com.MyCityVoice.proj.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){

        return userRepository.findAll();
    }

    public Optional<User> getUser(Long id){

        return userRepository.findById(id);
    }

    public User createUser(User user){

        return userRepository.save(user);
    }

    public User updateUser(User user){

        return userRepository.save(user);
    }

    public User deleteUser(Long id){
        userRepository.deleteById(id);
        return null;
    }
}
