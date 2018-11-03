package com.itheima.service;

import com.itheima.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IProductService {

    /*查询所有*/
    List<Product> findAll();

    /*保存*/
    public void save(Product product);

    /*根据ID查产品*/
    public Product findById(String id);
}
