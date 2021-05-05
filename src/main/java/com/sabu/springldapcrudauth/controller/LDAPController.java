package com.sabu.springldapcrudauth.controller;

import com.sabu.springldapcrudauth.dto.UserDTO;
import com.sabu.springldapcrudauth.dto.UserSaveRequestDTO;
import com.sabu.springldapcrudauth.service.LdapUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021/05/03
 */
@RestController
@AllArgsConstructor
public class LDAPController {

    private final LdapUserService ldapUserService;

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody UserSaveRequestDTO userSaveRequestDTO) {
        ldapUserService.addUser(userSaveRequestDTO);
        return ResponseEntity.ok("USER CREATED!");
    }

    @PostMapping("/verifyUser")
    public ResponseEntity<?> verify(@RequestBody UserSaveRequestDTO userSaveRequestDTO) {
        UserDTO verify = ldapUserService.verify(userSaveRequestDTO);
        return ResponseEntity.ok(verify);
    }

    @GetMapping("/getUser/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username) {
        UserDTO user = ldapUserService.getUser(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        List<UserDTO> users = ldapUserService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/updateUser")
    public ResponseEntity<?> update(@RequestBody UserSaveRequestDTO userSaveRequestDTO) {
        ldapUserService.updateLdapUser(userSaveRequestDTO);
        return ResponseEntity.ok("USER UPDATED!");
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") String userId) {
        ldapUserService.deleteUser(userId);
        return ResponseEntity.ok("DELETED USER " + userId);
    }
}
