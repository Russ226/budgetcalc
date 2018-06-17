package com.budgetcalc.controller;

import com.budgetcalc.service.BudgetService;
import com.budgetcalc.sql.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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

        Month thisMonth = Month.of(expenses.get(0).getCreatedOn().getMonth() + 1);
        System.out.println(thisMonth.toString());
        model.addAttribute("month", thisMonth.toString());
        model.addAttribute("total", sum);
        model.addAttribute("expenses", expenses);

        return "budgetTable";
    }

    // will only go back as far as a year ago
    @GetMapping("/previous")
    public String getPreviousMonth(@RequestParam(name="month") String month, Model model) throws ParseException {
        List<Expense> expenses;


        Month thisMonth = Month.valueOf(month.toUpperCase());
        Month lastMonth = thisMonth.minus(1);
        DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MMMM");

        expenses = budgetService.getByMonth(lastMonth.toString(),Calendar.getInstance().get(Calendar.YEAR));
        model.addAttribute("month", lastMonth.toString());


        BigDecimal sum = BigDecimal.valueOf(0);
        for(Expense expense: expenses){
            sum = sum.add(expense.getAmount());
        }


        model.addAttribute("total", sum);
        model.addAttribute("expenses", expenses);

        return "budgetTable";

    }


}
