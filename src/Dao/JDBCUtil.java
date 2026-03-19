package Dao;


/* TODO: JDBC local của Phạm Duy

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    private static final String url = "jdbc:mysql://localhost:3306/quanlycuahangbansach?useUnicode=true&characterEncoding=UTF-8";
    private static final String user = "root";
    private static final String password = ""; // Điền mật khẩu MySQL 
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Lỗi: Không tìm thấy Driver kết nối MySQL");
        }
        return DriverManager.getConnection(url, user, password);
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
} */
