package com.yukismimi.cartservice.service;

import com.yukismimi.cartservice.dto.AddItemDTO;
import com.yukismimi.cartservice.dto.EditCheckAllDTO;
import com.yukismimi.cartservice.dto.ModifyCartItemDTO;
import com.yukismimi.cartservice.entity.CartItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartService {

    List<CartItem> listItems();

    void addItem(AddItemDTO addItemDTO);

    void modifyCartItem(ModifyCartItemDTO modifyCartItemDTO);

    void deleteCartItem(Long id);

    void editCheckAll(EditCheckAllDTO editCheckAllDTO);

    @Transactional
    void deleteChecked();
}
