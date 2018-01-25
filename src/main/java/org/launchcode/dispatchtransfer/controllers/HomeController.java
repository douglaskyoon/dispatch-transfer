package org.launchcode.dispatchtransfer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("")
public class HomeController {


    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", "Welcome to EMS Dispatch");
        return "index";
    }



}
