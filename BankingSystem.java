import java.util.*;

public class BankingSystem {
    private Map<String, User> users;
    private Map<String, Account> accounts;
    private User currentUser;
    private Scanner scanner;

    public BankingSystem() {
        users = new HashMap<>();
        accounts = new HashMap<>();
        scanner = new Scanner(System.in);

        // Create a default admin user
        users.put("admin", new User("admin", "admin123", "admin"));
    }

    public void start() {
        while (true) {
            System.out.println("Welcome to the Banking System!");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == 1) {
                login();
            } else if (choice == 2) {
                System.out.println("Thank you for using the Banking System. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            System.out.println("Login successful!");
            if (currentUser.getRole().equals("admin")) {
                adminMenu();
            } else {
                userMenu();
            }
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private void adminMenu() {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Create User");
            System.out.println("2. Create Account");
            System.out.println("3. Delete Account");
            System.out.println("4. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == 1) {
                createUser();
            } else if (choice == 2) {
                createAccount();
            } else if (choice == 3) {
                deleteAccount();
            } else if (choice == 4) {
                currentUser = null;
                System.out.println("Logged out successfully.");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void userMenu() {
        while (true) {
            System.out.println("\nUser Menu:");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View Transaction History");
            System.out.println("6. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == 1) {
                viewBalance();
            } else if (choice == 2) {
                deposit();
            } else if (choice == 3) {
                withdraw();
            } else if (choice == 4) {
                transfer();
            } else if (choice == 5) {
                viewTransactionHistory();
            } else if (choice == 6) {
                currentUser = null;
                System.out.println("Logged out successfully.");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void createUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (user/admin): ");
        String role = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose a different username.");
        } else {
            users.put(username, new User(username, password, role));
            System.out.println("User created successfully!");
        }
    }

    private void createAccount() {
        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();
        System.out.print("Enter owner username: ");
        String ownerUsername = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        if (accounts.containsKey(accountId)) {
            System.out.println("Account ID already exists. Please choose a different account ID.");
        } else {
            accounts.put(accountId, new Account(accountId, ownerUsername, initialBalance));
            System.out.println("Account created successfully!");
        }
    }

    private void deleteAccount() {
        System.out.print("Enter account ID to delete: ");
        String accountId = scanner.nextLine();

        if (accounts.containsKey(accountId)) {
            accounts.remove(accountId);
            System.out.println("Account deleted successfully!");
        } else {
            System.out.println("Account ID not found. Please try again.");
        }
    }

    private void viewBalance() {
        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();

        Account account = accounts.get(accountId);
        if (account != null && account.getOwnerUsername().equals(currentUser.getUsername())) {
            System.out.println("Account Balance: " + account.getBalance());
        } else {
            System.out.println("Account ID not found or you do not have access to this account. Please try again.");
        }
    }

    private void deposit() {
        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        Account account = accounts.get(accountId);
        if (account != null && account.getOwnerUsername().equals(currentUser.getUsername())) {
            account.deposit(amount);
            System.out.println("Deposit successful! New balance: " + account.getBalance());
        } else {
            System.out.println("Account ID not found or you do not have access to this account. Please try again.");
        }
    }

    private void withdraw() {
        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        Account account = accounts.get(accountId);
        if (account != null && account.getOwnerUsername().equals(currentUser.getUsername())) {
            if (account.withdraw(amount)) {
                System.out.println("Withdrawal successful! New balance: " + account.getBalance());
            } else {
                System.out.println("Insufficient balance. Please try again.");
            }
        } else {
            System.out.println("Account ID not found or you do not have access to this account. Please try again.");
        }
    }

    private void transfer() {
        System.out.print("Enter your account ID: ");
        String fromAccountId = scanner.nextLine();
        System.out.print("Enter recipient's account ID: ");
        String toAccountId = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        Account fromAccount = accounts.get(fromAccountId);
        Account toAccount = accounts.get(toAccountId);
        if (fromAccount != null && toAccount != null && fromAccount.getOwnerUsername().equals(currentUser.getUsername())) {
            if (fromAccount.transfer(toAccount, amount)) {
                System.out.println("Transfer successful! New balance: " + fromAccount.getBalance());
            } else {
                System.out.println("Insufficient balance. Please try again.");
            }
        } else {
            System.out.println("Account ID not found or you do not have access to this account. Please try again.");
        }
    }

    private void viewTransactionHistory() {
        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();

        Account account = accounts.get(accountId);
        if (account != null && account.getOwnerUsername().equals(currentUser.getUsername())) {
            System.out.println("Transaction History:");
            for (Transaction transaction : account.getTransactionHistory()) {
                System.out.println(transaction);
            }
        } else {
            System.out.println("Account ID not found or you do not have access to this account. Please try again.");
        }
    }
}
