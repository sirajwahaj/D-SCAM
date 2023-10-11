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
        if (products == null){
            loadProducts();
        }
        return products;
    }

    public void loadProducts(){

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