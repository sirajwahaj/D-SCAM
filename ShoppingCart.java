import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ShoppingCart {
    private UserSession userSession = UserSession.getInstance();
    private List<Product> products;
    private String username = userSession.getUsername();
    private int latestOrderNumber = 1;

    
    public ShoppingCart() {

        products = new ArrayList<>();
    }
    
    private int getNextOrderNumber() {
        return latestOrderNumber++;
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

    public void clearShoppingCart(){
        products.clear();
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
                System.out.println("Din beställning är bekräftad!");
            } else {
                System.out.println("\n\nLägg till vara för kunna beställa");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Något gick fel när du skulle spara ordern: " + e.getMessage());
        }
        clearShoppingCart();
    }
}