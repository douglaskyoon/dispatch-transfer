package org.launchcode.dispatchtransfer.controllers;


import org.launchcode.dispatchtransfer.models.Patient;
import org.launchcode.dispatchtransfer.models.SocialWorker;
import org.launchcode.dispatchtransfer.models.data.SocialWorkerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.*;

import javax.validation.Valid;
import java.lang.annotation.Annotation;

@Controller
@RequestMapping(value="social")
public class SocialController {

    @Autowired
    private SocialWorkerDao socialworkerDao;

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

    @RequestMapping(value="login")
    public String login(Model model, @RequestParam String username, @RequestParam String password){
        SocialWorker user = socialworkerDao.findByUsername(username);
        if (user.getPassword().equals(password)){
            return "redirect:";
        }
        return "social/login";
    }

    @RequestMapping(value="register", method = RequestMethod.GET)
    public String displayRegister(Model model){
        model.addAttribute("title", "Register Social Worker");
        model.addAttribute(new SocialWorker());
        return "social/register";
    }

    @RequestMapping(value="register", method = RequestMethod.POST)
    public String processRegister(@ModelAttribute @Valid SocialWorker socialworker, Errors errors, Model model,
                                  @RequestParam String verify){

        if (errors.hasErrors() || !socialworker.getPassword().equals(verify)) {
            model.addAttribute("title", "Register Social Worker");
            return "social/register";

        }
        socialworkerDao.save(socialworker);
        return "redirect:";
    }
}
