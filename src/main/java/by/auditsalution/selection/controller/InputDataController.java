package by.auditsalution.selection.controller;

import by.auditsalution.selection.model.SearchType;
import by.auditsalution.selection.model.AuditRisk;
import by.auditsalution.selection.model.InitialData;
import by.auditsalution.selection.util.PunctuationUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if (session.getAttribute("accountListMap") == null){
            model.addAttribute("message", "Не выбрано ни однаго документа");
            return "redirect:/open-saldo";
        }
        return "InputData";
    }

    @PostMapping("/input-data")
    public String connectToDatabaseController(@RequestParam("risk") AuditRisk risk,
                                              @RequestParam("factor") String factor,
                                              @RequestParam("level") String level,
                                              @RequestParam("countMaxElement") String countMaxElement,
                                              @RequestParam("countRandomElement") String countRandomElement,
                                              @RequestParam("account") String account,
                                              Model model, HttpSession session) {
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
            return "inputData";
        } else if (verificationAccount(account) == null) { // TODO: 26.07.2023 >> Optional
            model.addAttribute("message", "Счет введен не правильно!!!");
            model.addAttribute("risk", risk);
            model.addAttribute("factor", factor);
            model.addAttribute("level", level);
            model.addAttribute("countMaxElement", countMaxElement);
            model.addAttribute("countRandomElement", countRandomElement);
            model.addAttribute("account", account);
            return "inputData";
        } else {
            InitialData initialValue = createInitializationValue(risk, factor, level, countMaxElement, countRandomElement, account, verificationAccount(account));
            session.setAttribute("initialValue", initialValue);
            return "redirect:/calculate";
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

    private SearchType verificationAccount(String account) {
        Pattern accountsPattern = Pattern.compile("\\d{2}");
        Pattern subAccountsOneDotsPattern = Pattern.compile("\\d{2}\\.\\d{1,2}");
        Pattern subAccountsTwoDotsPattern  = Pattern.compile("\\d{2}\\.\\d{1,2}\\.\\d{1,2}");
        Matcher accountsPatternMatcher = accountsPattern.matcher(account);
        Matcher subAccountsOneDotsMatcher = subAccountsOneDotsPattern.matcher(account);
        Matcher subAccountsTwoDotsMatcher = subAccountsTwoDotsPattern.matcher(account);
        if (account.isBlank()){
            return SearchType.ALL_ACCOUNTS;
        } else if (accountsPatternMatcher.matches() | subAccountsOneDotsMatcher.matches() | subAccountsTwoDotsMatcher.matches()) {
            return SearchType.ONE_ACCOUNTS;
        } else {
            return null; // TODO: 26.07.2023 >> Optional
        }
    }

}
