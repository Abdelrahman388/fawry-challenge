package Ecommerce.src;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingService {
    private double shippingRate =10;
    public List<Shippable> items = new ArrayList<>();
    private Map<String, Integer> groupedItems = new HashMap<>();
    public ShippingService(List<Shippable> items) {
        this.items = items;
    }


    public double calculateShippingCost() {
        double totalShippingCost = 0;
        for (Shippable item : items) {
            totalShippingCost += item.getWeight() * shippingRate;
        }
        return totalShippingCost;
    }

    private void groupItems(){
        for (Shippable item : items) {
            String itemName = item.getName();
            groupedItems.put(itemName, groupedItems.getOrDefault(itemName, 0) + 1);
        }
    }
    

    public void printShippingDetails() {
        double totalWeight = 0;
        System.out.println("\n\n** Shipment notice **");
        groupItems();
        for (Map.Entry<String, Integer> entry : groupedItems.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            double weight = items.stream()
                    .filter(item -> item.getName().equals(itemName))
                    .mapToDouble(Shippable::getWeight)
                    .sum();
            System.out.printf("%dx %-15s  %.2f kg \n", quantity, itemName, weight);
            totalWeight += weight;
        }
        System.out.printf("Total Package Weight: %.2f kg\n", totalWeight);
    }
}
