package com.ewalletUserService.controller;

import com.ewalletUserService.dto.UserAccountDto;
import com.ewalletUserService.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserAccountController {

    private UserAccountService accountService;

    @Autowired
    public UserAccountController(UserAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserAccountDto> addUserAccount(@RequestBody UserAccountDto accountDto) {
        return new ResponseEntity<>(accountService.createUserAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccountDto> getUserAccountById(@PathVariable Long id) {
        UserAccountDto accountDto = accountService.getUserAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<UserAccountDto> editUserAccount(@PathVariable Long id, @RequestBody Map<String, Object> values) {
        UserAccountDto accountDto = accountService.editUserAccount(id, values);
        return ResponseEntity.ok(accountDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserAccount(@PathVariable Long id) {
        if(accountService.deleteUserAccount(id)) {
            return ResponseEntity.ok("User account has been deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User account is not found");
        }
    }
}
