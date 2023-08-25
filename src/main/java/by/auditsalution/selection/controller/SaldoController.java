package by.auditsalution.selection.controller;

import by.auditsalution.selection.exception.ServiceException;
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

import static by.auditsalution.selection.model.ExcelFormatType.XLSX;
import static by.auditsalution.selection.model.InputOutputType.INPUT;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SaldoController {

    private final CardServiceImpl openFileService;
    @GetMapping("/open-saldo")
    public String getOpenFilePage() {
        return "OpenSaldo";
    }

    @PostMapping("/open-saldo")
    public String postSubmit(@RequestParam("files") List<MultipartFile> files, Model model, HttpSession session) {
        model.addAttribute("files", files);
        if (!files.get(0).isEmpty()) {
            if (ExcelUtil.isValidNameFiles(files)) {
                try {
                    String pathToFiles = FilePathUtil.getPathAndCreatePackage(INPUT.getDescription(), "saldo");
                    FileUtil.copy(files, pathToFiles);

//                    List<Card1CTemp> card1CTempList = openFileService.convertFileToObject(inputFilePath);
//                    Map<Account, List<Card1C>> accountListMap = openFileService.convertObjectToCards(card1CTempList);
//                    if (session.getAttribute("accountListMap") != null) {
//                        session.removeAttribute("accountListMap");
//                    }
//                    session.setAttribute("accountListMap", accountListMap);
                } catch (ServiceException e){
                    model.addAttribute("message", e.getMessage());
                }
            } else {
                model.addAttribute("message", "Один или несколько файлов не являются файлами Excel");
                return "OpenCard";
            }
        } else {
            model.addAttribute("message", "Вы не выбрали файлы...!!!");
            return "OpenSaldo";
        }
        return "redirect:/input-data";
    }
}