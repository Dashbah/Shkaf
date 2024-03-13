package dashbah.shkaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping({"", "/"})
    public String index() {
        System.out.println("hello from index()");
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        System.out.println("hello from login()");
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
