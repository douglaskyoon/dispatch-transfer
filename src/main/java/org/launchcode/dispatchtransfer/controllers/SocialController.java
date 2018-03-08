package org.launchcode.dispatchtransfer.controllers;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.launchcode.dispatchtransfer.models.Patient;
import org.launchcode.dispatchtransfer.models.SocialWorker;
import org.launchcode.dispatchtransfer.models.data.PatientDao;
import org.launchcode.dispatchtransfer.models.data.SocialWorkerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping(value="social")
public class SocialController {

    @Autowired
    private SocialWorkerDao socialworkerDao;

    @Autowired
    private PatientDao patientDao;

    public static final String ACCOUNT_SID = "AC93561add1ece928a0f5f27f88e9bc896";
    public static final String AUTH_TOKEN = "16f065045afcc772528e18e0726df4fa";

    @RequestMapping(value="")
    public String index(Model model, HttpSession user){
        model.addAttribute("title", "Schedule a Transfer");
        model.addAttribute(new Patient());
        model.addAttribute("user", user.getAttribute("id"));
        return "social/index";
    }

    @RequestMapping(value="sent", method = RequestMethod.POST)
    public String sent(@ModelAttribute @Valid Patient patient, Errors errors, Model model, HttpSession user){
        model.addAttribute("title", "transfer sent");

        if (errors.hasErrors()) {
            model.addAttribute("title", "Schedule a Transfer");
            return "social/index";
        }

        patient.setSocialworker((int) user.getAttribute("id"));
        patientDao.save(patient);
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+13143244732"), new PhoneNumber("+13143102524"), "New Patient").create();

        model.addAttribute("patients", patientDao.findAllBySocialworker((int) user.getAttribute("id")));

        return "social/sent";
    }

    @RequestMapping(value="view/{patientId}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable int patientId) {
        Patient patient = patientDao.findOne(patientId);
        model.addAttribute("patient", patient);
        return "social/view";
    }


    @RequestMapping(value="login", method = RequestMethod.GET)
    public String displaylogin(Model model){
        model.addAttribute("title", "Login Social Worker");
        return "social/login";
    }

    @RequestMapping(value="login", method = RequestMethod.POST)
    public String processlogin(HttpServletRequest req, Model model, @RequestParam String username, @RequestParam String password){
        model.addAttribute("title", "Welcome");
        SocialWorker socialworker = socialworkerDao.findByUsername(username);
        int socialworker_id = socialworker.getId();

        if(socialworker.getPassword().equals(password)){
            HttpSession user = req.getSession();
            user.setAttribute("id", socialworker_id);
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
