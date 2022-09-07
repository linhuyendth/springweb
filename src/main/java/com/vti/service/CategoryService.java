package com.vti.service;

import com.vti.entity.Category;
import com.vti.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository repo;

    public List<Category> getAll() {
        return repo.findAll();
    }

    public void save(Category category) {
        repo.save(category);
    }

    public Category getById(Integer id) throws NotFoundException {
        Optional<Category> optional = repo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new NotFoundException("Could not find any categories with ID: " + id);
    }

    public void deleteById(Integer id) throws NotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new NotFoundException("Could not find any categories with ID: " + id);
        }
        repo.deleteById(id);
    }
}
