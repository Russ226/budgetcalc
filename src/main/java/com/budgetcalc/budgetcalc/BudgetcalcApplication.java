package com.budgetcalc.budgetcalc;

import com.budgetcalc.controller.BudgetController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:Context.xml")
@ComponentScan(basePackageClasses = {BudgetController.class})
public class BudgetcalcApplication {

    public static void main(String[] args) {
        SpringApplication.run(BudgetcalcApplication.class, args);
    }
}
