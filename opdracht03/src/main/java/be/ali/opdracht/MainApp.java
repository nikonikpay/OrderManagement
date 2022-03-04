package be.ali.opdracht;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;
public class MainApp {

    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://moktok.intecbrussel.org:33061/beers";
        String username = "niko";
        String password = "niko2021";


        try {
            Order order1 = new Order("654122", "Asdrfff", "Alter", "19800", "karimavad", true, true);
            Order order2 = new Order("912545225", "Patrick", "Brussel", "0216523", "Berlin", false, true);
            Order order3 = new Order("3265550", "Mino Kochiki", "Manchester", "32650", "Manchester", true, false);
            Order order4 = new Order("5216548", "Nadia Hamid", "Sint-Niklaas", "685471", "Sint-Niklaas", true, false);
            OrderUtil orderUtil = new OrderUtil(dbUrl, username, password);


            Product product1 = new Product(21, "Bed", 1, new BigDecimal(2500));
            Product product2 = new Product(21, "Chair", 10, new BigDecimal(650));
            Product product3 = new Product(21, "Desk", 5, new BigDecimal(980));
            Product product4 = new Product(21, "Pc", 3, new BigDecimal(7000));
            Product product5 = new Product(21, "Mirror", 1, new BigDecimal(50));


            ProductUtil productUtil = new ProductUtil(dbUrl, username, password);

//            orderUtil.addOrder(order1);
//            orderUtil.addOrder(order2);
//            orderUtil.addOrder(order3);
//            orderUtil.addOrder(order4);
//            productUtil.addProduct(product1);
//            productUtil.addProduct(product2);
//            productUtil.addProduct(product3);
//            productUtil.addProduct(product4);
//            productUtil.addProduct(product5);
            orderUtil.getOrder(21);
            orderUtil.notSentItems();
//            orderUtil.updateOrderShipping(22);
            orderUtil.notSentItems();

//            orderUtil.deleteOrder(20);

//

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
