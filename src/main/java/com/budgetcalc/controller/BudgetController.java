package com.budgetcalc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
public class BudgetController {

    @GetMapping("/current")
    public String getCurrentMonthsBudget(){

        return "budgetTable";
    }


}
