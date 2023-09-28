import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Customer {
    private String username;
    private String password;
    private String name;
    private String address;

    public Customer(String username, String password, String name, String address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return username + "," + password + "," + name + "," + address;
    }
    
    public void registerUser(){
        TerminalApp TerminalApp = new TerminalApp();
        Scanner scan = new Scanner(System.in);
        String userName = "";
        String password = "";
        String firstName = "";
        String adress = "";
        boolean run = true;
        while(run){
                System.out.print("\n\nRegistrera ett konto" + 
                                    "\n1. Användarnamn - " + userName +
                                    "\n2. Password - " + password +
                                    "\n3. Förnamn - " + firstName + 
                                    "\n4. Adress - " + adress +
                                    "\n5. Spara" + 
                                    "\n\nQ. Gå tillbaka" +
                                    "\nVal: ");
                                    String choice = scan.nextLine();

                    switch (choice){
                    case "1":
                    System.out.print("Användarnamn: ");
                    userName = scan.nextLine(); 
                    break;
                    case "2":
                    System.out.print("Lösenord: ");
                    password = scan.nextLine();
                    break;
                    case "3":
                    System.out.print("Förnamn: ");
                    firstName = scan.nextLine();
                    break;
                    case "4":
                    System.out.print("Adress: ");
                    adress = scan.nextLine();
                    break;
                    case "5":
                    Customer customer = new Customer(userName, password, firstName, adress);
                    addCustomerToFile(customer);
                    run = false;
                    break;
                    case "Q":
                    case "q":
                    TerminalApp.run();
                    break;
                    
                    default: System.out.println("du måste välja mellan 1-4");
            }
        }
    }
    
    public void addCustomerToFile(Customer customer){
        String fileName = "textfile/account.txt";
        try(PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), "ISO-8859-1"))){
            String addUser = customer.toString();
            writer.println(addUser);
            System.out.println("Registreringen lyckades!");

        }catch (IOException e) { 
            e.printStackTrace();
            System.out.println("Något gick fel.");
        }
    }
    
}

// public void addToCart(Product product) {
//     shoppingCart.add(product);
// }

// public void removeFromCart(Product product) {
//     shoppingCart.remove(product);
// }

// public void checkout() {
//     // Skapa en ny order baserad på innehållet i varukorgen
//     // Order order = new Order(this, shoppingCart);
//     // purchaseHistory.add(order);
//     // shoppingCart.clear();
// }

// public void viewPurchaseHistory() {
//     System.out.println(order);
//     for (Order order : purchaseHistory) {
//     }
// }
//}