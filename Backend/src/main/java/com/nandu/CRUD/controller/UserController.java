package com.nandu.CRUD.controller;

import com.nandu.CRUD.model.User;
import com.nandu.CRUD.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nandu.CRUD.exception.UserNotFoundException;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository repo;
    @PostMapping("/user")
    User newUser(@RequestBody User newUser)
    {
        return  repo.save(newUser);
    }
    @GetMapping("/users")
    List<User> getAllUsers()
    {
        return repo.findAll();
    }
    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id)
    {
        return repo.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }
    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser,@PathVariable Long id)
    {
        return repo.findById(id).map(user -> {
            user.setUsername(newUser.getUsername());
            user.setEmail(newUser.getEmail());
            user.setName(newUser.getName());
            return repo.save(user);
        }).orElseThrow(()-> new UserNotFoundException(id));
    }
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id)
    {
        if(!repo.existsById(id))
        {
            throw new UserNotFoundException(id);
        }
        repo.deleteById(id);
        return "Deleted";
    }
}
