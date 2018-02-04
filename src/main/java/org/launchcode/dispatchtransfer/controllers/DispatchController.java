package org.launchcode.dispatchtransfer.controllers;


import org.launchcode.dispatchtransfer.models.Dispatcher;
import org.launchcode.dispatchtransfer.models.SocialWorker;
import org.launchcode.dispatchtransfer.models.data.DispatchDao;
import org.launchcode.dispatchtransfer.models.data.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping(value="dispatch")
public class DispatchController {

    @Autowired
    private DispatchDao dispatchDao;

    @Autowired
    private PatientDao patientDao;

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("patients", patientDao.findAll());
        model.addAttribute("title", "Transfer Schedule");

        return "dispatch/index";
    }

    @RequestMapping(value="login")
    public String login(Model model) {
        model.addAttribute("title", "Login Dispatch");
        return "dispatch/login";
    }

    @RequestMapping(value="register", method = RequestMethod.GET)
    public String displayregister(Model model) {
        model.addAttribute("title", "Register Dispatch User");
        model.addAttribute(new Dispatcher());
        return "dispatch/register";
    }

    @RequestMapping(value="register", method = RequestMethod.POST)
    public String processRegister(@ModelAttribute @Valid Dispatcher dispatcher, Errors errors, Model model,
                                  @RequestParam String verify){

        if (errors.hasErrors() || !dispatcher.getPassword().equals(verify)) {
            model.addAttribute("title", "Register Dispatcher User");
            return "social/register";

        }
        dispatchDao.save(dispatcher);
        return "redirect:";
    }
}
