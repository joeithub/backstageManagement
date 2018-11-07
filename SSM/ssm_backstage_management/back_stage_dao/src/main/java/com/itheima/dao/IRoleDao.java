package com.itheima.dao;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;

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

    @Select("select * from role")
    public List<Role> findAll();

    @Insert("insert into role(roleName,roleDesc)values(#{roleName},#{roleDesc})")
    public void save(Role role);

    @Select("select * from role where id = #{roleId}")
    Role findById(String roleId);


    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId} )")
    List<Permission> findOtherPermissions(String roleId);

    @Insert("insert into role_permission (roleId,permissionId)values(#{roleId},#{permissionId})")
    void addPermissionToRole(String roleId, String permissionId);
}
