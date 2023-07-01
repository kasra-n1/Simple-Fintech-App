package com.snapp.pay.auth.service;

import com.snapp.pay.auth.payload.JwtAuthResponse;
import com.snapp.pay.auth.payload.request.LoginRequest;
import com.snapp.pay.auth.payload.request.UserRegistrationRequest;
import com.snapp.pay.auth.payload.response.UserResponse;

public interface AuthService {
    JwtAuthResponse login(LoginRequest loginRequest);

    UserResponse register(UserRegistrationRequest userRegistrationRequest);

}
