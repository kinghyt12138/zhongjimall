package com.example.zhongjimall.service.shop;


import com.example.zhongjimall.entity.Product;

import java.util.List;


public interface ProductService {
    public List<Product> getProductList();
    public Product findProductById(long id);
}
