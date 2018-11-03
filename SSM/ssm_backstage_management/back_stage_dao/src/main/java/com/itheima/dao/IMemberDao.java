package com.itheima.dao;

import com.itheima.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface IMemberDao {

    /*根据ID查询*/
    @Select("select * from member where id =#{id}")
    public Member findById(String id);
}
