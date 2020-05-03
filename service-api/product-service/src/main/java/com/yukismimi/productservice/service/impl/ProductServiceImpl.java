package com.yukismimi.productservice.service.impl;

import com.yukismimi.productservice.dto.GetProductDTO;
import com.yukismimi.productservice.entity.Product;
import com.yukismimi.productservice.repository.ProductRepository;
import com.yukismimi.productservice.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public void addProduct(Product product) {
        repository.save(product);
    }

    @Override
    public void modifyProduct(Product product) {
        repository.save(product);
    }

    @Override
    public Page<Product> listProducts(Integer page, Integer size, Integer sort, BigDecimal min, BigDecimal max) {
        PageRequest pageRequest;

        if (sort < 0) {
            pageRequest = PageRequest.of(page - 1, size, Sort.by("price").descending());
        } else if (sort > 0) {
            pageRequest = PageRequest.of(page - 1, size, Sort.by("price").ascending());
        } else {
            pageRequest = PageRequest.of(page - 1, size);
        }

        return repository.findAll(pageRequest);
    }

    @Override
    public void deleteProduct(long id) {
        repository.deleteById(id);
    }

    @Override
    public GetProductDTO findProductById(long productId) {
        GetProductDTO getProductDTO = new GetProductDTO();
        Product product = repository.findById(productId).orElseThrow();
        BeanUtils.copyProperties(product, getProductDTO);
        return getProductDTO;
    }
}
