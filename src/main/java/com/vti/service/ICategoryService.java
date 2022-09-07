package com.vti.service;

import com.vti.entity.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> getAll();

    void save(Category category);

    Category getById(Integer id) throws NotFoundException;

    void deleteById(Integer id) throws NotFoundException;
}
