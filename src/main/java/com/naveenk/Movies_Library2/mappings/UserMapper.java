package com.naveenk.Movies_Library2.mappings;

import com.naveenk.Movies_Library2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public User mapper1(RegisterDetails registerDetails){
        User user = new User();
        user.setUsername(registerDetails.getUsername());
        user.setEmail(registerDetails.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(registerDetails.getPassword()));

        return user;
    }
}
