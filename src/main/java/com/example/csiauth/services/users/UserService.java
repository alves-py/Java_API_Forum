package com.example.csiauth.services.users;

import com.example.csiauth.model.users.User;
import com.example.csiauth.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    final UserRepository userReposity;

    public UserService(UserRepository userReposity) {
        this.userReposity = userReposity;
    }

    public boolean existsUser(String login) {
        return userReposity.existsByLogin(login);
    }

    public void createUser(User user) {
        userReposity.save(user);
    }
}
