package com.snapp.pay.auth.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    @ToString.Exclude
    private String password;

}
