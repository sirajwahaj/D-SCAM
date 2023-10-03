public class UserSession {
    private String username;
    private boolean isLoggedIn = false;

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
        this.username = null;
        this.isLoggedIn = false;
    }

    public String getUsername(){
        return username;
    }

    public boolean isLoggedIn(){
        return isLoggedIn;
    }
}
