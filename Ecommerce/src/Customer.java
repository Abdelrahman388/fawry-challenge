package Ecommerce.src;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Customer {
    String name;
    double balance;

    public Customer() {
        this.name = "Unknown";
        this.balance = 0.0; // Default balance
    }

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    private String getCurrentDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);
        return formattedDate;
    }

    private boolean isProductExpired(String expirationDate) {
        if (expirationDate.equals("Not Expirable")) {
            return false;
        }

        try {
            LocalDate expDate = LocalDate.parse(expirationDate);
            LocalDate today = LocalDate.now();
            return expDate.isBefore(today);
        } catch (Exception e) {
            // If date parsing fails, consider it expired for safety
            return true;
        }
    }

    public void addBalance(double amount) {
        if (amount < 0) {
            System.out.println("Cannot add a negative amount to balance.");
            return;
        }
        balance += amount;
        System.out.println("Balance updated. New balance: " + balance);
    }

    public void checkout(Cart cart) {
        if (cart.getProducts().isEmpty()) {
            System.err.println("Error: Cart is empty. Please add products before checkout.");
            return;
        }

        for (Map.Entry<Product, Integer> entry : cart.getProducts().entrySet()) {
            Product product = entry.getKey();
            double stockQuantity = product.getQuantity();
            int cartQuantity = entry.getValue();
            if (cartQuantity > stockQuantity) {
                System.err.println("Error: Quantity needed of Product " + product.getName()
                        + " is not available in stock. Available quantity: " + stockQuantity);
                return;
            }
            if (isProductExpired(product.getExpirationDate())) {
                System.err.println("Error: Product " + product.getName()
                        + " is not available for checkout due to passing expiration date: "
                        + product.getExpirationDate());
                return;
            }
        }

        double subtotal = cart.getTotalPrice();

        List<Shippable> shippableProducts = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : cart.getProducts().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            if (product instanceof Shippable) {
                for (int i = 0; i < quantity; i++) {
                    shippableProducts.add((Shippable) product);
                }
            }
        }

        double shippingFees = 0;
        ShippingService service = null;
        if (!shippableProducts.isEmpty()) {
            service = new ShippingService(shippableProducts);
            shippingFees = service.calculateShippingCost();
        }

        double totalAmount = subtotal + shippingFees;

        if (totalAmount > balance) {
            System.err.println(
                    "Error: Insufficient balance for checkout. Current balance: " + balance + ", Total cost: " + totalAmount);
            return;
        }

        // Process payment
        balance -= totalAmount;
        System.out.println("Checkout successful! New balance: " + balance);

        // Update inventory
        for (Map.Entry<Product, Integer> entry : cart.getProducts().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            int stockQuantity = product.getQuantity();
            product.setQuantity(stockQuantity - quantity);
        }

        // Print notices
        System.out.println("\n" + getName() + "   " + getCurrentDate());

        if (service != null) {
            service.printShippingDetails();
        }

        System.out.println("\n\n** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : cart.getProducts().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double priceForNItems = product.getPrice() * quantity;
            System.out.printf("%dx %-15s %.0f\n", quantity, product.getName(), priceForNItems);
        }

        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f\n", subtotal);
        if (shippingFees > 0) {
            System.out.printf("Shipping %.0f\n", shippingFees);
        }
        System.out.printf("Amount %.0f\n", totalAmount);
        System.out.printf("Remaining Balance: %.2f\n", getBalance());
    }
}