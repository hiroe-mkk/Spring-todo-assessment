package com.example.todo.domain.service.userdetails;

import com.example.todo.common.exception.ResourceNotFoundException;
import com.example.todo.domain.model.account.Account;
import com.example.todo.domain.service.account.AccountSharedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SampleUserDetailsService implements UserDetailsService {

    @Autowired
    AccountSharedService accountSharedService;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Account account = accountSharedService.findByUsername(username);
            return new SampleUserDetails(account);
        } catch (ResourceNotFoundException exception) {
            throw new UsernameNotFoundException("The user not found. (username=" + username + ")", exception);
        }
    }
}
