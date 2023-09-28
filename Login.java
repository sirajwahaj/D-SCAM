import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Login {
    TerminalApp TerminalApp = new TerminalApp();
    String username;

    public void verifyUser(String inputUsername, String inputPassword){
        boolean userFound = false;
        boolean isAdmin = false;
        

        try(Scanner scan = new Scanner(new File("textfile/account.txt"))){
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] parts = line.split(",");
                username = parts[0];
                String password = parts[1];
                if (inputUsername.equals(username) && inputPassword.equals(password)) {
                    userFound = true;
                    if (line.contains("admin=true")) {
                    isAdmin = true;
                    }
                }
                if (userFound) {
                if (isAdmin) {
                    TerminalApp.adminPage();
                } else {
                    TerminalApp.customerPage();
                }
                } else {
                    System.out.println("\nAnvändaren är inte registrerad.\n");
                    TerminalApp.run();
                    break;
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Något gick fel vid inloggning.");
        }
    }
    public void userMenu(){
        System.out.println(username);
    }
}

// Kontrollera resultatet




