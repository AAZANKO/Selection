package by.auditsalution.selection.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PrepareController {

    @GetMapping("/prepare")
    public String getPackage(){
        return "Prepare";
    }

    @PostMapping("/prepare")
    public String sendRedirect(){
        return "OpenCard";
    }

}
