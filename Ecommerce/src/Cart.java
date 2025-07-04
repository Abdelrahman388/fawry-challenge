package Ecommerce.src;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    Map<Product, Integer> products;

    public Cart() {
        products = new HashMap<>();
    }

    public boolean addProduct(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Cannot add zero or negative quantity.");
            return false;
        }

        int currentCartQuantity = products.getOrDefault(product, 0);
        int totalRequested = currentCartQuantity + quantity;

        if (totalRequested > product.getQuantity()) {
            System.out.println("Cannot add " + quantity + " of " + product.getName() +
                    ". Only " + (product.getQuantity() - currentCartQuantity) + " available.");
            return false;
        }

        products.put(product, totalRequested);
        return true;
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void printCart() {
        System.out.println("==== Cart Contents ====");
        if (products.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                System.out.println(
                        "Product: " + product.getName() + ", Quantity: " + quantity + ", Price: " + product.getPrice());
            }
        }
        System.out.println("Total Price: " + getTotalPrice());
        System.out.println("=======================");
    }
}
