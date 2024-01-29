package tests.entities;

import entities.Financing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.factories.FinancingFactory;

public class FinancingTests {

    @Test
    public void constructorShouldCreateObjectWhenDataIsValid() {
        double totalAmount = 100000;
        double income = 2000;
        int months = 80;

        Financing f = FinancingFactory.createEmptyFinancing(totalAmount, income, months);

        Assertions.assertEquals(f.getTotalAmount(), totalAmount);
        Assertions.assertEquals(f.getIncome(), income);
        Assertions.assertEquals(f.getMonths(), months);
    }

    @Test
    public void constructorShouldThrowIllegalArgumentExceptionWhenDataIsInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            double totalAmount = 100000;
            double income = 2000;
            int months = 20;
            Financing f = FinancingFactory.createEmptyFinancing(totalAmount, income, months);
        });
    }

    @Test
    public void setTotalAmountShouldUpdateValueWhenDataIsValid() {
        double expectedValue = 10000.0;
        Financing f = FinancingFactory.createFinancing();
        f.setTotalAmount(expectedValue);
        Assertions.assertEquals(f.getTotalAmount(), expectedValue);
    }

    @Test
    public void setTotalAmountShouldThrowIllegalArgumentExceptionWhenDataIsInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            double expectedValue = 200000.0;
            Financing f = FinancingFactory.createFinancing();
            f.setTotalAmount(expectedValue);
        });
    }

    @Test
    public void setIncomeShouldUpdateValueWhenDataIsValid() {
        double expectedValue = 2050;
        Financing f = FinancingFactory.createFinancing();
        f.setIncome(expectedValue);
        Assertions.assertEquals(f.getIncome(), expectedValue);
    }

    @Test
    public void setIncomeAmountShouldThrowIllegalArgumentExceptionWhenDataIsInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            double expectedValue = 200.0;
            Financing f = FinancingFactory.createFinancing();
            f.setIncome(expectedValue);
        });
    }

    @Test
    public void setMonthsShouldUpdateValueWhenDataIsValid() {
        int expectedValue = 90;
        Financing f = FinancingFactory.createFinancing();
        f.setMonths(expectedValue);
        Assertions.assertEquals(f.getMonths(), expectedValue);
    }

    @Test
    public void setMonthsShouldThrowIllegalArgumentExceptionWhenDataIsInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            int expectedValue = 20;
            Financing f = FinancingFactory.createFinancing();
            f.setMonths(expectedValue);
        });
    }

    @Test
    public void entryShouldCalculateEntryCorrectly() {
        double expectedValue = 20000.0;
        Financing f = FinancingFactory.createFinancing();

        Assertions.assertEquals(f.entry(), expectedValue);
    }

    @Test
    public void quotaShouldCalculateQuotaCorrectly() {
        double expectedValue = 1000;
        Financing f = FinancingFactory.createFinancing();

        Assertions.assertEquals(f.quota(), expectedValue);
    }
}
