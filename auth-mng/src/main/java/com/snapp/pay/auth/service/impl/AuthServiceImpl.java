package com.snapp.pay.auth.service.impl;

import com.snapp.pay.auth.payload.JwtAuthResponse;
import com.snapp.pay.auth.payload.request.LoginRequest;
import com.snapp.pay.auth.payload.request.UserRegistrationRequest;
import com.snapp.pay.auth.payload.response.UserResponse;
import com.snapp.pay.auth.security.JwtTokenProvider;
import com.snapp.pay.auth.security.JwtUser;
import com.snapp.pay.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    @Override
    public JwtAuthResponse login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);


        JwtUser loggedInUser = (JwtUser) authentication.getPrincipal();
        final String token = tokenProvider.generateToken(authentication);
        log.info("User {} logged in successfully", loggedInUser.getUsername());
        return new JwtAuthResponse(token, loggedInUser.getUsername(), loggedInUser.getAuthorities().iterator().next().getAuthority());

    }

    @Override
    public UserResponse register(UserRegistrationRequest userRegistrationRequest) {

        return null;
    }
}
