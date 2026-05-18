import java.util.ArrayList;
import java.util.List;

public class Exercise3StoreOrder {

    public static class Order {
        private final String orderNumber;
        private final String customerName;
        private final List<OrderItem> items;

        public Order(String orderNumber, String customerName) {
            this.orderNumber = orderNumber;
            this.customerName = customerName;
            this.items = new ArrayList<>();
        }

        // Static Nested Class: Logically belongs to Order namespace but behaves independently
        public static class OrderItem {
            private final String productName;
            private final double unitPrice;
            private final int quantity;

            public OrderItem(String productName, double unitPrice, int quantity) {
                this.productName = productName;
                this.unitPrice = unitPrice;
                this.quantity = quantity;
            }

            public double total() {
                return unitPrice * quantity;
            }
        }

        public void addItem(OrderItem item) {
            this.items.add(item);
        }

        public double total() {
            return items.stream().mapToDouble(OrderItem::total).sum();
        }

        public OrderSummary toSummary() {
            return new OrderSummary(this.orderNumber, this.customerName, this.total());
        }
    }

    // Summary Record
    public record OrderSummary(String orderNumber, String customerName, double totalAmount) {}

    public static void main(String[] args) {
        Order order = new Order("ORD-99231", "Alice Johnson");

        // Syntax explicitly shows creation of static nested class objects
        Order.OrderItem item1 = new Order.OrderItem("Laptop", 1200.0, 1);
        Order.OrderItem item2 = new Order.OrderItem("Wireless Mouse", 25.50, 2);

        order.addItem(item1);
        order.addItem(item2);

        OrderSummary summary = order.toSummary();
        System.out.println(summary);
    }
}