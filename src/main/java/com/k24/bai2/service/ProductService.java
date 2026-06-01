package com.k24.bai2.service;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public void addProduct(String productName) {
        System.out.println("SERVICE: Đang thêm sản phẩm " + productName);
        // Logic thêm sản phẩm giả lập
    }
}
