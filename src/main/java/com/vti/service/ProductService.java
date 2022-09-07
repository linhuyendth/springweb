package com.vti.service;

import com.vti.entity.Product;
import com.vti.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository repo;

    public List<Product> getAll() {
        return repo.findAll();
    }

    public void save(Product product) {
        repo.save(product);
    }

    public Product getById(Integer id) throws NotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new NotFoundException("Could not find any products with ID: " + id);
        }
        return repo.findById(id).get();
    }

    public void deleteById(Integer id) throws NotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new NotFoundException("Could not find any categories with ID: " + id);
        }
        repo.deleteById(id);
    }
}
