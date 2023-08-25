package by.auditsalution.selection.controller;

import by.auditsalution.selection.model.AuditRisk;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ReplacementAccountController {

    @GetMapping("/replacement-account")
    public String getInputDataPage(HttpSession session, Model model) {
        return "InputData";
    }

    @PostMapping("/replacement-account")
    public String connectToDatabaseController(@RequestParam("risk") AuditRisk risk,
                                              @RequestParam("factor") String factor,
                                              @RequestParam("level") String level,
                                              @RequestParam("countMaxElement") String countMaxElement,
                                              @RequestParam("countRandomElement") String countRandomElement,
                                              @RequestParam("account") String account,
                                              Model model, HttpSession session) {



        return "";
    }

}
