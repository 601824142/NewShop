package com.wan.service;

import com.wan.pojo.Goods;

import java.util.List;

/**
 * @Author 万星明
 * @Date 2019/1/24
 */
public interface ISearchService {

    //根据关键字查询商品集合(使用solr中间件)
    List<Goods> queryGoodsByIndexed(String keyword);

    //添加商品时,将商品同步到solr索引库
    int insertGoodsIndexed(Goods goods);

}
