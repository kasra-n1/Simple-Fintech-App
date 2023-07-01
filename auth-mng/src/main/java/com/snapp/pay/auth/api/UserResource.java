package com.snapp.pay.auth.api;

import com.snapp.pay.auth.mapper.UserMapper;
import com.snapp.pay.auth.payload.request.UserRegistrationRequest;
import com.snapp.pay.auth.payload.response.UserResponse;
import com.snapp.pay.auth.service.UserService;
import com.snapp.pay.commons.payload.response.GeneralResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {

    private final UserService userService;

    private final UserMapper dataMapper;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'TELLER')")
    @PostMapping(value = {"/customer/register"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GeneralResponse<UserResponse> registerCustomer(@Valid @RequestBody UserRegistrationRequest registerDto) {

        log.info("Register customer api invocation => {}", registerDto);
        return new GeneralResponse(dataMapper.toUserResponse(userService.register(dataMapper.toUser(registerDto), "CUSTOMER")));

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = {"/teller/register"})
    public GeneralResponse<UserResponse> registerTeller(@Valid @RequestBody UserRegistrationRequest registerDto) {

        log.info("Register teller api invocation => {}", registerDto);
        return new GeneralResponse(dataMapper.toUserResponse(userService.register(dataMapper.toUser(registerDto), "TELLER")));

    }

}
