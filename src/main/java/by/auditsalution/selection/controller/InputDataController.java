package by.auditsalution.selection.controller;

import by.auditsalution.selection.model.AuditRisk;
import by.auditsalution.selection.model.InitialData;
import by.auditsalution.selection.model.SearchType;
import by.auditsalution.selection.util.AccountUtil;
import by.auditsalution.selection.util.PunctuationUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class InputDataController {

    public static final int COUNT_AUDIT_RISK = 3;
    public static final String COUNT_RANDOM_VALUE = "13";
    public static final String COUNT_MAX_VALUE = "5";

    @ModelAttribute("auditRiskMap")
    public Map<String, String> auditRiskMap(){
        return initializationAuditRiskMap();
    }

    /**
     * Захардкожено кол-во элементов выборки
     */
    @ModelAttribute("countRandomElement")
    public String countRandomElement(){
        return COUNT_RANDOM_VALUE;
    }

    @ModelAttribute("countMaxElement")
    public String countMaxElement(){
        return COUNT_MAX_VALUE;
    }

    @GetMapping("/input-data")
    public String getInputDataPage(HttpSession session, Model model) {
        return "InputData";
    }

    @PostMapping("/input-data")
    public String connectToDatabaseController(HttpServletRequest httpServletRequest, ServletRequest request, @RequestParam("risk") AuditRisk risk,
                                              @RequestParam("factor") String factor,
                                              @RequestParam("level") String level,
                                              @RequestParam("countMaxElement") String countMaxElement,
                                              @RequestParam("countRandomElement") String countRandomElement,
                                              @RequestParam("account") String account,
                                              Model model, HttpSession session) {
        Optional<SearchType> searchType = AccountUtil.verificationAccount(account);
        if ("".equals(risk)
                | "".equals(factor.trim())
                | "".equals(level.trim())
                | "".equals(countMaxElement.trim())
                | "".equals(countRandomElement.trim())) {
            model.addAttribute("message", "Одно из полей не заполненно...!!!");
            model.addAttribute("risk", risk);
            model.addAttribute("factor", factor);
            model.addAttribute("level", level);
            model.addAttribute("countMaxElement", countMaxElement);
            model.addAttribute("countRandomElement", countRandomElement);
            model.addAttribute("account", account);
            return "InputData";
        } else if (!searchType.isPresent()) {
            model.addAttribute("message", "Счет введен не правильно!!!");
            model.addAttribute("risk", risk);
            model.addAttribute("factor", factor);
            model.addAttribute("level", level);
            model.addAttribute("countMaxElement", countMaxElement);
            model.addAttribute("countRandomElement", countRandomElement);
            model.addAttribute("account", account);
            return "InputData";
        } else {
            InitialData initialValue = createInitializationValue(risk, factor, level, countMaxElement, countRandomElement, account, searchType.get());
            session.setAttribute("initialValue", initialValue);
            return "redirect:/replacement-account";
        }
    }

    private Map<String, String> initializationAuditRiskMap() {
        Map<String, String> auditRiskMap = new LinkedHashMap<>(COUNT_AUDIT_RISK);
        AuditRisk[] values = AuditRisk.values();
        for (AuditRisk value : values) {
            auditRiskMap.put(value.toString(), value.getDescription());
        }
        return auditRiskMap;
    }

    private InitialData createInitializationValue(AuditRisk risk, String factor, String level, String countMaxElement, String countRandomElement, String account, SearchType accountType) {
        return InitialData.builder()
                .risk(risk)
                .factor(Double.parseDouble(PunctuationUtil.reverseCommaToPoint(factor)))
                .level(Integer.parseInt(level))
                .countMaxElement(Integer.parseInt(countMaxElement))
                .countRandomElement(Integer.parseInt(countRandomElement))
                .account(account)
                .accountType(accountType)
                .build();
    }
}
