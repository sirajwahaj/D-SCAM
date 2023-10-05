import java.util.List;
import java.util.Scanner;

    public class TerminalApp {
        private UserSession userSession = UserSession.getInstance();
        Scanner scan = new Scanner(System.in);
        boolean run = true; 
        

        public void run(){
            Customer customer = new Customer(null, null, null, null);
            Login Login = new Login();
           
            while(run){
                System.out.print("\n\nVälkommen till D-SCAM \n " +
                    "1. Logga in \n " +
                    "2. Registrera ny kund \n " +
                    "\n\nVal - ");
                String choice = scan.nextLine();

                switch(choice){
                    case "1":
                        System.out.println("Skriv in username");
                        String user = scan.nextLine();
                        System.out.println("Skriv in lösenord");
                        String pass = scan.nextLine();
                        Login.loginUser(user, pass);
                        break;
                    case "2":
                        customer.registerUser();
                        break;
                    default:
                        System.out.println("Ogiltigt val");
                }
            }
        }

        public void adminPage(){
            
            while(userSession.isLoggedIn()){
                String username = userSession.getUsername();
                System.out.print("\n\nVälkommen - " + username + " (Admin)" + 
                        "\n1. Lägg till/Ta bort produkter\n" +
                        "2. Visa och redigera kunduppgifter\n" +
                        "3. Översikt över alla beställningar och transaktioner\n" +
                        "\n\nQ. Logga ut" +
                        "\nVal -");

                String choice = scan.nextLine();

                switch(choice){
                    case "1":
                        Product.AddProduct();
                        //Visa alla produkter med möjlighet att ta bort/skapa ny
                        break;
                    case "2":
                        CustomerManager.listAllCustomers();
                        System.out.println("\nSkriv in namnet på Kunden du vill redigera");
                        String customer = scan.nextLine();
                        CustomerManager.editCustomer(customer);
                        //Visa alla customers med möjlighet att redigera uppgifter
                        break;
                    case "3":
                        //Visa alla beställningar och transaktioner
                        break;
                    case "Q":
                    case "q":
                        userSession.logout();                        
                        break;
                    default:
                        System.out.println("Ogiltigt val");
                }
            }
        }

        public void customerPage(){
            Scanner scan = new Scanner(System.in);
            while(userSession.isLoggedIn()){
                String username = userSession.getUsername();
                System.out.print("\n\nVälkommen - " + username + "\n" +
                        "1. Shoppa \n" +
                        "2. Varukorg \n" +
                        "3. Se kvitton och orderhistorik \n " +
                        "\n\nQ. Logga ut" + 
                        "\nVal - ");
                String choice = scan.next();
                switch(choice){
                    case "1":
                        System.out.println("Välj produkter att lägga till i varukorgen: ");
                        pickAProductToAddToCart();
                        //
                        break;
                    case "2":
                        Customer.showShoppingCart();
                        break;
                    case "3":
                        //Öppna aktuell kunds textfil med information om köp och kvitton
                        break;
                    case "Q":
                    case "q":
                        userSession.logout();
                        break;
                    default:
                        System.out.println("Ogiltigt val");
                }
            }
            
        }

        public void pickAProductToAddToCart() {
            Scanner scan = new Scanner(System.in);
            boolean run = true;
            List<Product> products = Product.loadProductsFromFile();

            while (run) {
                for (int i = 0; i < products.size(); i++) {
                    Product product = products.get(i);
                    System.out.println((i + 1) + ". " + product.getName() + " - Pris: " + product.getDescription() + " " + product.getPrice() + " kr");
                }

                System.out.println("\nQ. Gå tillbaka"
                        + "\nVal -");
                String choice = scan.nextLine();

                if (choice.equalsIgnoreCase("q")) {
                    run = false;
                } else if (!Product.onlyDigitInString(choice)) {
                    System.out.println("Du måste välja 1 - " + products.size() + " eller Q!");
                } else {
                    int productIndex = Integer.parseInt(choice) - 1;
                    if (productIndex >= 0 && productIndex < products.size()) {
                        Product selectedProduct = products.get(productIndex);
                        Customer.addToShoppingCart(selectedProduct);
                        System.out.println(selectedProduct.getName() + " har lagts till i din varukorg.");
                    } else {
                        System.out.println("Du måste välja 1 - " + products.size() + " eller Q!");
                    }
                }
            }
        }



    }
