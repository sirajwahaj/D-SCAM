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


   /* public void loadTerminalOrder() {
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
    */


    public void loadCustomersOrder(){
        String customerOrder = "textfile/Order.txt";
        File file = new File(customerOrder);


        if(file.exists()){

        List<Order> loadOrders = loadOrdersfromfile();
        for(Order order : loadOrders){
            System.out.println("Date: " + order.getDate() + order.getTime());
            System.out.println("------------------------");
            System.out.println("Kund: " + order.getUsername());
            System.out.println("Produkt: " + order.getProduct());
            System.out.println("produktbeskrivning: " + order.getDescription());
            System.out.println("Antal: " + order.getQty());
            System.out.println("Pris: " + order.getPrice()/order.getQty()+" x "+order.getQty());//Lägg till produktens pris
            System.out.println("------------------------");
            System.out.println("Totalt pris: " + order.getPrice());

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
                System.out.println("Date: " + order.getDate() + order.getTime());
                System.out.println("------------------------");
                System.out.println("Kund: " + order.getUsername());
                System.out.println("Produkt: " + order.getProduct());
                System.out.println("produktbeskrivning: " + order.getDescription());
                System.out.println("Antal: " + order.getQty());
                System.out.println("Pris: " + order.getPrice()/order.getQty()+" x "+order.getQty());//Lägg till produktens pris
                System.out.println("------------------------");
                System.out.println("Totalt pris: " + order.getPrice());

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
                    String username = parts[0];
                    String date = parts[1];
                    String time = parts[2];
                    String productName = parts[3];
                    String productDescription = parts[4];
                    int productQty = Integer.parseInt(parts[5]);
                    double productPrice = Double.parseDouble(parts[6]);
                    Order order = new Order(username,date,time,productName,productDescription,productQty,productPrice);
                    savedOrders.add(order);
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
                    String username = parts[0];
                    String date = parts[1];
                    String time = parts[2];
                    String productName = parts[3];
                    String productDescription = parts[4];
                    int productQty = Integer.parseInt(parts[5]);
                    double productPrice = Double.parseDouble(parts[6]);
                    Order order = new Order(username,date,time,productName,productDescription,productQty,productPrice);
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
        String newLine = "-";

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
                writer.write(newLine);
                writer.newLine();
                writer.close();
                System.out.println("Din order är sparad");
            } else {
                System.out.println("Det finns inget att spara");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Något gick fel när du skulle spara ordern: " + e.getMessage());
        }
    }
    public void saveIndividualPurchaseToFile() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String username = userSession.getUsername();
        String fileName = "textfile/CustomerOrder/Savedorders/" + username + ".txt";
        String newLine = "-";

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
                writer.write(newLine);
                writer.newLine();
                writer.close();
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


