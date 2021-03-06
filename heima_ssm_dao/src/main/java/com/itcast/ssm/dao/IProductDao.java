package com.itcast.ssm.dao;

import com.itcast.ssm.domain.Permission;
import com.itcast.ssm.domain.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {
    //根据id查询产品
    @Select("select * from product where id = #{id}")
    public Product findById(String id) throws Exception;
    //查询所有产品信息
    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus)"
    +"values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);


    @Delete("delete from product where id = #{id}")
    void deleteById(String id)throws Exception;

    @Delete("delete from order_traveller where orderId in (select orderId from orders where productId = #{productId})")
    void deleteOrderTravellerById(String productId);
}
