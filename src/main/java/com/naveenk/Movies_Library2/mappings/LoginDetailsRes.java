package com.naveenk.Movies_Library2.mappings;

import lombok.Data;

@Data
public class LoginDetailsRes {
    private String token;
    private String refreshToken;
}
