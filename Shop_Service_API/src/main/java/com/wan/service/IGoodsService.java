package com.wan.service;

import com.wan.pojo.Goods;

import java.util.List;

/**
 * @Author 万星明
 * @Date 2019/1/24
 */
public interface IGoodsService {

    //查询全部商品
    List<Goods> queryAllGoods();

    //插入商品信息
    Goods insertGoods(Goods goods);


}
