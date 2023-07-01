package com.snapp.pay.auth.service;

import com.snapp.pay.auth.model.Role;

public interface RoleService {

    Role create(Role role);

    Role findByName(String name);

    long rolesCount();

}
