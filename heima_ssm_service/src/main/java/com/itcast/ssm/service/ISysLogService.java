package com.itcast.ssm.service;

import com.itcast.ssm.dao.ISysLogDao;
import com.itcast.ssm.domain.SysLog;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ISysLogService {

    public void save(SysLog sysLog)throws Exception;

    List<SysLog> findAll(Integer page,Integer size);


    void deleteSysLog()throws Exception;
}
