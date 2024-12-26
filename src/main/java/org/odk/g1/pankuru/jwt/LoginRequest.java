package org.odk.g1.pankuru.jwt;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
