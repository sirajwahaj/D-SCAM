import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> products;


    public ShoppingCart() {

        products = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "" + products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    

}


