package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.sql.Savepoint;
import java.util.List;

public interface IProductDao{

    /*查询所有产品*/
    @Select("select * from product")
    List<Product> findAll();


    /*保存*/
  @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void save(Product product);

  /*根据ID查product*/
   @Select("select * from product where id = #{id}")
   public Product findById(String id);
}
