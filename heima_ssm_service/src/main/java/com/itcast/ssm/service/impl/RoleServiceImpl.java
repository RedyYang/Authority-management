package com.itcast.ssm.service.impl;

import com.itcast.ssm.dao.IRoleDao;
import com.itcast.ssm.domain.Permission;
import com.itcast.ssm.domain.Role;
import com.itcast.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;
    @Override
    public List<Role> findAll() throws Exception{
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public Role findById(String id) throws Exception {
        return roleDao.findById(id);
    }

    @Override
    public void deleteById(String id) throws Exception {
        roleDao.deteteUserRoleByRoleId(id);
        roleDao.deleteRolePermissionByRoleId(id);
        roleDao.deleteById(id);
    }

    @Override
    public List<Role> findOtherRoleByUserId(String id) throws Exception {
        return roleDao.findOtherRoleByUserId(id);
    }

    @Override
    public void addPermissionToRole(String id, String permissionId) throws Exception {
        roleDao.addPermissionToRole(id,permissionId);
    }

    @Override
    public void deletePermissionById(String permissionId, String roleId) throws Exception {
        roleDao.deletePermissionById(permissionId,roleId);
    }

}
