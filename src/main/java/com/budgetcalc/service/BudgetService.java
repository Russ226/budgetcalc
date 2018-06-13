package com.budgetcalc.service;


import com.budgetcalc.sql.Expense;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface BudgetService {
    List<Expense> getThisMonth();

    List<Expense> getByMonth(String month) throws ParseException;

    void newBill(BigDecimal amount, String name);

    void newBillFromAnotherMonth(BigDecimal amount, String name, Date date);

}
