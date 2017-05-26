package ua.petshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.petshop.dto.UserDto;

@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public ModelAndView showLoginForm(String error, String logout) {
        ModelAndView model = new ModelAndView("login");
        model.addObject(new UserDto());
        if (error != null) {
            model.addObject("error",
                "Username or password is incorrect");
        }
        if (logout != null) {
            model.addObject("message",
                "Logged out successfully");
        }
        return model;
    }
}
