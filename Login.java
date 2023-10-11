import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Login {
    private UserSession userSession = UserSession.getInstance();
    TerminalApp TerminalApp = new TerminalApp();
    String username;
    Scanner scan = new Scanner(System.in);

        public void verifyUser(String inputUsername, String inputPassword){
            boolean userFound = false;
            boolean isAdmin = false;
            

            try(Scanner scan = new Scanner(new File("textfile/account.txt"))){
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split(",");
                    if(parts.length >=2){
                        username = parts[0];
                        String password = parts[1];

                        if (inputUsername.equals(username) && inputPassword.equals(password)) {
                            userFound = true;
                            if (line.contains("admin=true")) {
                            isAdmin = true;
                            }
                            break;
                        }
                    }
                } 
                if (userFound) { // Har lagt denna if stats utanför loopen då den annars bara loopar första linjen i accounts och inte söker alla rader i accounts.
                    if (isAdmin) {
                        userSession.login(username);
                        TerminalApp.adminPage();
                    } else {
                        userSession.login(username);
                        TerminalApp.customerPage();
                    }
                } else {
                    System.out.println("\nAnvändaren är inte registrerad.\n");
                    TerminalApp.run();
                }
            }
            catch (FileNotFoundException e) {
                System.out.println("Något gick fel vid inloggning." + e.getMessage());
                System.exit(1);
            }
        }

    public void loginUser() {
        System.out.println("Skriv in username");
        String user = scan.nextLine();
        System.out.println("Skriv in lösenord");
        String pass = scan.nextLine();
        verifyUser(user, pass);
    }

   
    
}






