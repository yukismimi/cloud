package com.yukismimi.productservice.service;

import com.yukismimi.productservice.dto.GetProductDTO;
import com.yukismimi.productservice.entity.Product;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public interface ProductService {

    void addProduct(Product product);

    void modifyProduct(Product product);

    Page<Product> listProducts(Integer page, Integer size, Integer sort, BigDecimal min, BigDecimal max);

    void deleteProduct(long id);

    GetProductDTO findProductById(long productId);
}
