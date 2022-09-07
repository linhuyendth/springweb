package com.vti.service;

import com.vti.entity.Account;
import com.vti.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository repo;

    public List<Account> getAll() {
        return repo.findAll();
    }

    public void save(Account account) {
        repo.save(account);
    }

    public Account getById(Integer id) throws NotFoundException {
        Optional<Account> optional = repo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new NotFoundException("Could not find any accounts with ID: " + id);
    }

    public void deleteById(Integer id) throws NotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new NotFoundException("Could not find any accounts with ID: " + id);
        }
        repo.deleteById(id);
    }
}
