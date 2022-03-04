package be.ali.opdracht;

import java.math.BigDecimal;
public class Product {

    private int id;
    private int orderID;
    private String productName;
    private int amount;
    private BigDecimal pricePerUnit;

    public Product(int orderID, String productName, int amount, BigDecimal pricePerUnit) {
        this.orderID = orderID;
        this.productName = productName;
        this.amount = amount;
        this.pricePerUnit = pricePerUnit;
    }

    public Product(int id, int orderID, String productName, int amount, BigDecimal pricePerUnit) {
        this.id = id;
        this.orderID = orderID;
        this.productName = productName;
        this.amount = amount;
        this.pricePerUnit = pricePerUnit;
    }

    public int getId() {
        return id;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getProductName() {
        return productName;
    }

    public int getAmount() {
        return amount;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", orderID=" + orderID + ", productName='" + productName + '\'' + ", amount=" + amount + ", pricePerUnit=" + pricePerUnit + '}';
    }
}
