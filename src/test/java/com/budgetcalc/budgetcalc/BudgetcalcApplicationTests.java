package com.budgetcalc.budgetcalc;

import com.budgetcalc.sql.Expense;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(locations={"/Context.xml"})
public class BudgetcalcApplicationTests {

    @Autowired
    SessionFactory sessionFactory;

    @Test
    @Transactional
    public void contextLoads() {
        Session session = sessionFactory.getCurrentSession();

        Expense expense = session.get(Expense.class, 1);

        assertEquals(BigDecimal.valueOf(-60.95), expense.getAmount());

    }

}
