package org.launchcode.dispatchtransfer.controllers;


import org.launchcode.dispatchtransfer.models.Patient;
import org.launchcode.dispatchtransfer.models.SocialWorker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="social")
public class SocialController {

    @RequestMapping(value="")
    public String index(Model model){
        model.addAttribute("title", "Schedule a Transfer");
        model.addAttribute(new Patient());
        return "social/index";
    }

    @RequestMapping(value="login")
    public String login(Model model){
        model.addAttribute("title", "Login Social Worker");
        return "social/login";
    }

    @RequestMapping(value="register")
    public String register(Model model){
        model.addAttribute("title", "Register Social Worker");
        model.addAttribute(new SocialWorker());
        return "social/register";
    }
}
