package com.budgetcalc.testQuery;

import com.budgetcalc.dao.ExpenseDAO;
import com.budgetcalc.sql.Expense;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(locations={"/Context.xml"})
public class TestDAO {

    @Autowired
    ExpenseDAO expenseDAO;

    @Test
    @Transactional
    public void testGetCurrentMonth(){

        List<Expense> expenses = expenseDAO.getThisMonth();

        assertEquals(1, expenses.get(0).getId());
        assertEquals(2, expenses.get(1).getId());
        assertEquals(3, expenses.get(2).getId());
    }


    @Test
    @Transactional
    public void testGetBytMonth() throws ParseException {

        List<Expense> expenses = expenseDAO.getByMonth("February");

        assertEquals(4, expenses.get(0).getId());
        assertEquals(5, expenses.get(1).getId());
        assertEquals(6, expenses.get(2).getId());
    }

    // test automatically earses new expense after it finishes
    @Test
    @Transactional
    public void testNewBillWithoutDate(){
        expenseDAO.newBill(BigDecimal.valueOf(-25.00), "food");

        List<Expense> expenses = expenseDAO.getThisMonth();

        assertEquals(4, expenses.size());
        assertEquals("food", expenses.get(3).getExpenseName());
    }


}
