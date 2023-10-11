import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerManager {

    
    public static void editCustomer(String username){
        List<Customer> customers = loadCustomersFromFile();
        int indexToUpdate = -1;


        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            if (customer.getName().equals(username)) {
                indexToUpdate = i;
                break;
            }
        }

        if (indexToUpdate != -1) {
            Scanner scan = new Scanner(System.in);
            boolean run = true;

            while(run){
                System.out.println("\n\nUppdatera kund: " + customers.get(indexToUpdate).getName() + "s" + " information" +
                                "\n1. Namn - " + customers.get(indexToUpdate).getName() +
                                "\n2. Användarnamn - " + customers.get(indexToUpdate).getUsername() +
                                "\n3. Lösenord - " + customers.get(indexToUpdate).getPassword() +
                                "\n4. Adress - " + customers.get(indexToUpdate).getAddress() +
                                "\n5. Spara" +
                                "\n\nQ. Gå tillbaka" +
                                "\nVal: ");
                    
                    String choice = scan.nextLine();

                    switch (choice){
                    case "1":
                    System.out.print("Förnamn: ");
                    String newName = scan.nextLine();
                    customers.get(indexToUpdate).setName(newName);
                    break;
                    case "2":
                    System.out.print("Användarnamn: ");
                    String newUsername = scan.nextLine();
                    customers.get(indexToUpdate).setUsername(newUsername);
                    break;
                    case "3":
                    System.out.print("Lösenord: ");
                    String newPassword = scan.nextLine();
                    customers.get(indexToUpdate).setPassword(newPassword);
                    break;
                    case "4":
                    System.out.print("Adress: ");
                    String newAddress = scan.nextLine();
                    customers.get(indexToUpdate).setAddress(newAddress);
                    break;
                    case "5":
                    saveCustomersToFile(customers);
                    break;
                    case "Q":
                    case "q":
                    run = false;
                    break;
                    default: System.out.println("du måste välja alternativ 1-5");
                }
            
            }
            


           
        } else {
            System.out.println("Kunde inte hitta kund.");
        }
    
    }

    public static List<Customer> loadCustomersFromFile() {
        List<Customer> customers = new ArrayList<>();
        String fileName = "textfile/account.txt";

        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String username = parts[0];
                    String password = parts[1];
                    String name = parts[2];
                    String address = parts[3];
                    Customer customer = new Customer(username, password, name, address);
                    customers.add(customer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Något gick fel när kund uppgifterna skulle laddas från textfilen: " + e.getMessage());
        }

        return customers;
    }

    public static void saveCustomersToFile(List<Customer> customers) {
        String fileName = "textfile/account.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Customer customer : customers) {
                writer.write(customer.toString());
                writer.newLine();
            }
            System.out.println("Kundens information uppdaterad");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Något gick fel när kundens uppgifter skulle sparas i textfilen " + e.getMessage());
        }
    }
    public static void listAllCustomers() {
        List<Customer> customers = CustomerManager.loadCustomersFromFile();
        System.out.println("\n\nLista på alla kunder:");
        int rowcount = 1;
        for (Customer customer : customers) {
            System.out.println(rowcount + ". " + " Förnamn: " + customer.getName());
            rowcount++;
        }
    }
}
