package com.itcast.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itcast.ssm.dao.IOrdersDao;
import com.itcast.ssm.dao.IProductDao;
import com.itcast.ssm.domain.Permission;
import com.itcast.ssm.domain.Product;
import com.itcast.ssm.service.IOrdersService;
import com.itcast.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;
    @Autowired
    private IOrdersDao ordersDao;
    @Override
    public List<Product> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }

    @Override
    public void deleteById(String id) throws Exception {
        productDao.deleteOrderTravellerById(id);
        ordersDao.deleteByProductId(id);
        productDao.deleteById(id);
    }

}
