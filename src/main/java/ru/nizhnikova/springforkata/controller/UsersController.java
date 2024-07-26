package ru.nizhnikova.springforkata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.nizhnikova.springforkata.model.User;
import ru.nizhnikova.springforkata.service.UserService;

@Controller
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String printUsers(ModelMap model) {
        model.addAttribute("userList", userService.listUsers());
        return "/users";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam("firstName") String firstName,
                          @RequestParam("lastName") String lastName,
                          @RequestParam("email") String email) {
        userService.add(new User(firstName, lastName, email));
        return "redirect:/users";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(userService.findById(id));
        return "redirect:/users";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute User user) {
        if (userService.findById(user.getId()) != null) {
            userService.update(user);
        }
        return "redirect:/users";
    }

    @GetMapping(value = "/updateUser")
    public ModelAndView updateUserbyId(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("userUpdate");
        User user = userService.findById(id);
        mav.addObject("user", user);

        return mav;
    }

}