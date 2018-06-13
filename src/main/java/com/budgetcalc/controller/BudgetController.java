package com.budgetcalc.controller;

import com.budgetcalc.service.BudgetService;
import com.budgetcalc.sql.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping("/current")
    public String getCurrentMonthsBudget(Model model){
        List<Expense> expenses = budgetService.getThisMonth();
        BigDecimal sum = BigDecimal.valueOf(0);
        for(Expense expense: expenses){
            sum = sum.add(expense.getAmount());
        }
        model.addAttribute("total", sum);
        model.addAttribute("expenses", expenses);

        return "budgetTable";
    }


}
