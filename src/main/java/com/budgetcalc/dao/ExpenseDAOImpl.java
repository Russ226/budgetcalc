package com.budgetcalc.dao;

import com.budgetcalc.sql.Expense;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.util.calendar.BaseCalendar;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@Repository
public class ExpenseDAOImpl implements ExpenseDAO{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Expense> getThisMonth() {
        Session session = sessionFactory.getCurrentSession();
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;

        String select = "FROM Expense E WHERE MONTH(E.createdOn) = :month";
        Query query = session.createQuery(select, Expense.class);

        query.setParameter("month", currentMonth);

        List<Expense> thisMonthsBudget = query.list();

        return thisMonthsBudget;
    }

    @Override
    public List<Expense> getByMonth(String month) throws ParseException {
        Session session = sessionFactory.getCurrentSession();

        Date date = new SimpleDateFormat("MMMM", Locale.ENGLISH).parse(month);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        String select = "FROM Expense E WHERE MONTH(E.createdOn) = :month";
        Query query = session.createQuery(select, Expense.class);

        query.setParameter("month", cal.get(Calendar.MONTH) + 1);

        List<Expense> monthBudget = query.list();

        return monthBudget;
    }

    @Override
    public void newBill(BigDecimal amount, String name) {
        Session session = sessionFactory.getCurrentSession();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        Expense newExpense = new Expense(amount, name, date);

        try{
            session.save(newExpense);
        }catch(Exception e){
            e.printStackTrace();
        }

    }



}
