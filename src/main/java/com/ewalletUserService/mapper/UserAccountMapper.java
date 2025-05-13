package com.ewalletUserService.mapper;

import com.ewalletUserService.dto.UserAccountDto;
import com.ewalletUserService.entity.UserAccount;

public class UserAccountMapper {

    public static UserAccount maptoUserAccount(UserAccountDto userAccountDto) {
        return new UserAccount(
                userAccountDto.getId(),
                userAccountDto.getUsername(),
                userAccountDto.getEmail(),
                userAccountDto.getPassword(),
                userAccountDto.getRole()
        );
    }

    public static UserAccountDto mapToUserAccountDto(UserAccount userAccount) {
        return new UserAccountDto(
                userAccount.getId(),
                userAccount.getUsername(),
                userAccount.getEmail(),
                userAccount.getPassword(),
                userAccount.getRole()
        );
    }
}