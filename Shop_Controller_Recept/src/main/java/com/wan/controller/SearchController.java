package com.wan.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wan.pojo.Goods;
import com.wan.service.ISearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author 万星明
 * @Date 2019/1/24
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @Reference
    private ISearchService searchService;

    /**
     * 首页关键词查询商品
     * @param model
     * @param keyword
     * @return
     */
    @RequestMapping("/goodsList")
    public String search(Model model,String keyword){
        //调用接口进行关键词查询
        List<Goods> goodsList = searchService.queryGoodsByIndexed(keyword);
        System.out.println("已进入关键词"+keyword+"查询:"+goodsList);

        System.out.println("关键词查询得到集合:"+goodsList);
        //将查询集合数据返回
        model.addAttribute("goodsList",goodsList);

        //返回搜索结果页面
        return "searchlist";
    }


}
