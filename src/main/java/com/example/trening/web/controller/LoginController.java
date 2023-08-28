package com.example.trening.web.controller;


import com.example.trening.model.Trainer;
import com.example.trening.model.User;
import com.example.trening.model.exceptions.InvalidUserCredentialsException;
import com.example.trening.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage(Model model) {
        model.addAttribute("bodyContent","login");
        return "login-page";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model) {
        Trainer trainer = null;
        try{
            trainer = this.authService.TrainerLogin(request.getParameter("username"),
                    request.getParameter("password"));
            request.getSession().setAttribute("user", trainer);

            return "redirect:/home";
        }
        catch (InvalidUserCredentialsException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login-page";
        }
    }
}
