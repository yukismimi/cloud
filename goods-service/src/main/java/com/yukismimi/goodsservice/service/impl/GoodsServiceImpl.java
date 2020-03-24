package com.yukismimi.goodsservice.service.impl;

import com.yukismimi.goodsservice.entity.Goods;
import com.yukismimi.goodsservice.repository.GoodsRepository;
import com.yukismimi.goodsservice.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository repository;

    @Override
    public Goods create(Goods goods) {
        return repository.save(goods);
    }

    @Override
    public Goods update(Goods goods) {
        return repository.save(goods);
    }

    @Override
    public List<Goods> list() {
        return repository.findAll();
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
