package com.yukismimi.productservice.controller;

import com.yukismimi.common.api.CommonResult;
import com.yukismimi.productservice.dto.GetProductDTO;
import com.yukismimi.productservice.entity.Product;
import com.yukismimi.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/add")
    public CommonResult<String> addProduct(Product product) {
        service.addProduct(product);
        return CommonResult.success("商品创建成功");
    }

    @GetMapping("/all")
    public CommonResult<Page<Product>> listProducts(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                    @RequestParam(value = "size", required = false, defaultValue = "0") Integer size,
                                                    @RequestParam(value = "sort", required = false, defaultValue = "0") Integer sort,
                                                    @RequestParam(value = "min", required = false) BigDecimal min,
                                                    @RequestParam(value = "max", required = false) BigDecimal max) {
        return CommonResult.success(service.listProducts(page, size, sort, min, max));
    }

    @DeleteMapping("/delete/{id}")
    public CommonResult<String> deleteProduct(@PathVariable int id) {
        service.deleteProduct(id);
        return CommonResult.success("商品删除成功");
    }

    @PutMapping("/modify")
    public CommonResult<String> modifyProduct(Product product) {
        service.modifyProduct(product);
        return CommonResult.success("商品更新成功");
    }

    @GetMapping("/find")
    public CommonResult<GetProductDTO> findProduct(@RequestParam("id") Long productId) {
        // TODO: 2020/4/1
        return CommonResult.success(service.findProductById(productId));
    }
}
