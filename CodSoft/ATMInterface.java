import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(" Deposit successful. New balance: RS." + balance);
        } else {
            System.out.println(" Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(" Withdrawal successful. Remaining balance: RS." + balance);
        } else if (amount > balance) {
            System.out.println(" Insufficient balance!");
        } else {
            System.out.println(" Invalid withdrawal amount!");
        }
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = new BankAccount(1000.0); // Initial balance

        System.out.println(" Welcome to CODSOFT ATM!");

        boolean exit = false;
        while (!exit) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");

            System.out.print(" Your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println(" Your balance: RS." + account.getBalance());
                    break;
                case 2:
                    System.out.print(" Enter amount to deposit: RS.");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print(" Enter amount to withdraw: RS.");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println(" Thank you for using CODSOFT ATM. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println(" Invalid option! Try again.");
            }
        }

        scanner.close();
    }
}
