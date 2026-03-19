package Dao;

import java.sql.*;
import java.util.ArrayList;
import Dto.HoaDonDto;
public class HoaDonDAO {
    public ArrayList<HoaDonDto> getAll() {
        ArrayList<HoaDonDto> list = new ArrayList<>();
        String sql = "SELECT * FROM hoadon";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HoaDonDto hd = new HoaDonDto(
                        rs.getString("MaHD"),
                        rs.getString("NgayLap"),
                        rs.getString("MaNV"),
                        rs.getString("MaKH"),
                        rs.getDouble("TongTien"),
                        rs.getString("TrangThai")
                );
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean insert(HoaDonDto hd) {
        String sql = "INSERT INTO hoadon VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, hd.getMaHD());
            ps.setString(2, hd.getNgayLap());
            ps.setString(3, hd.getMaNV());
            ps.setString(4, hd.getMaKH());
            ps.setDouble(5, hd.getTongTien());
            ps.setString(6, hd.getTrangThai());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    public ArrayList<HoaDonDto> search(String keyword) {
        ArrayList<HoaDonDto> list = new ArrayList<>();
        String sql = "SELECT * FROM hoadon WHERE MaHD LIKE ? OR MaKH LIKE ? OR MaNV LIKE ?";
    
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
    
            String key = "%" + keyword + "%";
            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);
    
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonDto hd = new HoaDonDto(
                        rs.getString("MaHD"),
                        rs.getString("NgayLap"),
                        rs.getString("MaNV"),
                        rs.getString("MaKH"),
                        rs.getDouble("TongTien"),
                        rs.getString("TrangThai")
                );
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean update(HoaDonDto hd) {
        String sql = "UPDATE hoadon SET TongTien=?, TrangThai=? WHERE MaHD=?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, hd.getTongTien());
            ps.setString(2, hd.getTrangThai());
            ps.setString(3, hd.getMaHD());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean delete(String maHD) {
        String sql = "DELETE FROM hoadon WHERE MaHD=?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maHD);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}

