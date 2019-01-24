package com.wan.serviceImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.wan.dao.IGoodsDao;
import com.wan.pojo.Goods;
import com.wan.service.IGoodsService;
import com.wan.service.ISearchService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author 万星明
 * @Date 2019/1/24
 */
@Service
public class GoodServiceImpl implements IGoodsService {

    @Autowired
    private IGoodsDao goodsDao;

    @Reference
    private ISearchService searchService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 查询全部商品集合
     * @return
     */
    @Override
    public List<Goods> queryAllGoods() {
        //条件为空,查询全部
        return goodsDao.selectList(null);
    }

    /**
     * 插入商品
     * @param goods
     * @return
     */
    @Override
    @Transactional
    public Goods insertGoods(Goods goods) {
        //后台单独插入商品信息
        goodsDao.insert(goods);

        //同步插入商品信息到搜索索引库
        searchService.insertGoodsIndexed(goods);

        //通知详情工程生成相应的静态详情页
        //参数为(交换机,路由,对象)
        rabbitTemplate.convertAndSend("Goods_Exchange","",goods);

        return goods;
    }

}
