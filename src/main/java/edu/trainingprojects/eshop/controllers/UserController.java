package edu.trainingprojects.eshop.controllers;

import edu.trainingprojects.eshop.dto.UserDto;
import edu.trainingprojects.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new UserDto());
        return "user";
    }

    @PostMapping("/new")
    public String saveUser (UserDto dto, Model model){
        if (userService.save(dto)){
            return "/redirect:/";

        } else {
            model.addAttribute("user", dto);

            return "user";
        }

    }

}
