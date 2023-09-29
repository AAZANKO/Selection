package by.auditsalution.selection.controller;

import by.auditsalution.selection.model.Account;
import by.auditsalution.selection.model.Card;
import by.auditsalution.selection.model.InitialData;
import by.auditsalution.selection.service.CalculateService;
import by.auditsalution.selection.service.ExcelService;
import by.auditsalution.selection.service.OutputFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CalculateController {

    private final CalculateService calculateService;

    private final OutputFileService outputFileService;

    private final ExcelService excelService;

    @GetMapping("/calculate")
    public String getPackage(Model model, HttpSession session){

        // TODO: 16.09.2023 закомитанно на время теста!
//        if (session.getAttribute("saldoListMap") == null){
//            model.addAttribute("message", "Не выбрано ни однаго документа");
//            return "redirect:/open-saldo";
//        }
        return "Calculate";
    }

    @PostMapping("/calculate")
    public String calculate(HttpSession session, Model model){
        InitialData initialValue = (InitialData) session.getAttribute("initialValue");
        Map<Account, List<Card>> accountListMap = (Map<Account, List<Card>>) session.getAttribute("accountListMap");
        try {
            calculateService.calculate(initialValue, accountListMap);
            model.addAttribute("message", "Отчет сформирован");
        } catch (Exception e){
            model.addAttribute("message", e.getMessage());
            return "Calculate";
        }
        return "Calculate";
    }

}
