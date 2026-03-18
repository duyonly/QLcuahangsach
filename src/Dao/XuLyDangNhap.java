package Dao;
import Dto.TaiKhoanDTO;
import java.sql.*;
public class XuLyDangNhap {
public TaiKhoanDTO dangNhap(TaiKhoanDTO tk){
    String sql="Select  MaNV,QuyenQuanLy From taikhoan where TenDangNhap=? and MatKhau=?";
    try(Connection con=ConnectDB.getConnection()){
        PreparedStatement stmt=con.prepareStatement(sql);
        stmt.setString(1, tk.getTenDangNhap());
        stmt.setString(2, tk.getMatKhau());
        ResultSet rs=stmt.executeQuery();
        
        if(rs.next()){
            TaiKhoanDTO result = new TaiKhoanDTO();
            result.setMaNV(rs.getString("MaNV"));
            result.setQuyen(rs.getString("QuyenQuanLy"));
            return result;
        }
    }catch(Exception e){
        e.printStackTrace();
       
    }
    return null;
}
public boolean kiemTraEmail(TaiKhoanDTO tk){
    String sql="SELECT COUNT(*) FROM taikhoan where TenDangNhap=? and Email=?";
    try(Connection con = ConnectDB.getConnection()){
        PreparedStatement stmt=con.prepareStatement(sql);
        stmt.setString(1, tk.getTenDangNhap());
        stmt.setString(2, tk.getEmail());
        ResultSet rs=stmt.executeQuery();
       rs.next();
       return rs.getInt(1)>0;
    }catch(Exception e){
        e.printStackTrace();
    }
    return false;
}
public boolean capNhatMatKhau(TaiKhoanDTO tk){
    String sql="UPDATE taikhoan SET MatKhau=? WHERE TenDangNhap=?";
    try(Connection con=ConnectDB.getConnection()){
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, tk.getMatKhau());
        stmt.setString(2, tk.getTenDangNhap());
        return stmt.executeUpdate()>0;

    }catch(Exception e){
        e.printStackTrace();
    }
    return false;
}
}