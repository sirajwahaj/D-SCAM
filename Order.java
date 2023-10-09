import java.io.*;
import java.util.*;

public class Order {

    private UserSession userSession = UserSession.getInstance();
    int orderNum;
    String username;
    String date;
    String time;

    @Override
    public String toString() {
        return "Order{" +
                "orderNum=" + orderNum +
                ", username='" + username + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", products=" + products +
                '}';
    }

    private int orderNumberCounter = 1;
    private List<Product> products;



    public Order(String username, String date, String time, List<Product> products) {
        this.orderNum = orderNumberCounter++;
        this.username = username;
        this.date = date;
        this.time = time;
        this.products = products;

    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
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
    public List<Product> getProducts() {
        return products;
    }


    }
