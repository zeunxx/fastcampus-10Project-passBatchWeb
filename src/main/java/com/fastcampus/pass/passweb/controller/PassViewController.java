package com.fastcampus.pass.passweb.controller;

import com.fastcampus.pass.passweb.repository.pass.Pass;
import com.fastcampus.pass.passweb.service.pass.PassService;
import com.fastcampus.pass.passweb.service.user.User;
import com.fastcampus.pass.passweb.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/passes")
@RequiredArgsConstructor
public class PassViewController {

    private final UserService userService;
    private final PassService passService;

    @GetMapping
    public ModelAndView getPasses(@RequestParam("userId") String userId, ModelAndView modelAndView){

        final List<Pass> passes = passService.getPasses(userId);
        final User user = userService.getUser(userId);

        modelAndView.addObject("passes", passes);
        modelAndView.addObject("user",user);
        modelAndView.setViewName("pass/index");
        return modelAndView;
    }

}
