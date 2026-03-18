package Dao;

import Dto.ChiTietPhieuXuatDTO;
import java.sql.*;
import java.util.ArrayList;

public class ChiTietPhieuXuatDAO {

    Connection conn;

    public ArrayList<ChiTietPhieuXuatDTO> getAll(){

        ArrayList<ChiTietPhieuXuatDTO> list = new ArrayList<>();

        try{

            conn = ConnectDB.getConnection();

            String sql = "SELECT * FROM ChiTietPhieuXuat";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                ChiTietPhieuXuatDTO ct = new ChiTietPhieuXuatDTO();

                ct.setMaphieuxuat(rs.getString("maphieuxuat"));
                ct.setMasanpham(rs.getString("masanpham"));
                ct.setSoluong(rs.getInt("soluong"));
                ct.setDongia(rs.getDouble("dongia"));

                list.add(ct);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public boolean insert(ChiTietPhieuXuatDTO ct){

        try{

            conn = ConnectDB.getConnection();

            String sql = "INSERT INTO ChiTietPhieuXuat VALUES(?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, ct.getMaphieuxuat());
            ps.setString(2, ct.getMasanpham());
            ps.setInt(3, ct.getSoluong());
            ps.setDouble(4, ct.getDongia());

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(String maphieuxuat, String masanpham){

        try{

            conn = ConnectDB.getConnection();

            String sql = "DELETE FROM ChiTietPhieuXuat WHERE maphieuxuat=? AND masanpham=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, maphieuxuat);
            ps.setString(2, masanpham);

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
    public ArrayList<ChiTietPhieuXuatDTO> getByMaPhieuXuat(String maPX){

    ArrayList<ChiTietPhieuXuatDTO> list = new ArrayList<>();

    try{

        conn = ConnectDB.getConnection();

        String sql = "SELECT * FROM ChiTietPhieuXuat WHERE maphieuxuat=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, maPX);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            ChiTietPhieuXuatDTO ct = new ChiTietPhieuXuatDTO();

            ct.setMaphieuxuat(rs.getString("maphieuxuat"));
            ct.setMasanpham(rs.getString("masanpham"));
            ct.setSoluong(rs.getInt("soluong"));
            ct.setDongia(rs.getDouble("dongia"));

            list.add(ct);
        }

    }catch(Exception e){
        e.printStackTrace();
    }

    return list;
}
}