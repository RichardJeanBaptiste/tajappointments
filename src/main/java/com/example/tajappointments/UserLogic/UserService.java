package com.example.tajappointments.UserLogic;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public Boolean checkEmail(String email){
        if(findByEmail(email) == null) {
            return false;
        }

        return true;
    }


    public User create(User user) {

        // if (checkEmail(user.getEmail())) {
        //     throw new EmailAlreadyExistsException("Email already registered: " + user.getEmail());
        // }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already registered: " + user.getEmail());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));


        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            // Safety net for race conditions (two requests slipping past the check above)
            throw new EmailAlreadyExistsException("Email already registered: " + user.getEmail());
        }
        
    }

    
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }
}
