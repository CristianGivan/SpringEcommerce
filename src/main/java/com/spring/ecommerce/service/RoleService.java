package com.spring.ecommerce.service;

import com.spring.ecommerce.model.Role;
import com.spring.ecommerce.model.RoleType;
import com.spring.ecommerce.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRoleByType(RoleType roleType){
        return roleRepository.findRoleByRoleType(roleType);
    }
}
