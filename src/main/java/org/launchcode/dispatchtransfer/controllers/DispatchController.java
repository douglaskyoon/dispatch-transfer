package org.launchcode.dispatchtransfer.controllers;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.launchcode.dispatchtransfer.models.Dispatcher;
import org.launchcode.dispatchtransfer.models.Patient;
import org.launchcode.dispatchtransfer.models.SocialWorker;
import org.launchcode.dispatchtransfer.models.data.DispatchDao;
import org.launchcode.dispatchtransfer.models.data.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value="dispatch")
public class DispatchController {

    @Autowired
    private DispatchDao dispatchDao;

    @Autowired
    private PatientDao patientDao;

    public static final String ACCOUNT_SID = "AC93561add1ece928a0f5f27f88e9bc896";
    public static final String AUTH_TOKEN = "16f065045afcc772528e18e0726df4fa";

    @RequestMapping(value="", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("patients", patientDao.findAll());
        model.addAttribute("title", "Transfer Schedule");

        return "dispatch/index";
    }

    @RequestMapping(value="patient", method = RequestMethod.POST)
    public String patient(Model model) {
        model.addAttribute("patients", patientDao.findAll());
        model.addAttribute("title", "Transfer Schedule");
        return "dispatch/index";
    }

    @RequestMapping(value="view/{patientId}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable int patientId) {
        Patient patient = patientDao.findOne(patientId);
        model.addAttribute("patient", patient);
        return "dispatch/view";
    }

    @RequestMapping(value="enroute")
    public String enroute(@RequestParam int patientId) {
        Patient patient = patientDao.findOne(patientId);
        patientDao.delete(patient);
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+13143244732"), new PhoneNumber("+13143102524"), "Ambulance is enroute for room #" + patient.getRoom()).create();

        return "dispatch/enroute";
    }

    @RequestMapping(value="login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("title", "Login Dispatch");
        return "dispatch/login";
    }

    @RequestMapping(value="login", method = RequestMethod.POST)
    public String processlogin(Model model, @RequestParam String username, @RequestParam String password){
        model.addAttribute("title", "Welcome");
        Dispatcher dispatcher = dispatchDao.findByUsername(username);

        if(dispatcher.getPassword().equals(password)){

            return "redirect:";
        }
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
            model.addAttribute("title", "Register Dispatch User");
            return "dispatch/register";

        }
        dispatchDao.save(dispatcher);
        return "redirect:";
    }
}
