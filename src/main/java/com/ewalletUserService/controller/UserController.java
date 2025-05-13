package com.ewalletUserService.controller;

import com.ewalletUserService.dto.UserDto;
import com.ewalletUserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUser() {
        List<UserDto> allUsers = userService.getAllUser();
        return ResponseEntity.ok(allUsers);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<UserDto> editUser(@PathVariable Long id, @RequestBody Map<String, Object> values) {
        UserDto accountDto = userService.editUser(id, values);
        return ResponseEntity.ok(accountDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        if(userService.deleteUser(id)) {
            return ResponseEntity.ok("User account has been deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User account is not found");
        }
    }
}
