package com.snapp.pay.auth.service;

import com.snapp.pay.auth.model.User;

public interface UserService {

    User register(User user, String role);

    User findByUsername(String username);

    long usersCount();

}
