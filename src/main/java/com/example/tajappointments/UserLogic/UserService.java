package com.example.tajappointments.UserLogic;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

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

    public void editUser(User user) {
        userRepository.save(user);
    }

    public void addToBusinessId(UUID userId, UUID businessId) {
        User x = getUserById(userId);

        if (x.getBusinessIds() == null) {
            x.setBusinessIds();
        }

        x.getBusinessIds().add(businessId);

        userRepository.save(x);
    }

    
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserById(UUID id) {
        return userRepository.getUserById(id);
    }

    

}
