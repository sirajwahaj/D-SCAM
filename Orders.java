import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Orders {
    public void listAllOrders(){

        List<Order> loadOrders = loadCustomersOrders();
        
            if(!loadOrders.isEmpty()){
            for(Order order : loadOrders){
                    System.out.println("\n\nOrderNo: " + order.getOrderNum() + "\nKund: " + order.getUsername() + "\nDate: " + order.getDate() + " " + order.getTime());
                    System.out.println("------------------------");
                    
                    for(Product product : order.getProducts()){
                    System.out.println("Produkt: " + product.getName());
                    System.out.println("Produktbeskrivning: " + product.getDescription());
                    System.out.println("Antal: " + product.getQty());
                    System.out.println("Pris: " + product.getPrice()+ "\n");//Lägg till produktens pris
                    }

                    double totalPrice = 0.0;
                    for(Product product : order.getProducts()){
                        totalPrice += product.getPrice();
                    }
                    System.out.println("------------------------");
                    System.out.println("Totalt pris: " + totalPrice);
                }
            } else {
                System.out.println("Det finns inga ordrar");
            }

    }

    public void listCustomersOrders(){

        List<Order> loadOrders = loadCustomersOrders();

            if(!loadOrders.isEmpty()){
            for(Order order : loadOrders){
                System.out.println("\n\nOrderNo: " + order.getOrderNum() + "\nKund: " + order.getUsername() + "\nDate: " + order.getDate() + " " + order.getTime());
                System.out.println("------------------------");
                
                for(Product product : order.getProducts()){
                System.out.println("Produkt: " + product.getName());
                System.out.println("Produktbeskrivning: " + product.getDescription());
                System.out.println("Antal: " + product.getQty());
                System.out.println("Pris: " + product.getPrice()+ "\n");
                }

                double totalPrice = 0.0;
                for(Product product : order.getProducts()){
                    totalPrice += product.getPrice();
                }
                System.out.println("------------------------");
                System.out.println("Totalt pris: " + totalPrice);
            }
        } else {
            System.out.println("du har inga beställda ordrar");
        }

    }

    

    public List<Order> loadCustomersOrders() {
        List<Order> savedOrders = new ArrayList<>();
        String username = UserSession.getInstance().getUsername();
        

        String fileName = "textfile/Order.txt";

        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String orderUsername = parts[0];
                    
                    if (!isUserAdmin() && !orderUsername.equals(username)) {
                        continue; // Hoppa över om användarnamnet inte matchar den inloggade användaren.
                    }
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
    
    private boolean isUserAdmin() {
        UserSession userSession = UserSession.getInstance();
        return userSession.isUserAdmin();
    }
}
