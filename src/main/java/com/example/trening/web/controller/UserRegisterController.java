package com.example.trening.web.controller;


import com.example.trening.model.enumeration.Role;
import com.example.trening.service.TrainerService;
import com.example.trening.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class UserRegisterController {

    private final UserService userService;
    private final TrainerService trainerService;

    public UserRegisterController(UserService userService, TrainerService trainerService) {
        this.userService = userService;
        this.trainerService = trainerService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent","register");
        return "user-register-template";
    }

    @PostMapping("/user_register")
    public String addPlan(@RequestParam String username,
                          @RequestParam String name,
                          @RequestParam String surname,
                          @RequestParam int age,
                          @RequestParam long height,
                          @RequestParam long weight,
                          @RequestParam String password,
                          @RequestParam String card_number1,
                          @RequestParam String card_number2,
                          @RequestParam String card_number3,
                          @RequestParam String card_number4,
                          @RequestParam String expiration_date1,
                          @RequestParam String expiration_date2,
                          @RequestParam String CVV,
                          @RequestParam String bank_name


    ){

        String card_number = card_number1 + card_number2 + card_number3 + card_number4;

        String expiration_date = expiration_date1 +"/"+ expiration_date2;

        this.trainerService.UserRegister(username, name, surname, age, height, weight, password, card_number, expiration_date, CVV, bank_name, Role.ROLE_CLIENT);

        return "redirect:/";
    }

}
