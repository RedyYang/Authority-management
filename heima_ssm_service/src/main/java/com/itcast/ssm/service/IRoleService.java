package com.itcast.ssm.service;

import com.itcast.ssm.domain.Permission;
import com.itcast.ssm.domain.Role;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IRoleService {
    public List<Role> findAll() throws Exception;

    public void save(Role role) throws Exception;

    Role findById(String id) throws Exception;

    void deleteById(String id) throws Exception;

    List<Role> findOtherRoleByUserId(String id) throws Exception;

    void addPermissionToRole(String id, String permissionId) throws Exception;

    void deletePermissionById(String permissionId, String roleId)throws Exception;
}
