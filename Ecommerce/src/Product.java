package Ecommerce.src;
public class Product {
    String name;
    int quantity;
    double price;
    String expirationDate; 

    public Product(String name, int quantity, double price, String expirationDate) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.expirationDate = expirationDate;
    }

    public Product(String name, int quantity, double price) {
        setName(name);
        setQuantity(quantity);
        setPrice(price);
        setExpirationDate("Not Expirable");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void print() {
        System.out.printf(
                "name: %-15sprice: %-10.2f\tquantity: %-5d\texpiration date: %-12s\n",
                name, price, quantity, expirationDate);    }
}
