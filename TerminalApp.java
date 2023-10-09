import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

    public class TerminalApp {
        private UserSession userSession = UserSession.getInstance();
        Order order = new Order();
        Scanner scan = new Scanner(System.in);
        boolean run = true; 
        

        public void run(){
            Customer customer = new Customer(null, null, null, null);
            Login Login = new Login();
           
            while(run){
                System.out.print("\n\nVälkommen till D-SCAM \n " +
                    "1. Logga in \n " +
                    "2. Registrera ny kund \n " +
                    "3. Avsluta programmet" +
                    "\n\nVal - ");
                String choice = scan.nextLine();

                try {
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
                        case "3":
                            run = false;
                            break;
                        default:
                            throw new IllegalArgumentException("Ogiltigt val");
                    }
                }catch (InputMismatchException e){
                    System.out.println("Felaktig inmatning. Ange 1 eller 2.");
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
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
                try {
                    switch(choice){
                        case "1":
                            System.out.println("Välj produkter att lägga till i varukorgen: ");
                            pickAProductToAddToCart();
                            break;
                        case "2":
                            shoppingCartPage();
                            break;
                        case "3":
                           
                            break;
                        case "Q":
                        case "q":
                            userSession.logout();
                            break;
                        default:
                            throw new IllegalArgumentException("Ogiltigt val");
                    }
                }catch (InputMismatchException e){
                    System.out.println("Felaktig inmatning. Ange 1, 2 eller Q");
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
            
        }

        public void shoppingCartPage() {
            Scanner scan = new Scanner(System.in);
            boolean run = true;
            ShoppingCart shoppingCart = Customer.getShoppingCart();
            ShoppingCart shoppingCartOrder = new ShoppingCart();
            String username = userSession.getUsername();

            while (run) {
                
                Customer.showShoppingCart();
                System.out.print("\n\n1. Ta bort varor \n" +
                        "2. Spara order\n" +
                        "3. Beställ order \n" +
                        "\n\nQ. Gå tillbaka" +
                        "\nVal - ");
                String choice = scan.next();
            try{
                switch (choice) {
                    case "1":
                        removeProductFromCart();
                        break;
                    case "2":
                        List<Product> productsInCart = shoppingCart.getProducts();

                        if(!productsInCart.isEmpty()){
                            List<String> shoppingCartStrings = new ArrayList<>();
                        
                            for (Product product : shoppingCart.getProducts()) {
                                shoppingCartStrings.add(product.getName()+ 
                                "," + product.getDescription()+
                                "," + product.getQty()+
                                "," + product.getQtyPrice());
                            }
                            shoppingCartOrder.saveCustomersOrderToFile(shoppingCartStrings);
                        } else {
                            System.out.println("Det finns inget att spara");
                        }
                        break;
                    case "3":
                          Order.AvslutaOrder();
                        // <----------  LÄGG DIN METODER HÄR! 
                            //Öppna aktuell kunds textfil med information om köp och kvitton
                        break;
                    case "Q":
                    case "q":
                        run = false;
                        break;
                    default:
                            throw new IllegalArgumentException("Ogiltigt val");
                    }
                }catch (InputMismatchException e){
                    System.out.println("Felaktig inmatning. Ange 1, 2 eller Q");
                    scan.next();
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
        }


        public void pickAProductToAddToCart() {
            Scanner scan = new Scanner(System.in);
            boolean run = true;
            List<Product> products = Product.loadProductsFromFile();

            while (run) {
                System.out.println("Tillgängliga produkter att lägga till i varukorgen:");
                for (int i = 0; i < products.size(); i++) {
                    Product product = products.get(i);
                    System.out.println((i + 1) + ". " + product.getName() + " - Pris: " + product.getPrice() + " kr");
                }

                System.out.println("\nQ. Gå tillbaka" +
                        "\n\nVälj en siffra för att lägga till en produkt i varukorgen."
                        + "\nVal -");
                String choice = scan.nextLine();

                try {
                    if (choice.equalsIgnoreCase("q")) {
                        run = false;
                    } else if (!Product.onlyDigitInString(choice)) {
                        System.out.println("Du måste välja 1 - " + products.size() + " eller Q!");
                    } else if(!choice.isEmpty()){
                        int productIndex = Integer.parseInt(choice) - 1;
                        if (productIndex >= 0 && productIndex < products.size()) {
                            Product selectedProduct = products.get(productIndex);
                            Customer.addToShoppingCart(selectedProduct);
                            System.out.println(selectedProduct.getName() + " har lagts till i din varukorg.");
                            //order.addProductToOrderFile(selectedProduct);
                        } else {
                            throw new IllegalArgumentException("Du måste välja 1 - " + products.size() + " eller Q!");
                        }
                    }
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        public void removeProductFromCart() {
            Scanner scan = new Scanner(System.in);
            boolean run = true;
            ShoppingCart cart = Customer.getShoppingCart();

            while (run) {
                List<Product> cartProducts = cart.getProducts();

                if (cartProducts.isEmpty()) {
                    System.out.println("Din varukorg är tom.");
                    break;
                }

                System.out.println("Din varukorg innehåller följande produkter:");
                for (int i = 0; i < cartProducts.size(); i++) {
                    Product product = cartProducts.get(i);
                    System.out.println((i + 1) + ". - " + product.getQty() + "x " + product.getName() + " - Pris: " + product.getQtyPrice() + " kr");
                }

                System.out.println("\nQ. Gå tillbaka" +
                        "\n\nVälj en siffra för att ta bort en produkt från varukorgen."
                        + "\nVal -");

                try {
                    String choice = scan.nextLine();
                    if (choice.equalsIgnoreCase("q")) {
                        run = false;
                    } else if (!Product.onlyDigitInString(choice)) {
                        System.out.println("Du måste välja 1 - " + cartProducts.size() + " eller Q!");
                    } else if (!choice.isEmpty()) {
                        int productIndex = Integer.parseInt(choice) - 1;
                        if (productIndex >= 0 && productIndex < cartProducts.size()) {
                            Product selectedProduct = cartProducts.get(productIndex);

                            if (selectedProduct.getQty() > 1) {
                                selectedProduct.setQty(selectedProduct.getQty() - 1);
                                selectedProduct.setQtyPrice(selectedProduct.getQtyPrice() - 1);
                            } else {
                                // Ta bort produkten om det finns bara en kvar
                                cart.removeProduct(selectedProduct);
                            }

                            System.out.println(selectedProduct.getName() + " har tagits bort från din varukorg.");
                        } else {
                            throw new IllegalArgumentException("Du måste välja 1 - " + cartProducts.size() + " eller Q!");
                        }
                    }
                }catch (NumberFormatException e){
                    System.out.println("Felaktig input. Var vänlig skriv in ett giltigt nummer.");
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
        }





    }
