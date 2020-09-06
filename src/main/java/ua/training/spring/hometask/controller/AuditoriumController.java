package ua.training.spring.hometask.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.spring.hometask.domain.Auditorium;
import ua.training.spring.hometask.service.AuditoriumService;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/auditoriums")
public class AuditoriumController {

    @Autowired
    private AuditoriumService auditoriumService;

    @GetMapping
    public String getAuditoriums(Model model) {
        model.addAttribute("auditoriums", auditoriumService.getAll());

        return "auditoriums";
    }

    @GetMapping(value = "/getbyname")
    public String getByName(Model model, @RequestParam String name) {
        List<Auditorium> auditoriums = Lists.newArrayList();
        Auditorium auditorium = auditoriumService.getByName(name);
        if (Objects.nonNull(auditorium)) {
            auditoriums.add(auditorium);
        }
        model.addAttribute("auditoriums", auditoriums);

        return "auditoriums";
    }
}
