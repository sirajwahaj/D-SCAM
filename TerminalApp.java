import java.util.Scanner;

    public class TerminalApp {
        Scanner scan = new Scanner(System.in);

        public void run(){
            Customer customer = new Customer(null, null, null, null);
            Login authen = new Login();
           // while(run){
            System.out.print("Välkommen till D-SCAM \n " +
                    "1. Logga in \n " +
                    "2. Registrera ny kund \n " +
                    "3. Admin \n " +
                    "\n\nVal - ");
            String choice = scan.nextLine();

            switch(choice){
                case "1":
                    System.out.println("Skriv in username");
                    String user = scan.nextLine();
                    System.out.println("Skriv in lösenord");
                    String pass = scan.nextLine();
                    authen.verifyUser(user, pass);
                    //Gå till"Login"
                    break;
                case "2":
                    customer.registerUser();
                    //Gå till createCustomer
                    break;
                case "3":
                    //Gå till adminPage
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Ogiltigt val");
            }
       // }
    }

    public void adminPage(){
        System.out.print("Välkommen Admin - " +  
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
                System.out.println("Du är utloggad");
                TerminalApp TerminalApp = new TerminalApp();
                TerminalApp.run();
                break;
            default:
                System.out.println("Ogiltigt val");
        }
    }

    public void customerPage(){
        Scanner scan = new Scanner(System.in);
        System.out.print("\n\nVälommen" + "\n" +
                "1. Shoppa \n" +
                "2. Se varukorg \n" +
                "3. Se kvitton och orderhistorik \n " +
                "Val - ");
        String choice = scan.nextLine();

        switch(choice){
            case "1":
                //Gå till "product"
                break;
            case "2":
                //Gå till "varukorg"
                break;
            case "3":
                //Öppna aktuell kunds textfil med information om köp och kvitton
            case "4":
            default:
                System.out.println("Ogiltigt val");
        }
        scan.close();
    }
}
