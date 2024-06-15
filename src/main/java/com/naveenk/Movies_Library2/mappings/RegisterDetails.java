package com.naveenk.Movies_Library2.mappings;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class RegisterDetails {
    private String username;
    private String email;
    private String password;

}
