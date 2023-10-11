import java.io.*;
import java.util.*;

public class Order {
    private UserSession userSession = UserSession.getInstance();
    String username;
    String date;
    String time;
    int orderNum;
    private static int orderNumberCounter = 1;
    private List<Product> products;

    public Order(String username, String date, String time) {
        this.orderNum = orderNumberCounter++;
        this.username = username;
        this.date = date;
        this.time = time;
        this.products = new ArrayList<>();
    }


    @Override
    public String toString() {
        return "Order{" +
                "username='" + username + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
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