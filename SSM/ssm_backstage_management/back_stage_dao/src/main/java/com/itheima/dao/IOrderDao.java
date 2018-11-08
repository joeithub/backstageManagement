package com.itheima.dao;

import com.itheima.domain.Member;
import com.itheima.domain.Orders;
import com.itheima.domain.Product;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrderDao {


    /*查询所有
    * 指定对应方式
    * */
    @Select("select * from orders")
    @Results(id = "orderMap",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum" , column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId" ,javaType =Product.class ,one=@One(select = "com.itheima.dao.IProductDao.findById")),
    })
    List<Orders> findAll();


    /*根据ID查询*/
    @Select("select * from orders where id = #{id}")
    @Results(id = "orderMap2",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum" , column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId" ,javaType =Product.class ,one=@One(select = "com.itheima.dao.IProductDao.findById")),
            @Result(property = "member", column = "memberId", javaType = Member.class, one = @One(select = "com.itheima.dao.IMemberDao.findById")),
            @Result(property = "travellers", column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.ITravellerDao.findByOrdersId"))

    })
    Orders findById(String id);
}
