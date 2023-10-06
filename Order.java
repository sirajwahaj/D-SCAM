import java.io.*;
import java.util.*;

public class Order {
    private UserSession userSession = UserSession.getInstance();
    String username = userSession.getUsername();

    public void saveCustomersToFile(String username,List<String> orderProductStrings) {
        String fileName = "textfile/Order.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String orderProductString : orderProductStrings) {
                writer.write(orderProductString);
                writer.newLine();
            }
            System.out.println("Kundens information uppdaterad");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Något gick fel när kundens uppgifter skulle sparas i textfilen " + e.getMessage());
        }
    }
}
