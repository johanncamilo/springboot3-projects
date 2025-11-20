package com.johann.springmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johann.springmvc.model.User;
import com.johann.springmvc.model.dto.ParamDto;
import com.johann.springmvc.model.dto.UserDto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/var")
public class PathVariableController {

    @GetMapping("una/{message}")
    public ParamDto getBaz(@PathVariable(name = "message") String messageOtro) {

        ParamDto param = new ParamDto();
        param.setMessage(messageOtro);
        return param;

    }

    @GetMapping("varias/{product}/{id}")
    public Map<String, Object> getMix(@PathVariable String product, @PathVariable Long id) {

        Map<String, Object> json = new HashMap<>();
        json.put("product", product);
        json.put("id", id);

        return json;
    }

    @PostMapping
    public UserDto create(@RequestBody User user) {
        UserDto dto = new UserDto();
        dto.setFullname(user.getName() + " " + user.getLastname());
        dto.setTitle("Usuario con correo: " + user.getEmail());
        return dto;
    }

}
