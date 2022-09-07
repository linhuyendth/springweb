package com.vti.repository;

import com.vti.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer> {

    // kế thừa các hàm findAll, save(create or update), delete... từ JpaRepo

    Long countById(Integer id);

}
