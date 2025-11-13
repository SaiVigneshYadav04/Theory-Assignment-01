import java.util.Scanner;

public class BankingApp {

    static class Account {
        int accNumber;
        String holderName;
        double balance;
        String email;
        String phone;

        public Account(int accNumber, String holderName, double balance, String email, String phone) {
            this.accNumber = accNumber;
            this.holderName = holderName;
            this.balance = balance;
            this.email = email;
            this.phone = phone;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposit successful. Current balance: $" + balance);
            } else {
                System.out.println("Error: Deposit amount must be positive.");
            }
        }

        public void withdraw(double amount) {
            if (amount > 0) {
                if (balance >= amount) {
                    balance -= amount;
                    System.out.println("Withdrawal successful. Remaining balance: $" + balance);
                } else {
                    System.out.println("Error: Insufficient funds.");
                }
            } else {
                System.out.println("Error: Withdrawal amount must be positive.");
            }
        }

        public void showDetails() {
            System.out.println("\n--- Account Information ---");
            System.out.println("Account ID: " + accNumber);
            System.out.println("Account Holder: " + holderName);
            System.out.println("Balance: $" + balance);
            System.out.println("Email: " + email);
            System.out.println("Phone: " + phone);
            System.out.println("----------------------------\n");
        }

        public void updateContact(String newEmail, String newPhone) {
            email = newEmail;
            phone = newPhone;
            System.out.println("Contact information updated successfully.");
        }
    }

    static Account[] accounts = new Account[100];
    static int accountCount = 0;
    static int nextAccountNumber = 1001;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("=== Banking Management System ===");
            System.out.println("1. Open a New Account");
            System.out.println("2. Deposit Funds");
            System.out.println("3. Withdraw Funds");
            System.out.println("4. View Account Details");
            System.out.println("5. Update Contact Details");
            System.out.println("6. Exit");
            System.out.print("Enter your selection: ");

            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1: openAccount(); break;
                case 2: depositFunds(); break;
                case 3: withdrawFunds(); break;
                case 4: viewAccountDetails(); break;
                case 5: updateContactDetails(); break;
                case 6: System.out.println("Thank you for choosing our Bank."); break;
                default: System.out.println("Invalid selection. Please try again.");
            }

        } while (choice != 6);
    }

    static void openAccount() {
        System.out.print("Enter full name: ");
        String name = sc.nextLine();
        System.out.print("Enter initial deposit amount: $");
        double balance = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter email address: ");
        String email = sc.nextLine();
        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        accounts[accountCount++] = new Account(nextAccountNumber, name, balance, email, phone);
        System.out.println("Account successfully created. Your Account ID is " + nextAccountNumber);
        nextAccountNumber++;
    }

    static Account findAccount(int accNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].accNumber == accNumber) return accounts[i];
        }
        return null;
    }

    static void depositFunds() {
        System.out.print("Enter your Account ID: ");
        int id = sc.nextInt();
        System.out.print("Enter amount to deposit: $");
        double amount = sc.nextDouble();
        sc.nextLine();

        Account acc = findAccount(id);
        if (acc != null) acc.deposit(amount);
        else System.out.println("Account not found.");
    }

    static void withdrawFunds() {
        System.out.print("Enter your Account ID: ");
        int id = sc.nextInt();
        System.out.print("Enter amount to withdraw: $");
        double amount = sc.nextDouble();
        sc.nextLine();

        Account acc = findAccount(id);
        if (acc != null) acc.withdraw(amount);
        else System.out.println("Account not found.");
    }

    static void viewAccountDetails() {
        System.out.print("Enter your Account ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Account acc = findAccount(id);
        if (acc != null) acc.showDetails();
        else System.out.println("Account not found.");
    }

    static void updateContactDetails() {
        System.out.print("Enter your Account ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Account acc = findAccount(id);
        if (acc != null) {
            System.out.print("Enter new email: ");
            String newEmail = sc.nextLine();
            System.out.print("Enter new phone number: ");
            String newPhone = sc.nextLine();
            acc.updateContact(newEmail, newPhone);
        } else {
            System.out.println("Account not found.");
        }
    }
}
