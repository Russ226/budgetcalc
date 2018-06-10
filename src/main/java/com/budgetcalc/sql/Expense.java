package com.budgetcalc.sql;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "expenseName")
    private String expenseName;

    @Column(name = "createdOn", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    public Expense(){

    }

    public Expense(BigDecimal amount, String expenseName, Date createdOn) {
        this.amount = amount;
        this.expenseName = expenseName;
        this.createdOn = createdOn;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", amount=" + amount +
                ", expenseName='" + expenseName + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}
