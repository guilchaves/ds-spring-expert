package tests.entities;


import entities.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.factory.AccountFactory;

public class AccountTests {

    @Test
    public void depositShouldIncreaseBalanceAndDiscountFeeWhenPositiveAmount() {

        double amount = 200.0;
        double expectedValue = 196.00;
        Account acc = AccountFactory.createEmptyAccount();

        acc.deposit(amount);

        Assertions.assertEquals(expectedValue, acc.getBalance());
    }

    @Test
    public void depositShouldDoNothingWhenNegativeAmount() {
        double amount = -200.0;
        double expectedValue = 100.00;
        Account acc = AccountFactory.createAccount(expectedValue);

        acc.deposit(amount);

        Assertions.assertEquals(expectedValue, acc.getBalance());
    }

    @Test
    public void fullWithdrawShouldClearBalanceAndReturnAllBalance() {

        double expectedValue = 0.0;
        double initialBalance = 800.0;
        Account acc = AccountFactory.createAccount(initialBalance);

        double result = acc.fullWithdraw();

        Assertions.assertEquals(acc.getBalance(), expectedValue);
        Assertions.assertEquals(initialBalance, result);
    }

    @Test
    public void withdrawShouldDecreaseBalanceWhenSufficientBalance() {

        Account acc = AccountFactory.createAccount(800.0);

        acc.withdraw(500.0);

        Assertions.assertEquals(acc.getBalance(), 300.0);
    }

    @Test
    public void withdrawShouldThrowExceptionWhenInsufficientBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Account acc = AccountFactory.createAccount(100.0);
            acc.withdraw(150.00);
        });
    }
}
