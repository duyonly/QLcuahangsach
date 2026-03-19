package Dao;


import java.util.ArrayList;
import java.sql.*;

import Dto.chitiethoadon;

public class chitiethoadondao {
    public ArrayList<chitiethoadon> CTHD(String maHD){
        ArrayList<chitiethoadon> list = new ArrayList<>();
String sql;
if(maHD==null|| maHD.isEmpty()) {
    sql="select * from chitiethoadon";
}
else{
    sql="select * from chitiethoadon where MaHD=?";
}
try(
    Connection con= ConnectDB.getConnection();
    PreparedStatement pr=con.prepareStatement(sql);
){
   
   
    if(maHD!=null  && !maHD.isEmpty()){
        pr.setString(1, maHD);
    }
    ResultSet rs=pr.executeQuery();
    while (rs.next()) {
        chitiethoadon chitiet=new chitiethoadon();
        chitiet.setMaHD(rs.getString("MaHD"));
        chitiet.setMaSach(rs.getString("MaSach"));
        chitiet.setSoLuong(rs.getInt("SoLuongMua"));
        chitiet.setDonGia(rs.getDouble("DonGia"));
        chitiet.setThanhTien(rs.getDouble("ThanhTien"));
        list.add(chitiet);
    }
    
}
 catch (Exception e) {
    e.printStackTrace();
}
return list;
}
public ArrayList<chitiethoadon> search(String keyword) {
    ArrayList<chitiethoadon> list = new ArrayList<>();
    String sql = "SELECT * FROM chitiethoadon WHERE MaHD LIKE ? OR MaSach LIKE ?";

    try (Connection con = ConnectDB.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        String key = "%" + keyword + "%";
        ps.setString(1, key);
        ps.setString(2, key);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            chitiethoadon ct = new chitiethoadon();
            ct.setMaHD(rs.getString("MaHD"));
            ct.setMaSach(rs.getString("MaSach"));
            ct.setSoLuong(rs.getInt("SoLuongMua"));
            ct.setDonGia(rs.getDouble("DonGia"));
            ct.setThanhTien(rs.getDouble("ThanhTien"));
            list.add(ct);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}
public boolean insert(chitiethoadon ct) {
    String sql = "INSERT INTO chitiethoadon VALUES (?, ?, ?, ?, ?)";
    try (Connection con = ConnectDB.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, ct.getMaHD());
        ps.setString(2, ct.getMaSach());
        ps.setInt(3, ct.getSoLuong());
        ps.setDouble(4, ct.getDonGia());
        ps.setDouble(5, ct.getThanhTien());

        return ps.executeUpdate() > 0;
    } catch (Exception e) {
        return false;
    }
}
public boolean update(chitiethoadon ct) {
    String sql = "UPDATE chitiethoadon SET SoLuongMua=?, DonGia=?, ThanhTien=? WHERE MaHD=? AND MaSach=?";
    try (Connection con = ConnectDB.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, ct.getSoLuong());
        ps.setDouble(2, ct.getDonGia());
        ps.setDouble(3, ct.getThanhTien());
        ps.setString(4, ct.getMaHD());
        ps.setString(5, ct.getMaSach());

        return ps.executeUpdate() > 0;
    } catch (Exception e) {
        return false;
    }
}
public boolean delete(String maHD, String maSach) {
    String sql = "DELETE FROM chitiethoadon WHERE MaHD=? AND MaSach=?";
    try (Connection con = ConnectDB.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, maHD);
        ps.setString(2, maSach);

        return ps.executeUpdate() > 0;
    } catch (Exception e) {
        return false;
    }
}
}
