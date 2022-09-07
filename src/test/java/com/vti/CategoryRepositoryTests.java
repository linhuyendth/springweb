package com.vti;

import com.vti.entity.Category;
import com.vti.repository.ICategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class CategoryRepositoryTests {

    @Autowired
    private ICategoryRepository repo;

    @Test
    public void testCreate() {
        Category savedCategory = repo.save(new Category("Honda's"));
        Assertions.assertThat(savedCategory.getId()).isGreaterThan(0);
    }
}
