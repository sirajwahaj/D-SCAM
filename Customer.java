import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class Customer {
    private String username;
    private String password;
    private String name;
    private String address;

    private static ShoppingCart shoppingCart;

    public Customer(String username, String password, String name, String address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        shoppingCart = new ShoppingCart();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return username + "," + password + "," + name + "," + address;
    }
    
    
    public void registerUser(){
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
                    if (userName.isEmpty()){                        
                        System.out.println("Du måste välja ett användarnamn");
                    } else if (password.isEmpty()) {
                        System.out.println("Du måste ange ett lösenord");
                    } else if (firstName.isEmpty()) {
                        System.out.println("Du måste ange ett lösenord");
                    } else if (adress.isEmpty()) {
                        System.out.println("Du måste skriva in adress");
                    } else {
                        Customer customer = new Customer(userName, password, firstName, adress);
                        addCustomerToFile(customer);
                        run = false;
                    } 
                    
                    break;
                    case "Q":
                    case "q":
                    run = false;
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
            System.out.println("Något gick fel." + e.getMessage());
        }
    }

    public static ShoppingCart getShoppingCart() {

        return shoppingCart;
    }
    public static void showShoppingCart() {
        ShoppingCart shoppingCart = Customer.getShoppingCart();
        List<Product> products = shoppingCart.getProducts();

        if (products.isEmpty()) {
            System.out.println("Din varukorg är tom.");
        } else {
            System.out.println("Din varukorg innehåller följande produkter:");
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                System.out.println("\nProdukt: " + product.getName() + "\nQty: " + product.getQty() + "\nPris: " + product.getPrice() + " kr" + "\nTotal: " + product.getQtyPrice());
            }
        }
    }


    

    public static void addToShoppingCart(Product product) {
        ShoppingCart shoppingCart = Customer.getShoppingCart();
        List<Product> products = shoppingCart.getProducts();

        for (Product cartProduct : products) {
            if (cartProduct.getName().equals(product.getName())) {

                cartProduct.setQty(cartProduct.getQty()+1);
                System.out.println("Kvantiteten av " + cartProduct.getName() + " har ökats.");
                return;
                }
        }
            shoppingCart.addProduct(product);
            System.out.println("Produkten har lagts till i varukorgen.");
    }          

}


