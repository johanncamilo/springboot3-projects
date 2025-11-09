package com.johann.springmvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johann.springmvc.model.User;
import com.johann.springmvc.model.dto.UserDto;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("details")
    public UserDto getUserDetails() {
        User user = new User("Johann", "Belos", "johann@gmail.com");
        UserDto userDto = new UserDto();

        userDto.setTitle("User Details from DTO");
        userDto.setFullname(user.getName() + " " + user.getLastname());

        return userDto;
    }
    
    @GetMapping("users")
    public List<UserDto> getUsers() {
        User user1 = new User("Gaby", "Ch", "gaby@gmail.com");
        User user2 = new User("Ana", "Lopez", "ana@gmail.com");
        User user3 = new User("Juan", "Perez", "juan@gmail.com");

        List<User> users = List.of(user1, user2, user3);

        return users.stream().map(u -> {
            UserDto dto = new UserDto();
            dto.setTitle("User from DTO");
            dto.setFullname(u.getName() + " " + u.getLastname());
            return dto;
        }).toList();
    }
    

    @GetMapping("/details-map")
    public Map<String, Object> details() {
        User user = new User("Johann", "Camilo", "johanncamilo@gmail.com"); // tambien se puede retornar user directamente
        Map<String, Object> body = new HashMap<>();
        body.put("name", user.getName());
        body.put("email", user.getEmail());
        body.put("title", user.getLastname());
        return body;
    }
}
