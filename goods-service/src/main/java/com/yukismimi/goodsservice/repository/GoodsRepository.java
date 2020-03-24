package com.yukismimi.goodsservice.repository;

import com.yukismimi.goodsservice.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Integer> {

}
