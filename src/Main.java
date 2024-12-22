class ExceptionGenerator {
    public void generateNullPointerException() {
        String nullString = null;
        // Wywołanie metody na obiekcie null wywoła NullPointerException
        nullString.length();
    }
}

class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException(String message) {
        super(message);
    }
}

class Account {
    private String owner;
    private int balance;
    private String accountNumber;

    // Konstruktor
    public Account(String owner, int balance, String accountNumber) {
        this.owner = owner;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    // Gettery i settery
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    // Metoda symulująca przelew
    public void transfer(int amount) throws NotEnoughMoneyException {
        if (amount > balance) {
            throw new NotEnoughMoneyException("Brak wystarczających środków na koncie.");
        }
        balance -= amount;
        System.out.println("Przelew w wysokości " + amount + " został wykonany. Aktualny stan konta: " + balance);
    }
}


public class Main {
    public static void main(String[] args) {
        Account account = new Account("Jan Kowalski", 500, "123456789");

        try {
            System.out.println("Stan konta przed przelewem: " + account.getBalance());
            // Próba wykonania przelewu większego niż saldo
            account.transfer(1000);
        } catch (NotEnoughMoneyException e) {
            System.out.println("Wyjątek: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Wystąpił inny wyjątek: " + e.getMessage());
        } finally {
            System.out.println("Stan konta po operacjach: " + account.getBalance());
        }

        ExceptionGenerator generator = new ExceptionGenerator();

        try {
            generator.generateNullPointerException();
        } catch (NullPointerException e) {
            System.out.println("Wystąpił wyjątek: NullPointerException");
            e.printStackTrace(); // Wypisanie pełnego stosu wywołań
            System.out.println(e.toString()); // Wywołanie toString() na obiekcie wyjątku
        }
    }
}