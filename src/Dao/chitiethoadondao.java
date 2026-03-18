package Dao;


import java.sql.Connection;
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
}}
