package be.ali.opdracht;

import java.sql.*;
public class ProductUtil {
    private String dbUrl;
    private String username;
    private String password;

    public ProductUtil(String dbUrl, String username, String password) {
        this.dbUrl = dbUrl;
        this.username = username;
        this.password = password;
    }

    public void addProduct(Product theProduct) throws Exception {
        Connection myConnection = null;
        PreparedStatement myStatement = null;

        try {
            myConnection = DriverManager.getConnection(dbUrl,username,password);
            String sql = "insert into order_products " + "(order_id, product_name, amount, price_per_unit) " + "values (?, ?, ?,?)";

            myStatement = myConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            myStatement.setInt(1,theProduct.getOrderID());
            myStatement.setString(2, theProduct.getProductName());
            myStatement.setInt(3, theProduct.getAmount());
            myStatement.setBigDecimal(4, theProduct.getPricePerUnit());

            myStatement.execute();
            ResultSet newRs = myStatement.getGeneratedKeys();
            while (newRs.next()){
                System.out.println("The id is"+newRs.getInt(1));
            }
        } finally {
            close(myConnection, myStatement, null);

        }

    }


    private void close(Connection myConnection, Statement myStatement, ResultSet myResultSet) {

        try{
            if(myResultSet!=null){
                myConnection.close();
            }
            if(myStatement!=null){
                myStatement.close();
            }
            if(myConnection != null){
                myConnection.close();
            }


        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
