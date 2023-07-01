package com.snapp.pay.auth.service.impl;

import com.snapp.pay.auth.exception.DuplicateUsernameException;
import com.snapp.pay.auth.exception.RoleNotFoundException;
import com.snapp.pay.auth.exception.UserException;
import com.snapp.pay.auth.exception.UserNotFoundException;
import com.snapp.pay.auth.model.User;
import com.snapp.pay.auth.repository.UserRepository;
import com.snapp.pay.auth.service.RoleService;
import com.snapp.pay.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(User user, String role) {

        if (repository.existsByUsername(user.getUsername())) {
            throw new DuplicateUsernameException();
        }
        try {
            user.setRole(roleService.findByName(role));
        } catch (RoleNotFoundException e) {
            throw new UserException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);

    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public long usersCount() {
        return repository.count();
    }
}
