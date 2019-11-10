package ch.ffhs.guesthouse.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("title", "Kata Backend");
        model.addAttribute("persons", 42);
        return "index";
    }
}
