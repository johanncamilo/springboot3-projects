package com.johann.springmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johann.springmvc.model.dto.ParamDto;
import com.johann.springmvc.model.dto.ParamMixDto;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/params")
public class RequestParamsController {

    @GetMapping("foo")
    public ParamDto foo(@RequestParam String message, String otro) {

        ParamDto param = new ParamDto();
        param.setMessage(message + " " + otro);

        return param;
    }

    @GetMapping("bar")
    public ParamMixDto bar(@RequestParam String text, @RequestParam Integer code) {

        ParamMixDto params = new ParamMixDto();
        params.setMessage(text);
        params.setCode(code);
        return params;
    }

    @GetMapping("servlet")
    public ParamMixDto getServlet(HttpServletRequest request) {

        Integer code = 111;
        try {
            code = Integer.valueOf(request.getParameter("code"));
        } catch (NumberFormatException e) {
            // TODO: handle exception
        }

        ParamMixDto params = new ParamMixDto();
        params.setMessage(request.getParameter("message"));
        params.setCode(code);
        return params;
    }

}
