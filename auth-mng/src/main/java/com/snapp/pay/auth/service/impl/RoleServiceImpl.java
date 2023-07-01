package com.snapp.pay.auth.service.impl;

import com.snapp.pay.auth.exception.RoleNotFoundException;
import com.snapp.pay.auth.model.Role;
import com.snapp.pay.auth.repository.RoleRepository;
import com.snapp.pay.auth.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    @Override
    public Role create(Role role) {
        return repository.save(role);
    }

    @Override
    public Role findByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new RoleNotFoundException(name));
    }

    @Override
    public long rolesCount() {
        return repository.count();
    }
}
