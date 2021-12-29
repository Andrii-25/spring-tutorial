package com.spring.tutorial.controller;

import com.spring.tutorial.entity.UserEntity;
import com.spring.tutorial.exceptions.UserAlreadyExistsException;
import com.spring.tutorial.exceptions.UserNotFoundException;
import com.spring.tutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registerUser(@RequestBody UserEntity user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok().body("User successfully saved!");
        } catch(UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Error!");
        }
    }

    @GetMapping
    public ResponseEntity getOneUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(userService.getOneUser(id));
        } catch(UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body("Error!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(userService.deleteUser(id));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Error!");
        }
    }
}
