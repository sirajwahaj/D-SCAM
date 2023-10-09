import java.io.*;
import java.util.*;

public class Order {

    private UserSession userSession = UserSession.getInstance();
    String username;
    String date;
    String time;
    String Product;
    String description;
    int qty;
    double price;
    int orderNum;
    private int orderNumberCounter = 1;



    @Override
    public String toString() {
        return "Order{" +
                "username='" + username + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", Product='" + Product + '\'' +
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
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
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

    public Order(String username, String date, String time, String product, String description, int qty, double price) {
        this.orderNum = orderNumberCounter++;
        this.username = username;
        this.date = date;
        this.time = time;
        this.Product = product;
        this.description = description;
        this.qty = qty;
        this.price = price;
    }


}