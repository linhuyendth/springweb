package com.vti.controller;

import com.vti.entity.Department;
import com.vti.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private DepartmentRepository depRepo;

    @GetMapping
    public List<Department> getAll() {
         return depRepo.findAll();
    }

    @PostMapping
    public String create(@RequestBody Department department) {
        depRepo.save(department);
        return "Created!";
    }

    @PutMapping
    public String update(@RequestBody Department department) {
        depRepo.save(department);
        return "Update success!";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Department> department = depRepo.findById(id);

        if (department.isPresent() == false) {
            return "Delete fail! Not found Department has id " + id;
        }

        depRepo.delete(department.get());
        return "Delete success!";
    }
}
