public class Exercise4LibraryAccount {

    public static class LibraryAccount {
        private final String readerName;
        private final int borrowedBooks;
        private final int lateDays;

        public LibraryAccount(String readerName, int borrowedBooks, int lateDays) {
            this.readerName = readerName;
            this.borrowedBooks = borrowedBooks;
            this.lateDays = lateDays;
        }

        // Non-static Inner Class: implicitly tied to a specific instance of LibraryAccount
        public class FineCalculator {
            public double calculate() {
                // Accesses outer class fields directly
                return borrowedBooks * lateDays * 1.50;
            }
        }
    }

    @FunctionalInterface
    public interface MessagePrinter {
        void print(String message);
    }

    public static void main(String[] args) {
        LibraryAccount account = new LibraryAccount("Bob Smith", 3, 5);

        // Syntax explicitly links the inner class initialization to the parent account object instance
        LibraryAccount.FineCalculator calculator = account.new FineCalculator();
        double fine = calculator.calculate();

        String message = "Reader: " + account.readerName + " has a late fine of: $" + fine;

        // Lambda implementation of MessagePrinter
        MessagePrinter lambdaPrinter = msg -> System.out.println("[Lambda Printer] " + msg);
        lambdaPrinter.print(message);

        // Anonymous Class implementation of MessagePrinter
        MessagePrinter anonymousPrinter = new MessagePrinter() {
            @Override
            public void print(String msg) {
                System.out.println("[Anonymous Class Printer] " + msg);
            }
        };
        anonymousPrinter.print(message);
    }
}