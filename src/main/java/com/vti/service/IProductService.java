package com.vti.service;

import com.vti.entity.Product;

import java.util.List;

public interface IProductService {

    List<Product> getAll();

    void save(Product product);

    Product getById(Integer id) throws NotFoundException;

    void deleteById(Integer id) throws NotFoundException;
}

