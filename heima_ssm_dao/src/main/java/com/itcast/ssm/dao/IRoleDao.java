package com.itcast.ssm.dao;

import com.itcast.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = ("com.itcast.ssm.dao.IPermissionDao.findPermissionByRoleId")))
    })
    public List<Role> findRoleByUserId(String userId)throws Exception;

    @Select("select * from role")
    public List<Role> findAll() throws Exception;

    @Insert("Insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void save(Role role) throws Exception;

    @Select("select * from role where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = ("com.itcast.ssm.dao.IPermissionDao.findPermissionByRoleId")))
    })
    public Role findById(String id) throws Exception;

    @Delete("delete from role where id = #{id}")
    void deleteById(String id)throws Exception;

    @Delete("delete from role_permission where roleId = #{roleId}")
    void deleteRolePermissionByRoleId(String roleId) throws Exception;

    @Delete("delete from users_role where roleId = #{roleId}")
    void deteteUserRoleByRoleId(String roleId) throws Exception;

    @Select("select * from role where id not in (select roleId from users_role where userId = #{userId})")
    List<Role> findOtherRoleByUserId(String id) throws Exception;

    @Insert("insert into role_permission (roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId)throws Exception;

    @Delete("delete from role_permission where permissionId = #{permissionId} and roleId = #{roleId}")
    void deletePermissionById(@Param("permissionId") String permissionId, @Param("roleId") String roleId)throws Exception;
}
