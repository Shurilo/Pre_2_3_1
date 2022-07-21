package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.service.UserService;
import web.users.User;
import web.dao.UserDaoImpl;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String printCars(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "printuser";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") long id, Model model) {
        model.addAttribute("userById", userService.getById(id));
        return "userbyid";
    }

    @GetMapping("/newuser")
    public String newUser(@ModelAttribute ("newPerson") User user) {
        return "newuser";
    }

    @PostMapping
    public String create (@ModelAttribute ("newPerson") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "newuser";
        }
        userService.createUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/update")
    public String updateUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("userById", userService.getById(id));
        return "update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("updateUser") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "update";
        }
        userService.updateUser(user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete (@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/";

    }
}
