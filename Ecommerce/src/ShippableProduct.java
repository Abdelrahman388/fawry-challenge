package Ecommerce.src;
public class ShippableProduct extends Product implements Shippable {
    private double weight;

    public ShippableProduct(String name, int quantity, double price, String expirationDate, double weight) {
        super(name, quantity, price, expirationDate);
        this.weight = weight;
    }

    public ShippableProduct(String name, int quantity, double price, double weight) {
        super(name, quantity, price);
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public void print() {
        System.out.printf(
                "name: %-15sprice: %-10.2f\tquantity: %-5d\texpiration date: %-12s\tweight: %.2f\n",
                name, price, quantity, expirationDate, weight);
    }

}
