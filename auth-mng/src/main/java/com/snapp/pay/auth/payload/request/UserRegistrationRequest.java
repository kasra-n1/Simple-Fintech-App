package com.snapp.pay.auth.payload.request;

import com.snapp.pay.auth.annotation.Password;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserRegistrationRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String username;

    @NotBlank
    @Password
    @ToString.Exclude
    private String password;

}
