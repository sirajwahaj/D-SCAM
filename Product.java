import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name;
    private String description;
    private double price;


    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + "," + description + "," + price;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static void AddProduct(){
        Scanner scan = new Scanner(System.in);
        String name = "";
        String description = "";
        double price = 0;
        boolean run = true;

        while (run) { 
            displayAllProducts();
            System.out.println("Hantera Produkter:");
            System.out.println("1. Lägg till ny produkt");
            System.out.println("2. Ta bort produkt");
            System.out.println("Q. Gå tillbaka");
            System.out.print("Val: ");
            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    // Lägger till nya produkter
                    System.out.println("Lägg till ny produkt:");
                    System.out.print("Namn: ");
                    name = scan.nextLine();
                    System.out.print("Beskrivning: ");
                    description = scan.nextLine();
                    System.out.print("Pris: ");
                    String priceInput = scan.nextLine();
                    try {
                        price = Double.parseDouble(priceInput);
                    } catch (NumberFormatException e) {
                        System.out.println("Ogiltigt pris format.");
                        scan.nextLine();
                        continue;
                    }
                    Product product = new Product(name, description, price);
                    addProductToFile(product);
                    System.out.println("Produkten har lagts till i listan!");
                    break;
                case "2":
                    // Tar bort nurvarande produkter
                    System.out.println("Ta bort produkt:");
                    System.out.print("Skriv in namnet på produkten du vill plocka bort: ");
                    String productToRemove = scan.nextLine();
                    if (removeProductFromFile(productToRemove)) {
                        System.out.println("Produkten har plockats bort från listan!");
                    } else {
                        System.out.println("Produkten hittades inte.");
                    }
                    break;
                case "Q":
                case "q":
                    run = false;
                    break;
                default: System.out.println("Du måste välja en av alternativen!");
            }
        }
        if (!run) {
            TerminalApp terminalApp = new TerminalApp();
            terminalApp.adminPage(terminalApp.username);

        }
    }

    public static void addProductToFile(Product product) {
        String fileName = "textfile/Product.txt";
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), "ISO-8859-1"))) {
            String addProduct = product.toString();
            writer.println(addProduct);
        } catch (IOException e) {
            System.err.println("Problem med att skriva till filen: " + e.getMessage());
        }
    }

    public static boolean removeProductFromFile(String productName) {
        String fileName = "textfile/Product.txt";
        List<String> lines = new ArrayList<>();
        boolean removed = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && !parts[0].equalsIgnoreCase(productName)) {
                    lines.add(line);
                } else {
                    removed = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Problem med att läsa filen: " + e.getMessage());
            return false;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (IOException e) {
            System.out.println("Problem att skriva till filen: " + e.getMessage());
            return false;
        }

        return removed;
    }

    public static void displayAllProducts() {
        List<Product> products = getAllProductsFromTextfile();
        System.out.println("\n\nAlla produkter:");
        for (Product product : products) {
            System.out.println("Namn: " + product.getName() + "Beskrivning: " + product.getDescription() + "Pris: " + product.getPrice() + "kr");
        }
    }

    public static List<Product> getAllProductsFromTextfile() {
        List<Product> productList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("textfile/Product.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    String description = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    Product product = new Product(name, description, price);
                    productList.add(product);
                }
            }
        } catch (IOException e) {
            System.err.println("Problem med att läsa filen: " + e.getMessage());
        }

        return productList;
    }
}