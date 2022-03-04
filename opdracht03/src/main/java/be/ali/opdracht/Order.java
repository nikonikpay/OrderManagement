package be.ali.opdracht;

import java.sql.Date;
import java.text.SimpleDateFormat;
public class Order {

    private int id;
    private String orderNumber;
    private String orderClient;
    private String orderDeliveryAddress;
    private String orderDeliveryPostalCode;
    private String orderDeliveryCity;
    private boolean isVatFree;
    private boolean isSend;
    private Date orderDate;

    public Order(String orderNumber, String orderClient, String orderDeliveryAddress, String orderDeliveryPostalCode, String orderDeliveryCity, boolean isVatFree, boolean isSend) {
        this.orderNumber = orderNumber;
        this.orderClient = orderClient;
        this.orderDeliveryAddress = orderDeliveryAddress;
        this.orderDeliveryPostalCode = orderDeliveryPostalCode;
        this.orderDeliveryCity = orderDeliveryCity;
        this.isVatFree = isVatFree;
        this.isSend = isSend;
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        this.orderDate = sqlDate;
    }

    public Order(int id, String orderNumber, String orderClient, String orderDeliveryAddress, String orderDeliveryPostalCode, String orderDeliveryCity, boolean isVatFree, boolean isSend, Date orderDate) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderClient = orderClient;
        this.orderDeliveryAddress = orderDeliveryAddress;
        this.orderDeliveryPostalCode = orderDeliveryPostalCode;
        this.orderDeliveryCity = orderDeliveryCity;
        this.isVatFree = isVatFree;
        this.isSend = isSend;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getOrderClient() {
        return orderClient;
    }

    public String getOrderDeliveryAddress() {
        return orderDeliveryAddress;
    }

    public String getOrderDeliveryPostalCode() {
        return orderDeliveryPostalCode;
    }

    public String getOrderDeliveryCity() {
        return orderDeliveryCity;
    }

    public boolean isVatFree() {
        return isVatFree;
    }

    public boolean isSend() {
        return isSend;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setOrderClient(String orderClient) {
        this.orderClient = orderClient;
    }

    public void setOrderDeliveryAddress(String orderDeliveryAddress) {
        this.orderDeliveryAddress = orderDeliveryAddress;
    }

    public void setOrderDeliveryPostalCode(String orderDeliveryPostalCode) {
        this.orderDeliveryPostalCode = orderDeliveryPostalCode;
    }

    public void setOrderDeliveryCity(String orderDeliveryCity) {
        this.orderDeliveryCity = orderDeliveryCity;
    }

    public void setVatFree(boolean vatFree) {
        isVatFree = vatFree;
    }

    public void setSend(boolean send) {
        isSend = send;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", orderNumber='" + orderNumber + '\'' + ", orderClient='" + orderClient + '\'' + ", orderDeliveryAddress='" + orderDeliveryAddress + '\'' + ", orderDeliveryPostalCode='" + orderDeliveryPostalCode + '\'' + ", orderDeliveryCity='" + orderDeliveryCity + '\'' + ", isVatFree=" + isVatFree + ", isSend=" + isSend + ", orderDate='" + orderDate + '\'' + '}';
    }






}
