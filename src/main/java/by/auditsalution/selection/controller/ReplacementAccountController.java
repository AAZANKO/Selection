package by.auditsalution.selection.controller;

import by.auditsalution.selection.model.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ReplacementAccountController {

    @GetMapping("/replacement-account")
    public String getInputDataPage(HttpSession session, Model model) {
        return "ReplacementAccount";
    }

    @PostMapping("/replacement-account")
    public String connectToDatabaseController(String[] oldAccount, Account[] newAccount, Model model, HttpSession session) {
        if (oldAccount != null && newAccount != null){
            if (newAccount.length != oldAccount.length){
                model.addAttribute("message", "Не указан счет для замены");
                return "ReplacementAccount";
            }
            for (int i = 0; i < oldAccount.length; i++) {
                if (oldAccount[i].trim().isBlank() || newAccount[i].getValue().isBlank()){
                    model.addAttribute("message", "Не указан счет для замены");
                    return "ReplacementAccount";
                }
            }
            Map<String, Account> replacementAccountMap = new HashMap<>();
            for (int i = 0; i < oldAccount.length; i++) {
                replacementAccountMap.put(oldAccount[i], newAccount[i]);
            }
            session.setAttribute("replacementAccountMap", replacementAccountMap);
        }
        return "redirect:/open-card";
    }
}
