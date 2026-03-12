package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    private static final String url=" ";
    private static final String user="root";
    private static final String password="";
public static Connection getConnection() throws SQLException{
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
    catch(ClassNotFoundException e){
        System.out.print("không thể kết nối đến database");
    }
    return DriverManager.getConnection(url,user,password);
}
public static void closeConnection(Connection con){
    try{
          if(con != null) con.close();
    }catch(SQLException ex){
        ex.printStackTrace();
    }
}
}
