import java.util.Scanner;

public class UserSession {
    private String username;
    private boolean isLoggedIn = false;
    Scanner scan = new Scanner(System.in);
    private static UserSession instance;

    private UserSession(){
        

    }

    public static UserSession getInstance() {
        if (instance == null){
            instance = new UserSession();
        }
        return instance;
    }

    public void login(String username){
        this.username = username;
        this.isLoggedIn = true;
    }

    public void logout(){
        System.out.println("vill du logga ut? Yes/no");
        String answer = scan.nextLine();
        if(answer.equalsIgnoreCase("Yes")){
        this.username = null;
        this.isLoggedIn = false;
        System.out.println("Du har blivit utloggad.");
        }

        
    }

    public String getUsername(){
        return username;
    }

    public boolean isLoggedIn(){
        return isLoggedIn;
    }
}
