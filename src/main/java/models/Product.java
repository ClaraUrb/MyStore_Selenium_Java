package models;

import lombok.Data;

@Data
public class Product {

    public String name;
    public double price;
    public double totalPrice;
    public String size;
    public int orderedQuantity;
    public String color;
    public String paperType;
    public String dimension;

}
