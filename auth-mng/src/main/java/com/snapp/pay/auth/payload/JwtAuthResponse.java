package com.snapp.pay.auth.payload;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class JwtAuthResponse {

    private final String accessToken;

    private final String username;

    private String tokenType = "Bearer";

    private final String role;

}
