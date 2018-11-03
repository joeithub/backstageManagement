package com.itheima.service.impl;

import com.itheima.dao.IMemberDao;
import com.itheima.domain.Member;
import com.itheima.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private IMemberDao memberDao;

    @Override
    public Member findById(String id) {
        return memberDao.findById(id);
    }
}
