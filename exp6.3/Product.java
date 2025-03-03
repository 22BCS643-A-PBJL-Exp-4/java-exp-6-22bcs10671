import java.util.*;
import java.util.stream.*;

class Product {
    String name;
    String category;
    double price;

   
    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

   
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', category='" + category + "', price=" + price + "}";
    }
}

public class ProductProcessor {

    public static void main(String[] args) {
      
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 1200),
            new Product("Smartphone", "Electronics", 800),
            new Product("Tablet", "Electronics", 500),
            new Product("T-shirt", "Clothing", 30),
            new Product("Jeans", "Clothing", 40),
            new Product("Jacket", "Clothing", 100),
            new Product("Blender", "Home Appliances", 150),
            new Product("Microwave", "Home Appliances", 200),
            new Product("Washing Machine", "Home Appliances", 500)
        );

        
        Map<String, List<Product>> productsByCategory = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory));

        
        System.out.println("Products grouped by category:");
        productsByCategory.forEach((category, productList) -> {
            System.out.println(category + ": " + productList);
        });

       
        System.out.println("\nMost expensive product in each category:");
        productsByCategory.forEach((category, productList) -> {
            Product mostExpensiveProduct = productList.stream()
                .max(Comparator.comparingDouble(Product::getPrice))
                .orElseThrow(NoSuchElementException::new);  // In case the list is empty
            System.out.println(category + ": " + mostExpensiveProduct);
        });

       
        double averagePrice = products.stream()
            .collect(Collectors.averagingDouble(Product::getPrice));

        System.out.println("\nAverage price of all products: $" + averagePrice);
    }
}
