import java.io.*;
import java.util.*;

public class Order {
    private UserSession userSession = UserSession.getInstance();
    String username;
    String date;
    String time;
    String product;
    String description;
    int qty;
    double price;
    int orderNum;
    private static int orderNumberCounter = 1;
    private List<Product> products;

    public Order(String username, String date, String time, int qty) {
        this.orderNum = orderNumberCounter++;
        this.username = username;
        this.date = date;
        this.time = time;
        this.description = description;
        this.qty = qty;
        this.price = price;
        this.product = product;
        this.products = new ArrayList<>();
    }


    @Override
    public String toString() {
        return "Order{" +
                "username='" + username + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", Product='" + product + '\'' +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String products) {
        products = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOrderNum() {
        return orderNum;
    }
    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        if (product != null) {
            products.add(product);
        }
    }
    

}