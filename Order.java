import java.io.*;
import java.util.*;

public class Order {
    private UserSession userSession = UserSession.getInstance();
    String username = userSession.getUsername();
    
    public Order(){
        
    }
    


    
    public void saveCustomersOrderToFile(String username,List<String> orderProductStrings) {
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

            }
            writer.close();
            System.out.println("Din order är sparad");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Något gick fel när du skulle spara ordern" + e.getMessage());
        }
    }
}
