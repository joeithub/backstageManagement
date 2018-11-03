package com.itheima.service;

import com.itheima.domain.Member;

public interface IMemberService {

    /*根据ID查询*/
    public Member findById(String id);
}
