public class Exercise2PriceCalculator {

    public record ServiceOrder(String clientName, double hours, double hourRate) {}

    @FunctionalInterface
    public interface PriceStrategy {
        double calculate(ServiceOrder order);
    }

    public static class PriceCalculator {
        public double calculate(ServiceOrder order, PriceStrategy strategy) {
            return strategy.calculate(order);
        }
    }

    public static void main(String[] args) {
        ServiceOrder order = new ServiceOrder("Acme Corp", 10.0, 100.0);
        PriceCalculator calculator = new PriceCalculator();

        // 1. Standard Price Strategy
        PriceStrategy standard = o -> o.hours() * o.hourRate();
        
        // 2. Discount Strategy (10% off)
        PriceStrategy discount = o -> (o.hours() * o.hourRate()) * 0.90;
        
        // 3. Weekend Surcharge Strategy (25% extra)
        PriceStrategy weekend = o -> (o.hours() * o.hourRate()) * 1.25;

        System.out.println("Standard Price: $" + calculator.calculate(order, standard));
        System.out.println("Discounted Price: $" + calculator.calculate(order, discount));
        System.out.println("Weekend Price: $" + calculator.calculate(order, weekend));
    }
}