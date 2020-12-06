package ua.training.spring.hometask.controller.restcontroller;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.dto.rest.request.AddUserParameter;
import ua.training.spring.hometask.dto.rest.response.AddUserResult;
import ua.training.spring.hometask.facade.UserFacade;
import ua.training.spring.hometask.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/operations/users")
public class UserOperationsController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private UserService userService;


    @PostMapping(value = "/add")
    public AddUserResult addUser(@RequestBody AddUserParameter addUserParameter) {
        return userFacade.saveUser(addUserParameter);
    }

    @DeleteMapping(value = "/remove/{id}")
    public ResponseEntity remove(@PathVariable Long id) {
        userService.remove(userService.getById(id));

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/getbyid/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping(value = "/getbyemail")
    public User getByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping(value = "/getAll")
    public List<User> getUsers() {
        return Lists.newArrayList(userService.getAll());
    }
}
