package com.budgetcalc.dao;

import com.budgetcalc.sql.Expense;

import java.math.BigDecimal;
import java.util.Date;
import java.text.ParseException;
import java.util.List;

public interface ExpenseDAO {

    List<Expense> getThisMonth();

    List<Expense> getByMonth(String month, int year) throws ParseException;

    void newBill(BigDecimal amount, String name);

    void newBillFromAnotherMonth(BigDecimal amount, String name, Date date);



}
