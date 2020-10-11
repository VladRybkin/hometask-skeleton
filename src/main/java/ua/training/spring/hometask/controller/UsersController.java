package ua.training.spring.hometask.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.facade.UserFacade;
import ua.training.spring.hometask.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/users")
public class UsersController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAll());

        return "users";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute User user, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday) {
        userFacade.saveUser(user, birthday);

        return "redirect:/users";
    }

    @PreAuthorize("hasAuthority('BOOKING_MANAGER')")
    @GetMapping(value = "/remove/{id}")
    public String remove(Model model, @PathVariable Long id) {
        userService.remove(userService.getById(id));
        model.addAttribute("users", userService.getAll());

        return "redirect:/users";
    }

    @GetMapping(value = "/getbyid/{id}")
    public String getById(Model model, @PathVariable Long id) {
        List<User> users = Lists.newArrayList();
        User user = userService.getById(id);
        if (Objects.nonNull(user)) {
            users.add(user);
        }
        model.addAttribute("users", users);

        return "users";
    }

    @GetMapping(value = "/getbyemail")
    public String getByEmail(Model model, @RequestParam String email) {
        List<User> users = Lists.newArrayList();
        User user = userService.getUserByEmail(email);
        if (Objects.nonNull(user)) {
            users.add(user);
        }
        model.addAttribute("users", users);

        return "users";
    }
}
