package com.yukismimi.cartservice.repository;

import com.yukismimi.cartservice.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findAllByUserId(long userId);

    CartItem findByUserIdAndProductId(long userId, long id);

    void deleteByUserIdAndChecked(long userId, int checked);
}
