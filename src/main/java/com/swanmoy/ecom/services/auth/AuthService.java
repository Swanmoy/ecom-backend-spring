package com.swanmoy.ecom.services.auth;

import com.swanmoy.ecom.dto.SignupRequest;
import com.swanmoy.ecom.dto.UserDto;

public interface AuthService {
    public UserDto createUser(SignupRequest signupRequest);
    public Boolean hasUserWithEmail(String email);

}
