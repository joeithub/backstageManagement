package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao{

    /*查询所有产品*/
    @Select("select * from product")
    List<Product> findAll();
}
