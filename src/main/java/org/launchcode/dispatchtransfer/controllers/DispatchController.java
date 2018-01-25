package org.launchcode.dispatchtransfer.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="dispatch")
public class DispatchController {

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", "Transfer Schedule");
        return "dispatch/index";
    }

    @RequestMapping(value="login")
    public String login(Model model) {
        model.addAttribute("title", "Login Dispatch");
        return "dispatch/login";
    }

    @RequestMapping(value="register")
    public String register(Model model) {
        model.addAttribute("title", "Register Dispatch User");
        return "dispatch/register";
    }
}
