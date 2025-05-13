package com.ewalletUserService.service;

import com.ewalletUserService.dto.UserAccountDto;

import java.util.Map;

public interface UserAccountService {
    UserAccountDto createUserAccount(UserAccountDto userAccountDto);
    UserAccountDto getUserAccountById(Long id);
    UserAccountDto editUserAccount(Long id, Map<String, Object> values);
    boolean deleteUserAccount(Long id);
}
