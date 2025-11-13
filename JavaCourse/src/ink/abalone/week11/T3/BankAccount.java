package ink.abalone.week11.T3;

import java.util.Scanner;

public class BankAccount {
    double deposit;
    public BankAccount(double deposit) {
        this.deposit = deposit;
    }
    void withdraw(double amount) throws InsufficientFundsException {
        if (amount<0)throw new InvalidAmountException();
        if (amount <= deposit) {
            deposit -= amount;
            System.out.println("取出了"+amount+"元,余额: "+deposit);
        }else throw new InsufficientFundsException(deposit);
    }
}
class BankAccountTest{
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        Scanner sc = new Scanner(System.in);
        try {
            account.withdraw(sc.nextDouble());
        } catch (InsufficientFundsException e) {
            System.err.println(e.getMessage());
        }
    }
}