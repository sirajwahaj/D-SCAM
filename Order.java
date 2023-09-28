import java.util.Date;
import java.util.List;

public class Order {
    private Customer customer;
    private List<Product> products;
    private double totalAmount;
    private Date orderDate;

    public Order(Customer customer, List<Product> products) {
        this.customer = customer;
        this.products = products;
        // Beräkna totalbelopp och ställ in orderdatum här
    }

    // Getter-metoder för orderinformation
}