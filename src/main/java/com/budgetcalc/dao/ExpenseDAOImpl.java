package com.budgetcalc.dao;

import com.budgetcalc.sql.Expense;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.List;

public class ExpenseDAOImpl implements ExpenseDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Expense> getThisMonth() {
        Session session = sessionFactory.getCurrentSession();
        String currentMonth = String.valueOf(Calendar.getInstance().get(Calendar.MONTH));

        String select = "FROM Expense E WHERE MONTH(E.createdOn) = MONTH(:month)";
        Query query = session.createQuery(select, Expense.class);

        query.setParameter("month", currentMonth);

        List<Expense> thisMonthsBudget = query.list();

        return thisMonthsBudget;
    }

    @Override
    public List<Expense> getByMonth(String month) {
        Session session = sessionFactory.getCurrentSession();

        String select = "FROM Expense E WHERE MONTH(E.createdOn) = MONTH(:month)";
        Query query = session.createQuery(select, Expense.class);

        query.setParameter("month", month);

        List<Expense> monthsBudget = query.list();

        return monthsBudget;
    }
}
