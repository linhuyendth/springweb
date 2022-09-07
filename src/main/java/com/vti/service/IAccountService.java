package com.vti.service;

import com.vti.entity.Account;

import java.util.List;

public interface IAccountService {

    List<Account> getAll();

    void save(Account account);

    Account getById(Integer id) throws NotFoundException;

    void deleteById(Integer id) throws NotFoundException;
}
