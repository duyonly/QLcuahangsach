package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static final String url="jdbc:mysql://localhost:3306/qlbansach?useSSL=false&serverTimezone=UTC";
    private static final String user="root";
    private static final String password="";
    //lấy kết nối
public static Connection getConnection() throws SQLException{
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
    catch(ClassNotFoundException e){
        System.out.print("không thể kết nối đc");
    }
    return DriverManager.getConnection(url,user,password);
}
public static void close(Connection con){
    try{
          if(con != null) con.close();
    }catch(SQLException ex){
        ex.printStackTrace();
    }
}
}
