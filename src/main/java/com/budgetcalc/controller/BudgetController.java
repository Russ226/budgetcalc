package com.budgetcalc.controller;

import com.budgetcalc.service.BudgetService;
import com.budgetcalc.sql.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.Month;
import java.util.Calendar;
import java.util.List;


@Controller
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @ModelAttribute("expense")
    public Expense getExpense() {
        return new Expense();
    }

    @GetMapping("/current")
    public String getCurrentMonthsBudget(Model model){
        List<Expense> expenses = budgetService.getThisMonth();
        BigDecimal sum = BigDecimal.valueOf(0);

        for(Expense expense: expenses){
            sum = sum.add(expense.getAmount());
        }

        Month thisMonth = Month.of(expenses.get(0).getCreatedOn().getMonth() + 1);

        model.addAttribute("current", true);
        model.addAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
        model.addAttribute("month", thisMonth.toString());
        model.addAttribute("total", sum);
        model.addAttribute("expenses", expenses);

        return "budgetTable";
    }

    //default year is current year
    @GetMapping("/previous")
    public String getPreviousMonth(@RequestParam(name="month") String month, @RequestParam(name="year", defaultValue = "1") int year, Model model) throws ParseException {
        List<Expense> expenses;

        if(year ==  1){
            year = Calendar.getInstance().get(Calendar.YEAR);
        }
        Month thisMonth = Month.valueOf(month.toUpperCase());
        Month lastMonth = thisMonth.minus(1);
        if(thisMonth.toString() == "JANUARY"){
            year -= 1;
            expenses = budgetService.getByMonth(lastMonth.toString(),year);
        }else{
            expenses = budgetService.getByMonth(lastMonth.toString(),year);
        }



        BigDecimal sum = BigDecimal.valueOf(0);
        for(Expense expense: expenses){
            sum = sum.add(expense.getAmount());
        }
        model.addAttribute("current", false);
        model.addAttribute("year", year);
        model.addAttribute("month", lastMonth.toString());
        model.addAttribute("total", sum);
        model.addAttribute("expenses", expenses);

        return "budgetTable";

    }

    @GetMapping("/newbill")
    public String newBill(){
        return "newBillForm";
    }

    @PostMapping("/newbill")
    public String postBill(@ModelAttribute Expense expense){
        budgetService.newBill(expense.getAmount(), expense.getExpenseName());
        return "redirect:/current";
    }


}
