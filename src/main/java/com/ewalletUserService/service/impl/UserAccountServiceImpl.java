package com.ewalletUserService.service.impl;

import com.ewalletUserService.dto.UserAccountDto;
import com.ewalletUserService.entity.UserAccount;
import com.ewalletUserService.mapper.UserAccountMapper;
import com.ewalletUserService.repository.UserAccountRepository;
import com.ewalletUserService.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccountDto createUserAccount(UserAccountDto userAccountDto) {
        UserAccount userAccount = UserAccountMapper.maptoUserAccount(userAccountDto);
        UserAccount savedUserAccount = userAccountRepository.save(userAccount);
        return UserAccountMapper.mapToUserAccountDto(savedUserAccount);
    }

    @Override
    public UserAccountDto getUserAccountById(Long id) {
        UserAccount userAccount = userAccountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Id Not Found"));
        return UserAccountMapper.mapToUserAccountDto(userAccount);
    }

    @Override
    public UserAccountDto editUserAccount(Long id, Map<String, Object> values) {
        return UserAccountMapper.mapToUserAccountDto(userAccountRepository
                .findById(id)
                .map(userAccount -> {
            if(values.get("username") != null) userAccount.setUsername((String) values.get("username"));
            if(values.get("email") != null) userAccount.setEmail((String) values.get("email"));
            if(values.get("password") != null) userAccount.setPassword((String) values.get("password"));
            if(values.get("role") != null) userAccount.setRole((String) values.get("role"));
            return userAccountRepository.save(userAccount);
        })
                .orElseThrow(() -> new RuntimeException("Id Not Found")));
    }

    @Override
    public boolean deleteUserAccount(Long id) {
        if(!userAccountRepository.existsById(id)) {
            return false;
        } else {
            userAccountRepository.deleteById(id);
            return true;
        }
    }
}
