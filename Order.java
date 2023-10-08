import java.io.*;
import java.util.*;

public class Order {
    private UserSession userSession = UserSession.getInstance();
    String username = userSession.getUsername();
    Date todayDate = new Date();

    private int orderNumber;
    private List<Product> products;
    private double totalPrice;

    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
        this.products = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
        totalPrice += product.getPrice();
    }

    public void removeProduct(Product product) {
        products.remove(product);
        totalPrice -= product.getPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order Number: ").append(orderNumber).append("\n");
        sb.append("Products:\n");
        for (Product product : products) {
            sb.append("- ").append(product.getName()).append(": ").append(product.getPrice()).append("\n");
        }
        sb.append("Total Price: ").append(totalPrice);
        return sb.toString();
    }
    public Order(){
    }

}