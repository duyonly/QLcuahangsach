package Gui;
import java.sql.Connection;
import java.sql.SQLException;

import Dao.ConnectDB;
public class Main {
      public static void main(String[] args){
      try{
            Connection con=ConnectDB.getConnection();
       
            System.out.print("ket noi voi sql thanh cong");
        
            ConnectDB.close(con);
    }catch(SQLException e){
        e.printStackTrace();
    }
      }
}
