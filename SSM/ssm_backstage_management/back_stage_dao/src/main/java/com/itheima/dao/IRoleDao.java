package com.itheima.dao;

import com.itheima.domain.Role;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IRoleDao {

    /*根据用户ID查询出所有的角色*/
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")

    @Results({
        @Result(id = true,property = "id",column = "id"),
        @Result(property = "roleName",column = "roleName"),
        @Result(property = "roleDesc",column = "roleDesc"),
        @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.IPermissionDao.findByRoleId"))
    })
    public List<Role> findByUserId(String userId);
}
