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


    public void loadTerminalOrder() {
        String customerOrder = "textfile/CustomerOrder/Savedorders/" + username + ".txt";
        File file = new File(customerOrder);
        ShoppingCart shoppingCart = Customer.getShoppingCart();
        
        if (file.exists() && username.equals(file.getName().replace(".txt", ""))) {
            List<Product> loadOrdersToTerminal = loadOrdersfromfile();
    
            for (Product product : loadOrdersToTerminal) {
                String productName = product.getName();
                String productDescription = product.getDescription();
                double productPrice = product.getQtyPrice();
                int productQty = product.getQty();
                
                Product order = new Product(productName, productDescription, productPrice, productQty);
                
                // Lägg till produkten i din shoppingCart (varukorg)
                shoppingCart.addProduct(order);
            }
        }
    }

    public void loadCustomersOrder(){
        String customerOrder = "textfile/CustomerOrder/Savedorders/" + username + ".txt";
        File file = new File(customerOrder);
        

        if(file.exists() && username.equals(file.getName().replace(".txt", ""))){
        
        List<Product> loadOrders = loadOrdersfromfile();
        for(Product order : loadOrders){
            System.out.println("Username: " + username);
            System.out.println("Product Name: " + order.getName());
            System.out.println("Product Description: " + order.getDescription());
            System.out.println("Product Qty: " + order.getQty());
            System.out.println("Product Price: " + order.getQtyPrice());
            System.out.println("------------------------");
            
        }
        } else {
            System.out.println("du har inga sparade ordrar");
        }

    }

    public List<Product> loadOrdersfromfile() {
        List<Product> savedOrders = new ArrayList<>();
        String fileName = "textfile/CustomerOrder/Savedorders/" + username + ".txt";

        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String productName = parts[0];
                    String productDescription = parts[1];
                    int productQty = Integer.parseInt(parts[2]);
                    double productPrice = Double.parseDouble(parts[3]);
                    Product order = new Product(productName, productDescription, productPrice, productQty);
                    savedOrders.add(order);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Något gick fel när kund uppgifterna skulle laddas från textfilen: " + e.getMessage());
        }

        return savedOrders;
    }
    
    
    public void saveCustomersOrderToFile(List<String> orderProductStrings) {
        String fileName = "textfile/CustomerOrder/Savedorders/" + username + ".txt";

        try {
            File file = new File(fileName);

            if(!file.exists()){
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Filen kunde inte skapas");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String orderProductString : orderProductStrings) {
                
                writer.write(orderProductString);
                writer.newLine();
                writer.flush();
            }
            
            System.out.println("Din order är sparad");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Något gick fel när du skulle spara ordern" + e.getMessage());
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
                }

                System.out.println("Din order är sparad");
            } else {
                System.out.println("Det finns inget att spara");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Något gick fel när du skulle spara ordern: " + e.getMessage());
        }
    }
}


