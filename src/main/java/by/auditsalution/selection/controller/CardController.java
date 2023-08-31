package by.auditsalution.selection.controller;

import by.auditsalution.selection.exception.ServiceException;
import by.auditsalution.selection.model.Account;
import by.auditsalution.selection.model.Card;
import by.auditsalution.selection.model.Card1CTemp;
import by.auditsalution.selection.service.impl.CardServiceImpl;
import by.auditsalution.selection.util.ExcelUtil;
import by.auditsalution.selection.util.FilePathUtil;
import by.auditsalution.selection.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static by.auditsalution.selection.model.InputOutputType.INPUT;

// TODO: 26.07.2023 ЗАИСПОЛЬЗОВАТЬ !!!
//@SessionAttributes("accountListMap") // заиспользовать!!! если в коде, по ключу "accountListMap" - что-то клдалется в модель, то оно автоматически попадает в Session
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CardController {
    private final CardServiceImpl openFileService;

    @GetMapping("/open-card")
    public String getOpenFilePage() {
        return "OpenCard";
    }

    @PostMapping("/open-card")
    public String postSubmit(@RequestParam("files") List<MultipartFile> files, Model model, HttpSession session) {
        Map<String, Account> replacementAccountMap = (Map<String, Account>) session.getAttribute("replacementAccountMap");
        if (!files.get(0).isEmpty()) {
            if (ExcelUtil.isValidNameFiles(files)) {
                try {
                    String pathToFiles = FilePathUtil.getPathAndCreatePackage(INPUT.getDescription(), "card");
                    FileUtil.copy(files, pathToFiles);
                    List<Card1CTemp> card1CTempList = openFileService.createCardTempFromFiles(pathToFiles);
                    Map<Account, List<Card>> accountListMap = openFileService.convertToCards(card1CTempList, replacementAccountMap);
                    if (session.getAttribute("accountListMap") != null) {
                        session.removeAttribute("accountListMap");
                    }
                    session.setAttribute("accountListMap", accountListMap);
                } catch (ServiceException e){
                    model.addAttribute("message", e.getMessage());
                }
            } else {
                model.addAttribute("message", "Один или несколько файлов не являются файлами Excel");
                return "OpenCard";
            }
        } else {
            model.addAttribute("message", "Вы не выбрали файлы...!!!");
            return "OpenCard";
        }
        return "redirect:/open-saldo";
    }

}
