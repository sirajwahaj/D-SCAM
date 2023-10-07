import java.io.*;
import java.util.*;

public class Order {
    private UserSession userSession = UserSession.getInstance();
    String username = userSession.getUsername();
    Date todayDate = new Date();
    
    
    public Order(){
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

    
    
    // KODEN NEDANFÖR ÄR TEST

    // public void saveCustomersOrderToFile2() {
    //     ShoppingCart shoppingCart = Customer.getShoppingCart();
    //     String fileName = "textfile/CustomerOrder/Savedorders/" + username + ".txt";
    //     try {
    //         File file = new File(fileName);

    //         if(!file.exists()){
    //             file.createNewFile();
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         System.out.println("Filen kunde inte skapas");
    //     }

    //     try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
    //             writer.write(shoppingCart.toString().replace("[", "").replace("]", "").replace(", ", "\n"));
    //             writer.newLine();
    //             writer.flush();
            
            
    //         System.out.println("Din order är sparad");
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //         System.out.println("Något gick fel när du skulle spara ordern" + e.getMessage());
    //     }
    // }

}
