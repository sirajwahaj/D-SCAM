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

    public Order(String username, String date, String time) {
        this.orderNum = orderNumberCounter++;
        this.username = username;
        this.date = date;
        this.time = time;
        this.description = description;
        this.qty = qty;
        this.price = price;
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

    
    public void loadCustomersOrder(){
        String customerOrder = "textfile/Order.txt";
        File file = new File(customerOrder);


        if(file.exists()){

        List<Order> loadOrders = loadOrdersfromfile();
        for(Order order : loadOrders){
                System.out.println("\n\nOrderNo: " + order.getOrderNum() + "\nKund: " + order.getUsername() + "\nDate: " + order.getDate() + " " + order.getTime());
                System.out.println("------------------------");
                
                for(Product product : order.getProducts()){
                System.out.println("Produkt: " + product.getName());
                System.out.println("Produktbeskrivning: " + product.getDescription());
                System.out.println("Antal: " + product.getQty());
                System.out.println("Pris: " + product.getQtyPrice()+ "\n");//Lägg till produktens pris
                }

                double totalPrice = 0.0;
                for(Product product : order.getProducts()){
                    totalPrice += product.getPrice();
                }
                System.out.println("------------------------");
                System.out.println("Totalt pris: " + totalPrice);
            }
        } else {
            System.out.println("du har inga sparade ordrar");
        }

    }
    public void loadIndividualCustomersOrder(){
        String customerOrder = "textfile/CustomerOrder/Savedorders/"+username+".txt";
        File file = new File(customerOrder);


        if(file.exists() && username.equals(file.getName().replace(".txt",""))){

            List<Order> loadOrders = loadIndividualOrdersfromfile();
            for(Order order : loadOrders){
                System.out.println("\n\nOrderNo: " + order.getOrderNum() + "\nKund: " + order.getUsername() + "\nDate: " + order.getDate() + " " + order.getTime());
                System.out.println("------------------------");
                
                for(Product product : order.getProducts()){
                System.out.println("Produkt: " + product.getName());
                System.out.println("Produktbeskrivning: " + product.getDescription());
                System.out.println("Antal: " + product.getQty());
                System.out.println("Pris: " + product.getQtyPrice()+ "\n");//Lägg till produktens pris
                }

                double totalPrice = 0.0;
                for(Product product : order.getProducts()){
                    totalPrice += product.getPrice();
                }
                System.out.println("------------------------");
                System.out.println("Totalt pris: " + totalPrice);
            }
        } else {
            System.out.println("du har inga sparade ordrar");
        }

    }

    public List<Order> loadOrdersfromfile() {
        List<Order> savedOrders = new ArrayList<>();
        String fileName = "textfile/Order.txt";

        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String orderUsername = parts[0];
                    String orderDate = parts[1];
                    String orderTime = parts[2];
                    String productName = parts[3];
                    String productDescription = parts[4];
                    int productQty = Integer.parseInt(parts[5]);
                    double productPrice = Double.parseDouble(parts[6]);
                    
                    Order existingOrder = null;
                    for (Order order : savedOrders){
                        if(order.getDate().equals(orderDate) &&
                            order.getTime().equals(orderTime)){
                            existingOrder = order;
                                break;
                            }
                    }

                    if(existingOrder == null) {
                        Order newOrder = new Order(orderUsername,orderDate,orderTime);
                        savedOrders.add(newOrder);
                        existingOrder = newOrder;
                    }

                    Product product = new Product(productName, productDescription, productPrice, productQty);
                    existingOrder.getProducts().add(product);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Något gick fel när kund uppgifterna skulle laddas från textfilen: " + e.getMessage());
        }

        return savedOrders;
    }

    public List<Order> loadIndividualOrdersfromfile() {
        List<Order> savedOrders = new ArrayList<>();
        String fileName = "textfile/CustomerOrder/Savedorders/"+username+".txt";

        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String orderUsername = parts[0];
                    String orderDate = parts[1];
                    String orderTime = parts[2];
                    String productName = parts[3];
                    String productDescription = parts[4];
                    int productQty = Integer.parseInt(parts[5]);
                    double productPrice = Double.parseDouble(parts[6]);
                    
                    Order existingOrder = null;
                    for (Order order : savedOrders){
                        if(order.getUsername().equals(orderUsername) && 
                            order.getDate().equals(orderDate) &&
                            order.getTime().equals(orderTime)){
                            existingOrder = order;
                                break;
                            }
                    }

                    if(existingOrder == null) {
                        Order newOrder = new Order(username,orderDate,orderTime);
                        savedOrders.add(newOrder);
                        existingOrder = newOrder;
                    }

                    Product product = new Product(productName, productDescription, productPrice, productQty);
                    existingOrder.getProducts().add(product);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Något gick fel när kund uppgifterna skulle laddas från textfilen: " + e.getMessage());
        }

        return savedOrders;
    }
    

}