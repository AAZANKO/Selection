package by.auditsalution.selection.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String getMainPage() {
        return "Hello";
    }

    @GetMapping("/hello")
    public String getHelloPage() {
        return "Hello";
    }

    @PostMapping("/hello")
    public String getPreparePage() {
        return "Prepare";
    }

}
