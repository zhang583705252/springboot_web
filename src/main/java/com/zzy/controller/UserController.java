package com.zzy.controller;

import com.zzy.entity.User;
import com.zzy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/find/{id}")
    public String findUserById(Model model,@PathVariable("id") Integer id){
        System.out.println("进入handler");
        User user = userService.findById(id);
        model.addAttribute("user",user);
        System.out.println("跳转页面");
        return "first";
    }
    @GetMapping("/delete")
    public String deleteUser(Integer id){
        System.out.println("根据id删除");
        return "redirect:/login.html";
    }
}
