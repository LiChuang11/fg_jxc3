package com.atguigu.jxc.api;

import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: rime
 * @Date: 2021/5/13 10:30
 **/
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 查询当前库存（可根据商品类别、商品编码或名称搜索）
     * @param page
     * @param rows
     * @param codeOrName
     * @param goodsTypeId
     * @return
     */
    @PostMapping("/listInventory")
    @ResponseBody
    public Map<String,Object> listInventory(@RequestParam Integer page,
                                            @RequestParam Integer rows,
                                            @RequestParam(required = false) String codeOrName,
                                            @RequestParam(required = false) Integer goodsTypeId) {
        Map<String, Object> map = new HashMap<>();
        List<Goods> goods = goodsService.listInventory(page, rows, codeOrName, goodsTypeId);
//        System.out.println("goods = " + goods);
        map.put("rows", goods);
        map.put("total", goods.size());

        return map;
    }
}
