package com.itheima.service;

import com.itheima.domain.Syslog;

import java.util.List;


public interface ISysLogService {

    void save(Syslog syslog);

    List<Syslog> findAll();
}
