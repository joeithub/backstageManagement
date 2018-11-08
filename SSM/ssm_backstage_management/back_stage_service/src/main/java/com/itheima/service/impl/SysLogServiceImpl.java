package com.itheima.service.impl;

import com.itheima.dao.ISysLogDao;
import com.itheima.domain.Syslog;
import com.itheima.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public void save(Syslog syslog) {
        sysLogDao.save(syslog);
    }

    @Override
    public List<Syslog> findAll() {
        return sysLogDao.findAll();
    }
}
