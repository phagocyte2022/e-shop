package edu.trainingprojects.eshop.controllers;

import edu.trainingprojects.eshop.dto.UserDTO;
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

    @GetMapping
    public String userList(Model model){
        model.addAttribute("user", userService.getAll());
        return "userList";
    }


    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new UserDTO());
        return "user";
    }

    @PostMapping("/new")
    public String saveUser (UserDTO dto, Model model){
        if (userService.save(dto)){
            return "/redirect:/user";

        } else {
            model.addAttribute("user", dto);

            return "user";
        }

    }

}
