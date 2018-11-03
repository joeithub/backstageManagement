package com.itheima.service;

import com.itheima.domain.Product;

import java.util.List;

public interface IProductService {

    /*查询所有*/
    List<Product> findAll();

    /*保存*/
    public void save(Product product);
}
