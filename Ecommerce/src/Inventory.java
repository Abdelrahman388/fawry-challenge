package Ecommerce.src;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    static List<Product> products;

    public Inventory() {
        products = new ArrayList<>();
    }

    public static void showInventory (){
        System.out.println("============================== Current Stock ==============================");
        for(Product p : products){
            p.print();
        }
        System.out.println("===========================================================================");

    }

    public static Product getByName(String name) {
        for (Product p : products) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public static void resetInventory() {
        if (products != null) {
            products.clear();
        } else {
            products = new ArrayList<>();
        }
        ShippableProduct cheese = new ShippableProduct("Cheese", 48, 10.0, "2026-01-01", 0.2);
        Product scratchCards = new Product("Scratch Cards", 100, 5.5);
        ShippableProduct TV = new ShippableProduct("TV", 4, 500.0, 25.0);
        Product phone = new Product("Phone", 10, 300.0);
        ShippableProduct biscuits = new ShippableProduct("Biscuits", 20, 2.0, "2026-01-01", 0.35);
        
        products.add(cheese);
        products.add(biscuits);
        products.add(scratchCards);
        products.add(TV);
        products.add(phone);
        System.out.println("Inventory has been reset.");
    }
}
