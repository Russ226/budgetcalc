package com.budgetcalc.service;

import com.budgetcalc.dao.ExpenseDAO;
import com.budgetcalc.sql.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private ExpenseDAO expenseDAO;

    @Override
    @Transactional
    public List<Expense> getThisMonth() {
        return expenseDAO.getThisMonth();
    }

    @Override
    @Transactional
    public List<Expense> getByMonth(String month, int year) throws ParseException {
        return expenseDAO.getByMonth(month, year);
    }

    @Override
    @Transactional
    public void newBill(BigDecimal amount, String name) {
        expenseDAO.newBill(amount, name);
    }

    @Override
    @Transactional
    public void newBillFromAnotherMonth(BigDecimal amount, String name, Date date) {
        expenseDAO.newBillFromAnotherMonth(amount, name, date);
    }
}
