package be.ali.opdracht;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
public class OrderUtil {


    private String dbUrl;
    private String username;
    private String password;

    public OrderUtil(String dbUrl, String username, String password) {
        this.dbUrl = dbUrl;
        this.username = username;
        this.password = password;
    }


    public void addOrder(Order theOrder) throws Exception {

        Connection myConnection = null;
        PreparedStatement myStatement = null;

        try {
            myConnection = DriverManager.getConnection(dbUrl, username, password);
            String sql = "insert into order_table " + "(order_number, order_client, order_delivery_address, order_delivery_postalcode, order_delivery_city, is_vat_free,is_send,order_date) " + "values (?, ?, ?,?,?,?,?,?)";
            myStatement = myConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            myStatement.setString(1, theOrder.getOrderNumber());
            myStatement.setString(2, theOrder.getOrderClient());
            myStatement.setString(3, theOrder.getOrderDeliveryAddress());
            myStatement.setString(4, theOrder.getOrderDeliveryPostalCode());
            myStatement.setString(5, theOrder.getOrderDeliveryCity());
            myStatement.setBoolean(6, theOrder.isVatFree());
            myStatement.setBoolean(7, theOrder.isSend());
            myStatement.setDate(8, theOrder.getOrderDate());

            myStatement.execute();
            int myId = 0;
            ResultSet newRs = myStatement.getGeneratedKeys();
            while(newRs.next()) {
                System.out.println("The id is" + newRs.getInt(1));
                myId = newRs.getInt(1);
            }
            String sql2 = "update order_table set order_number=? where id=?";
            myStatement = myConnection.prepareStatement(sql2);
            myStatement.setString(1, orderNumberGenerator(theOrder, myId));
            myStatement.setInt(2, myId);
            myStatement.executeUpdate();

        } finally {
            close(myConnection, myStatement, null);

        }

    }

    public void getOrder(int theOrderId) throws Exception {
        Connection myConnection = null;
        PreparedStatement myPreparedStatement = null;
        ResultSet myResultSet = null;

        try {
            myConnection = DriverManager.getConnection(dbUrl, username, password);
            String sql = "select order_number, order_client,order_delivery_address,op.product_name,op.amount,op.price_per_unit from order_table INNER join order_products op on order_table.id = op.order_id where order_table.id= ?";
            myPreparedStatement = myConnection.prepareStatement(sql);
            myPreparedStatement.setInt(1, theOrderId);
            System.out.println(myPreparedStatement.getResultSet());
            myResultSet = myPreparedStatement.executeQuery();
            System.out.println(myPreparedStatement.getResultSet());
            int sum = 0;
            while(myResultSet.next()) {
                System.out.println("Order number is " + myResultSet.getString("order_number"));
                System.out.println("Order Client is " + myResultSet.getString("order_client"));
                System.out.println("Order address is " + myResultSet.getString("order_delivery_address"));
                System.out.println("Product name is " + myResultSet.getString("product_name"));
                System.out.println("The amount is " + myResultSet.getString("amount"));
                System.out.println("The price per unit is " + myResultSet.getString("price_per_unit"));
                System.out.println("The Total Price for that item is :");
                System.out.println(myResultSet.getInt("price_per_unit") * myResultSet.getInt("amount"));
                sum += myResultSet.getInt("price_per_unit") * myResultSet.getInt("amount");
                System.out.println("===============");
            }
            System.out.println("The price of whole order is:");
            System.out.println(sum + " Euro");
        } finally {
            close(myConnection, myPreparedStatement, myResultSet);
        }

    }


    public void notSentItems() throws Exception {
        Connection myConnection = null;
        PreparedStatement myPreparedStatement = null;
        ResultSet myResultSet = null;

        try {
            myConnection = DriverManager.getConnection(dbUrl, username, password);
            String sql = "select * from order_table where is_send=0;";
            myPreparedStatement = myConnection.prepareStatement(sql);
            myResultSet = myPreparedStatement.executeQuery();

            while(myResultSet.next()) {
                System.out.println(">>><<<<<");
                System.out.println("Order number is " + myResultSet.getString("order_number"));
                System.out.println("Order Client is " + myResultSet.getString("order_client"));
                System.out.println("Order address is " + myResultSet.getString("order_delivery_address"));
                System.out.println("===============");
            }


        } finally {
            close(myConnection, myPreparedStatement, myResultSet);
        }
    }


    public void updateOrderShipping(int orderId) throws Exception {

        Connection myConnection = null;
        PreparedStatement myPreparedStatement = null;

        try {
            myConnection = DriverManager.getConnection(dbUrl, username, password);
            String sql = "update order_table set is_send =1 where id=?;";
            myPreparedStatement = myConnection.prepareStatement(sql);
            myPreparedStatement.setInt(1, orderId);
            myPreparedStatement.execute();
        } finally {
            close(myConnection, myPreparedStatement, null);
        }

    }

    public void deleteLastOrder() throws Exception {
        Connection myConnection = null;
        PreparedStatement myPreparedStatement = null;
        ResultSet myResultSet = null;


        try {


            //2-
            myConnection = DriverManager.getConnection(dbUrl, username, password);


            String sql3 = "select *from order_table order by id DESC limit 1";
            myPreparedStatement = myConnection.prepareStatement(sql3);
            myResultSet = myPreparedStatement.executeQuery();
            int lastId = 0;
            while(myResultSet.next()) {
                lastId = myResultSet.getInt("id");
            }

            //3-
            String sql = "delete from order_products where order_id =?";

            //4
            myPreparedStatement = myConnection.prepareStatement(sql);

            //5
            myPreparedStatement.setInt(1, lastId);
            //6
            myPreparedStatement.execute();

            String sql2 = "delete from order_table where id=?";
            myPreparedStatement = myConnection.prepareStatement(sql2);
            myPreparedStatement.setInt(1, lastId);
            myPreparedStatement.executeUpdate();


        } finally {
            //7-

            close(myConnection, myPreparedStatement, myResultSet);
        }

    }


    private void close(Connection myConnection, Statement myStatement, ResultSet myResultSet) {

        try {
            if(myResultSet != null) {
                myConnection.close();
            }
            if(myStatement != null) {
                myStatement.close();
            }
            if(myConnection != null) {
                myConnection.close();
            }


        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private String orderNumberGenerator(Order myOrder, int id) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        String dateString = formatter.format(myOrder.getOrderDate());
        if(id >= 10 && id < 100) {
            return "ORD-" + dateString + "-00" + id;
        } else if(id >= 100 && id < 1000) {
            return "ORD-" + dateString + "-0" + id;
        }
        return "ORD-" + dateString + "-000" + id;
    }

}
