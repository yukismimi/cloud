package com.yukismimi.cartservice.controller;

import com.yukismimi.cartservice.dto.AddItemDTO;
import com.yukismimi.cartservice.dto.EditCheckAllDTO;
import com.yukismimi.cartservice.dto.ModifyCartItemDTO;
import com.yukismimi.cartservice.entity.CartItem;
import com.yukismimi.cartservice.service.CartService;
import com.yukismimi.common.api.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService service;

    @GetMapping("/all")
    public CommonResult<List<CartItem>> list() {
        return CommonResult.success(service.listItems());
    }

    @PostMapping("/add")
    public CommonResult<String> addCartItem(@RequestBody AddItemDTO addItemDTO) {
        service.addItem(addItemDTO);
        return CommonResult.success(null);
    }

    @PutMapping("/modify")
    public CommonResult<String> modifyCartItem(@RequestBody ModifyCartItemDTO modifyCartItemDTO) {
        service.modifyCartItem(modifyCartItemDTO);
        return CommonResult.success(null);
    }

    @DeleteMapping("/delete")
    public CommonResult<String> deleteCartItems(@RequestParam("id") Long id) {
        service.deleteCartItem(id);
        return CommonResult.success(null);
    }

    @DeleteMapping("/deleteChecked")
    public CommonResult<String> deleteChecked() {
        service.deleteChecked();
        return CommonResult.success(null);
    }

    @PutMapping("/editCheckAll")
    public CommonResult<Void> editCheckAll(@RequestBody EditCheckAllDTO editCheckAllDTO) {
        service.editCheckAll(editCheckAllDTO);
        return CommonResult.success(null);
    }
}
