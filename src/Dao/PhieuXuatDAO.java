package Dao;

import Dto.PhieuXuatDTO;
import java.sql.*;
import java.util.ArrayList;

public class PhieuXuatDAO {

    Connection conn;

    public ArrayList<PhieuXuatDTO> getAll(){

        ArrayList<PhieuXuatDTO> list = new ArrayList<>();

        try{
            conn = ConnectDB.getConnection();

            String sql = "SELECT * FROM PhieuXuat";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                PhieuXuatDTO px = new PhieuXuatDTO();

                px.setMaphieuxuat(rs.getString("maphieuxuat"));
                px.setThoigian(rs.getTimestamp("thoigian"));
                px.setTrangthai(rs.getString("trangthai"));
                px.setNguoitaophieuxuat(rs.getString("nguoitaophieuxuat"));
                px.setMakhachhang(rs.getString("makhachhang"));

                list.add(px);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public boolean insert(PhieuXuatDTO px){

        try{

            conn = ConnectDB.getConnection();

            String sql = "INSERT INTO PhieuXuat VALUES(?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, px.getMaphieuxuat());
            ps.setTimestamp(2, new Timestamp(px.getThoigian().getTime()));
            ps.setString(3, px.getTrangthai());
            ps.setString(4, px.getNguoitaophieuxuat());
            ps.setString(5, px.getMakhachhang());

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(PhieuXuatDTO px){

        try{

            conn = ConnectDB.getConnection();

            String sql = "UPDATE PhieuXuat SET thoigian=?, trangthai=?, nguoitaophieuxuat=?, makhachhang=? WHERE maphieuxuat=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setTimestamp(1, new Timestamp(px.getThoigian().getTime()));
            ps.setString(2, px.getTrangthai());
            ps.setString(3, px.getNguoitaophieuxuat());
            ps.setString(4, px.getMakhachhang());
            ps.setString(5, px.getMaphieuxuat());

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(String ma){

        try{

            conn = ConnectDB.getConnection();

            String sql = "DELETE FROM PhieuXuat WHERE maphieuxuat=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, ma);

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
    
}