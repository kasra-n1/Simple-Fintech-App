package com.snapp.pay.auth.api;

import com.snapp.pay.auth.payload.JwtAuthResponse;
import com.snapp.pay.auth.payload.request.LoginRequest;
import com.snapp.pay.auth.service.AuthService;
import com.snapp.pay.commons.payload.response.GeneralResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthResource {

    private final AuthService authService;

    @PostMapping(value = {"/login"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GeneralResponse<JwtAuthResponse> login(@RequestBody LoginRequest loginDto) {

        log.info("Login api invocation => {}", loginDto);
        return new GeneralResponse(authService.login(loginDto));

    }

}
