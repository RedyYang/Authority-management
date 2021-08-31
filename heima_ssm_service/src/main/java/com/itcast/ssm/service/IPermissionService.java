package com.itcast.ssm.service;

import com.itcast.ssm.domain.Permission;
import com.itcast.ssm.domain.Role;

import java.util.List;

public interface IPermissionService {
    public List<Permission> findAll() throws Exception;

    public void save(Permission permission) throws Exception;

    public Permission findById(String id) throws Exception;

    List<Permission> findOtherPermissionByRoleId(String id) throws Exception;

    void deleteById(String id) throws Exception;
}
