import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Order {
    private UserSession userSession = UserSession.getInstance();
    public Order(){
       
    }
    public void addProductToOrderFile(String product) {
        
        String username = userSession.getUsername();
        
        String fileName = "textfile/Order.txt";
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), "ISO-8859-1"))) {
            String addProduct = product.toString();
            writer.print(username + ",");
            writer.println(addProduct);
        } catch (IOException e) {
            System.err.println("Problem med att skriva till filen: " + e.getMessage());
        }
    }

}
