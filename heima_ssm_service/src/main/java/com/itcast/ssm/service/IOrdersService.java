package com.itcast.ssm.service;

import com.itcast.ssm.domain.Orders;

import java.util.List;

public interface IOrdersService {
    List<Orders> findAll(int page,int size) throws Exception;

    Orders findById(String ordersId) throws Exception;

    void deleteById(String id)throws Exception;
}
