package com.yukismimi.goodsservice.controller;

import com.yukismimi.common.api.CommonResult;
import com.yukismimi.goodsservice.entity.Goods;
import com.yukismimi.goodsservice.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService service;

    @PostMapping("/create")
    public CommonResult<String> create(Goods goods) {
        if (service.create(goods) != null) {
            return CommonResult.success("商品创建成功");
        }
        return CommonResult.failed("商品创建失败");
    }

    @GetMapping("/all")
    public CommonResult<List<Goods>> list() {
        return CommonResult.success(service.list());
    }

    @DeleteMapping("/delete/{id}")
    public CommonResult<String> delete(@PathVariable int id) {
        service.delete(id);
        return CommonResult.success("商品删除成功");
    }

    @PutMapping("/modify")
    public CommonResult<String> modify(Goods goods) {
        if (service.update(goods) != null) {
            return CommonResult.success("商品更新成功");
        }
        return CommonResult.failed("商品更新失败");
    }
}
