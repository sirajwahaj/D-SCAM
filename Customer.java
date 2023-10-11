import java.util.List;

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
    
    
    
    public static ShoppingCart getShoppingCart() {

        return shoppingCart;
    }

    public static void showShoppingCart() {
        ShoppingCart shoppingCart = Customer.getShoppingCart();
        List<Product> products = shoppingCart.getProducts();
        if (!products.isEmpty()) {
            System.out.println("\n\nDin varukorg innehåller  följande produkter:");
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                if (product.getQty() == 0) {
                    product.setQty(1);
                }
                System.out.println((i + 1) + ". " + product.getQty() + "x " + product.getName() +" - Beskrivning: " +  product.getDescription() + " - Pris: "+  " " + product.getQtyPrice() + " kr");
            }
            double totalSum = 0.0;

            for (Product product : products) {
                totalSum += product.getQtyPrice();
            }

            System.out.println("\nTotalsumma: " + totalSum + " kr");
        } else {
            System.out.println("\n\nDin varukorg är tom.");
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

    }          

}

