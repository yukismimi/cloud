package com.yukismimi.goodsservice.service;

import com.yukismimi.goodsservice.entity.Goods;

import java.util.List;

public interface GoodsService {

    Goods create(Goods goods);

    Goods update(Goods goods);

    List<Goods> list();

    void delete(int id);
}
