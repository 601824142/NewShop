package com.wan.rabbitListen;

import com.wan.pojo.Goods;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 万星明
 * @Date 2019/1/24
 */
@Component//使得该类能够被springBoot找到并管理
@RabbitListener(queues = "Goods_Queue")//参数为队列名
public class GoodsRabbitListen {

    @Autowired
    private Configuration configuration;

    /**
     * 接收消息,处理消息的方法
     */
    @RabbitHandler
    public void  handleMessage(Goods goods){
        System.out.println("商品详情页接收到的消息:"+goods);

        //接收对象数据,生成静态页面
        //新建一个Map
        Map<String, Goods> map = new HashMap<>();
        //将转后的Goods商品对象添加进Map
        map.put("goods",goods);

        //准备输出流,输出到static下的goodsPage文件夹下
        String path = this.getClass().getResource("/static/goodspage/").getPath() + goods.getId() + ".html";
        System.out.println("静态页生成后的路径:"+path);

        //JDK1.8的写法,括号中的内容会自动关闭
        try ( Writer out = new FileWriter(path) )
        {

            //准备静态页面
            Template template = configuration.getTemplate("goods.ftl");

            //生成静态页面
            template.process(map,out);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
