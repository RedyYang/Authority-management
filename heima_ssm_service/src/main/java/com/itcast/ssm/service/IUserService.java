package com.itcast.ssm.service;

import com.itcast.ssm.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {


    public List<UserInfo> findAll(int page,int size) throws Exception;

    public void save(UserInfo userInfo) throws Exception;

    public UserInfo findById(String id) throws Exception;

    void deleteById(String id) throws Exception;

    void addRoleToUser(String id, String roleId) throws Exception;

    void deleteRoleById(String roleId, String userId) throws Exception;
}
