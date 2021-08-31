package com.itcast.ssm.service;

import com.itcast.ssm.domain.Permission;
import com.itcast.ssm.domain.Product;

import java.util.List;

public interface IProductService {
    public List<Product> findAll(int page,int size) throws Exception;

    void save(Product product) throws Exception;

    void deleteById(String id)throws Exception;
}
