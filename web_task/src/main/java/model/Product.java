package model;

import lombok.AllArgsConstructor;
import lombok.Data;

//@Data
//@AllArgsConstructor
public class Product implements Comparable<Product> {

    private String website;
    private String link;
    private String name;
    private double price;

    public Product(String website, String link, String name, double price) {
        this.website = website;
        this.link = link;
        this.name = name;
        this.price = price;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

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
