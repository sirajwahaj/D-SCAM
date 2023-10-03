import java.util.List;
import java.util.Scanner;

    public class TerminalApp {
        public String username;
        private String loggedInUser;
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

        public void adminPage(String username){
            loggedInUser = username;
            while(run){
                System.out.print("\n\nVälkommen - " + loggedInUser + " (Admin)" + 
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
                        //Visa alla custumers med möjlighet att redigera uppgifter
                        break;
                    case "3":
                        //Visa alla beställningar och transaktioner
                        break;
                    case "Q":
                    case "q":
                        logOut();
                        break;
                    default:
                        System.out.println("Ogiltigt val");
                }
            }
        }

        public void customerPage(String username){
            loggedInUser = username;
            Scanner scan = new Scanner(System.in);
            while(run){
                System.out.print("\n\nVälkommen - " + loggedInUser + "\n" +
                        "1. Shoppa \n" +
                        "2. Se varukorg \n" +
                        "3. Se kvitton och orderhistorik \n " +
                        "\n\nQ. Logga ut" + 
                        "\nVal - ");
                String choice = scan.next();
                switch(choice){
                    case "1":
                        System.out.println("Välj produkter att lägga till i varukorgen: ");
                        //Gå till "product"
                        break;
                    case "2":
                        Customer.showShoppingCart();
                        break;
                    case "3":
                        //Öppna aktuell kunds textfil med information om köp och kvitton
                    case "Q":
                    case "q":
                        logOut();
                        //run = false;
                        break;
                    default:
                        System.out.println("Ogiltigt val");
                }
            }
            scan.close();
        }


        public void logOut() {
            System.out.println(loggedInUser + " har loggats ut.");
            loggedInUser = null; // Nollställ inloggad användare.
            run();
        }
}
