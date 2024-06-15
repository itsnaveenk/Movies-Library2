package com.naveenk.Movies_Library2.mappings;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LoginDetailsReq {
    private String username;
    private String password;
}
