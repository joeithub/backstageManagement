package com.itheima.dao;

import com.itheima.domain.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in (select permissionID from role_permission where roleId = #{roleId})")
    public List<Permission> findByRoleId(String roleId);
}
