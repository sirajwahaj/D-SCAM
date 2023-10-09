import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Order {

    private UserSession userSession = UserSession.getInstance();
    private int orderNum;
    private String username;
    private String localDate;
    private String localTime;
    private static int orderNumberCounter = 1;
    private List<Product> products;
    
    public Order(String username, String localDate, String localTime, List<Product> products) {
        this.orderNum = orderNumberCounter++;
        this.username = username;
        this.localDate = localDate;
        this.localTime = localTime;
        this.products = products;

    }

    public UserSession getUserSession() {
        return userSession;
    }



    public int getOrderNum() {
        return orderNum;
    }



    public String getUsername() {
        return username;
    }



    public String getLocalDate() {
        return localDate;
    }



    public String getLocalTime() {
        return localTime;
    }



    public int getOrderNumberCounter() {
        return orderNumberCounter;
    }



    public List<Product> getProducts() {
        return products;
    }


}
