package ua.training.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.spring.hometask.dto.RegistrationUserForm;
import ua.training.spring.hometask.facade.UserFacade;

import java.time.LocalDate;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping
    public String getLogin() {
        return "registration";
    }

    @PostMapping
    public String registerUser(@ModelAttribute RegistrationUserForm userForm,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfTheBirth) {
        userFacade.registerUser(userForm, dateOfTheBirth);

        return "redirect:/welcome";
    }
}
