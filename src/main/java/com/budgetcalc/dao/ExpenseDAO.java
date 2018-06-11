package com.budgetcalc.dao;

import com.budgetcalc.sql.Expense;

import java.util.List;

public interface ExpenseDAO {

    public List<Expense> getThisMonth();

    public List<Expense> getByMonth(String month);
}
