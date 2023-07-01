package com.snapp.pay.auth.payload.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserResponse {

    private String firstName;

    private String lastName;

    private String username;

    private String role;

}
