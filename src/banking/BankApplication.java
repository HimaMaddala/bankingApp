package banking;
import java.util.ArrayList;
import java.util.Scanner;

public class BankApplication {
    private ArrayList<User> users;
    private User currentUser;

    public BankApplication() {
        this.users = new ArrayList<>();
    }

    private boolean isUserLoggedIn() {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return false;
        }
        return true;
    }

    public void registerUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists.");
                return;
            }
        }
        users.add(new User(username, password));
        System.out.println("Registration successful.");
    }

    public boolean loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                currentUser = user;
                System.out.println("Login successful.");
                return true;
            }
        }
        System.out.println("Invalid credentials.");
        return false;
    }

    public void logoutUser() {
        currentUser = null;
        System.out.println("Logged out successfully.");
    }

    public void openAccount(String holderName, String type, double initialDeposit) {
        if (!isUserLoggedIn()) return;
        String accountNumber = "ACC" + (int) (Math.random() * 10000);
        Account account = new Account(accountNumber, holderName, type, 0.0);
        currentUser.addAccount(account);
        account.deposit(initialDeposit);
        System.out.println("Account opened successfully. Account Number: " + accountNumber);
    }

    public void depositToAccount(String accountNumber, double amount) {
        if (!isUserLoggedIn()) return;
        Account account = currentUser.getAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdrawFromAccount(String accountNumber, double amount) {
        if (!isUserLoggedIn()) return;
        Account account = currentUser.getAccount(accountNumber);
        if (account != null) {
            if (account.withdraw(amount)) {
                System.out.println("Withdrawal successful.");
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void checkBalance(String accountNumber) {
        if (!isUserLoggedIn()) return;
        Account account = currentUser.getAccount(accountNumber);
        if (account != null) {
            System.out.println("Current balance: " + account.checkBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public void generateStatement(String accountNumber) {
        if (!isUserLoggedIn()) return;
        Account account = currentUser.getAccount(accountNumber);
        if (account != null) {
            account.generateStatement();
        } else {
            System.out.println("Account not found.");
        }
    }

    public void applyMonthlyInterest() {
        if (!isUserLoggedIn()) return;
        for (User user : users) {
            for (Account account : user.getAccounts()) {
                account.applyInterest();
            }
        }
        System.out.println("Monthly interest applied to all savings accounts.");
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nWelcome to the Banking Application!");

            if (currentUser == null) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
            } else {
                System.out.println("3. Open Account");
                System.out.println("4. Deposit");
                System.out.println("5. Withdraw");
                System.out.println("6. Check Balance");
                System.out.println("7. View Statement");
                System.out.println("8. Apply Monthly Interest");
                System.out.println("9. Logout");
                System.out.println("10. Exit");
            }

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (currentUser == null) {
                switch (choice) {
                    case 1:
                        System.out.print("Enter a username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter a password: ");
                        String password = scanner.nextLine();
                        registerUser(username, password);
                        break;
                    case 2:
                        System.out.print("Enter username: ");
                        username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        password = scanner.nextLine();
                        loginUser(username, password);
                        break;
                    case 3:
                        exit = true;
                        System.out.println("Thank you for using the Banking Application!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } else {
                switch (choice) {
                    case 3:
                        System.out.print("Enter account holder's name: ");
                        String holderName = scanner.nextLine();
                        System.out.print("Choose account type (savings/checking): ");
                        String type = scanner.nextLine();
                        System.out.print("Enter initial deposit amount: ");
                        double initialDeposit = scanner.nextDouble();
                        openAccount(holderName, type, initialDeposit);
                        break;
                    case 4:
                        System.out.print("Enter account number: ");
                        String accountNumber = scanner.nextLine();
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        depositToAccount(accountNumber, depositAmount);
                        break;
                    case 5:
                        System.out.print("Enter account number: ");
                        accountNumber = scanner.nextLine();
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawalAmount = scanner.nextDouble();
                        withdrawFromAccount(accountNumber, withdrawalAmount);
                        break;
                    case 6:
                        System.out.print("Enter account number: ");
                        accountNumber = scanner.nextLine();
                        checkBalance(accountNumber);
                        break;
                    case 7:
                        System.out.print("Enter account number: ");
                        accountNumber = scanner.nextLine();
                        generateStatement(accountNumber);
                        break;
                    case 8:
                        applyMonthlyInterest();
                        break;
                    case 9:
                        logoutUser();
                        break;
                    case 10:
                        exit = true;
                        System.out.println("Thank you for using the Banking Application!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }

        scanner.close();
    }


    public static void main(String[] args) {
        BankApplication bankApp = new BankApplication();
        bankApp.start();
    }
}
