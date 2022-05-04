package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product implements Comparable<Product> {

    private String website;
    private String link;
    private String name;
    private double price;

    @Override
    public String toString() {
        return "Product{" +
                "Website='" + website + '\'' +
                ", Price='" + price + "vnđ" + '\'' +
                ", Name='" + name + '\'' +
                ", Link=" + link +
                '}';
    }

    public int compareTo(Product o) {
        return Double.compare(this.getPrice(), o.getPrice());
    }

}
