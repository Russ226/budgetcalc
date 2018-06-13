package com.budgetcalc.dao;

import com.budgetcalc.sql.Expense;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

public interface ExpenseDAO {

    List<Expense> getThisMonth();

    List<Expense> getByMonth(String month) throws ParseException;

    void newBill(BigDecimal amount, String name);



}
