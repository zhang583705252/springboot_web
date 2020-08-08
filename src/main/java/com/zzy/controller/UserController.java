package com.zzy.controller;

import com.zzy.entity.User;
import com.zzy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @GetMapping("/find/{id}")
    public String findUserById(Model model,@PathVariable("id") Integer id){
        System.out.println("进入handler");
        User user = userService.findById(id);
        logger.debug("user"+user.getAge());
        model.addAttribute("user",user);
        //System.out.println("跳转页面");
        logger.debug("user"+user);
        logger.info("g叫姐姐");
        return "first";
    }
    @GetMapping("/delete")
    public String deleteUser(Integer id){
        System.out.println("根据id删除");
        return "redirect:/login.html";
    }
    @PostMapping("/save")
    public String saveUser(User user){
        return "";
    }
    
}
