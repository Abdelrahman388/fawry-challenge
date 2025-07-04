package Ecommerce.src;
public class Tests {

    public static void scenario1() {
        System.out.println("====== SCENARIO 1: Successful Checkout ======");
        Inventory.resetInventory();
        Inventory.showInventory();
        Customer customer1 = new Customer("Mohamed", 850.0);
        Cart cart1 = new Cart();

        Product cheese = Inventory.getByName("Cheese");
        Product scratchCards = Inventory.getByName("Scratch Cards");
        Product TV = Inventory.getByName("TV");
        Product phone = Inventory.getByName("Phone");
        Product biscuits = Inventory.getByName("Biscuits");

        cart1.addProduct(cheese, 2);
        cart1.addProduct(scratchCards, 1);
        cart1.addProduct(TV, 1);
        cart1.addProduct(biscuits, 1);
        cart1.printCart();
        customer1.checkout(cart1);
        System.out.println("\n");
    }

    public static void scenario2() {
        System.out.println("====== SCENARIO 2: Insufficient Balance ======");
        Inventory.resetInventory();
        Customer customer = new Customer("Bob", 75.0);
        Cart cart = new Cart();

        Product TV = Inventory.getByName("TV");
        cart.addProduct(TV, 1);
        cart.printCart();
        customer.checkout(cart);
        System.out.println("\n");
    }

    public static void scenario3() {
        System.out.println("====== SCENARIO 3: Insufficient Stock ======");
        Inventory.resetInventory();
        Customer customer = new Customer("Alice", 3000.0);
        Cart cart = new Cart();

        Product TV = Inventory.getByName("TV");
        cart.addProduct(TV, 5); // Only 4 TVs available
        cart.printCart();
        customer.checkout(cart);
        System.out.println("\n");
    }

    public static void scenario4() {
        System.out.println("====== SCENARIO 4: Empty Cart ======");
        Customer customer = new Customer("Charlie", 500.0);
        Cart cart = new Cart();

        cart.printCart();
        customer.checkout(cart);
        System.out.println("\n");
    }

    public static void scenario5() {
        System.out.println("====== SCENARIO 5: Expired Product ======");
        Inventory.resetInventory();
        Customer customer = new Customer("Diana", 500.0);
        Cart cart = new Cart();

        // Manually create an expired product for testing
        Product expiredCheese = new ShippableProduct("Expired Cheese", 5, 10.0, "2023-01-01", 0.2);
        cart.addProduct(expiredCheese, 1);
        cart.printCart();
        customer.checkout(cart);
        System.out.println("\n");
    }

    public static void scenario6() {
        System.out.println("====== SCENARIO 6: Non-Shippable Products Only ======");
        Inventory.resetInventory();
        Customer customer = new Customer("Eve", 350.0);
        Cart cart = new Cart();

        Product scratchCards = Inventory.getByName("Scratch Cards");
        Product phone = Inventory.getByName("Phone");
        cart.addProduct(scratchCards, 2);
        cart.addProduct(phone, 1);
        cart.printCart();
        customer.checkout(cart);
        System.out.println("\n");
    }

    public static void scenario7() {
        System.out.println("====== SCENARIO 7: Adding Invalid Quantity =======");
        Inventory.resetInventory();
        Customer customer = new Customer("Frank", 100.0);
        Cart cart = new Cart();

        Product cheese = Inventory.getByName("Cheese");
        cart.addProduct(cheese, 0); // Invalid quantity
        cart.addProduct(cheese, -5); // Invalid quantity
        cart.addProduct(cheese, 50); // More than available
        cart.printCart();
        System.out.println("\n");
    }

    public static void scenario8() {
        System.out.println("=== SCENARIO 8: Two Customers Competing for Limited Stock ===");
        Inventory.resetInventory();
        Inventory.showInventory();

        Customer customer1 = new Customer("John", 3000.0);
        Customer customer2 = new Customer("Jane", 1000.0);

        Cart cart1 = new Cart();
        Cart cart2 = new Cart();

        Product TV = Inventory.getByName("TV");

        System.out.println("Customer 1 (John) adds 3 TVs to cart:");
        cart1.addProduct(TV, 3);
        cart1.printCart();

        System.out.println("Customer 2 (Jane) adds 2 TVs to cart:");
        cart2.addProduct(TV, 2);
        cart2.printCart();

        System.out.println("Customer 1 (John) proceeds to checkout:");
        customer1.checkout(cart1);

        System.out.println("Updated inventory after John's purchase:");
        Inventory.showInventory();

        System.out.println("Customer 2 (Jane) tries to checkout:");
        customer2.checkout(cart2);
        System.out.println("\n");
    }

    public static void runAllScenarios() {
        scenario1();
        scenario2();
        scenario3();
        scenario4();
        scenario5();
        scenario6();
        scenario7();
        scenario8();
    }
}
