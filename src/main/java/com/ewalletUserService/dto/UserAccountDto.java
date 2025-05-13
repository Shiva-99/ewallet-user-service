package com.ewalletUserService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
}
