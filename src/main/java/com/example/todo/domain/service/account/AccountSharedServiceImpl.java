package com.example.todo.domain.service.account;

import com.example.todo.common.exception.ResourceNotFoundException;
import com.example.todo.common.message.ResultMessage;
import com.example.todo.domain.model.account.Account;
import com.example.todo.domain.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountSharedServiceImpl implements AccountSharedService {

    @Autowired
    AccountRepository accountRepository;

    @Transactional(readOnly = true)
    @Override
    public Account findByUsername(String username) {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            ResultMessage resultMessage = ResultMessage.error("The given account is not found. (username=" + username + ")");
            throw new ResourceNotFoundException(resultMessage);
        }

        return account;
    }
}
