package pl.mantiscrab.zebrapuzzlesolver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HelloWorldController {
    @GetMapping("/")
    public String helloWorld(Model model) {
        model.addAttribute("message", "Hello Patryk!");
        return "hello"; // Refers to hello.html template
    }
}
