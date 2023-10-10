import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class ShoppingCart {
    private UserSession userSession = UserSession.getInstance();
    private List<Product> products;
    private String username = userSession.getUsername();

    public ShoppingCart() {

        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public List<Product> getProducts() {
        return products;
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
            System.out.println("Något gick fel när kund uppgifterna skulle laddas från textfilen: " + e.getMessage());
        }

        return savedOrders;
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
                System.out.println("Pris: " + product.getQtyPrice()+ "\n");
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
            System.out.println("Något gick fel när kund uppgifterna skulle laddas från textfilen: " + e.getMessage());
        }

        return savedOrders;
    }



    public void saveIndividualPurchaseToFile() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String username = userSession.getUsername();
        String fileName = "textfile/CustomerOrder/Savedorders/" + username + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            List<Product> productsInCart = products;

            if (!productsInCart.isEmpty()) {

                for (Product product : productsInCart) {
                    String orderProductString = username + "," +
                            localDate + "," +
                            localTime.format(timeFormatter) + "," +
                            product.getName() + "," +
                            product.getDescription() + "," +
                            product.getQty() + "," +
                            product.getQtyPrice();
                    writer.write(orderProductString);
                    writer.newLine();
                    writer.flush();

                }
                writer.close();
            } else {
                System.out.println("Det finns inget att spara");
            }
        } catch (IOException e) {
            System.out.println("Något gick fel när du skulle spara ordern: " + e.getMessage());
        }
    }


    public void savePurchaseToFile() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String username = userSession.getUsername();
        String fileName = "textfile/Order.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            List<Product> productsInCart = products;

            if (!productsInCart.isEmpty()) {
                for (Product product : productsInCart) {
                    String orderProductString = username + "," +
                            localDate + "," +
                            localTime.format(timeFormatter) + "," +
                            product.getName() + "," +
                            product.getDescription() + "," +
                            product.getQty() + "," +
                            product.getQtyPrice();
                    writer.write(orderProductString);
                    writer.newLine();
                    writer.flush();
                
                }
                writer.close();
            } else {
                System.out.println("Det finns inget att spara");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Något gick fel när du skulle spara ordern: " + e.getMessage());
        }
    }

}




