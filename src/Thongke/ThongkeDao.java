package Thongke;

import Dao.ConnectDB;
import Thongke.Model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThongkeDao{

    private Connection con;
    public ThongkeDao(){
        try{
            con = ConnectDB.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       // con = ConnectDB.getConnection();
    }
    public double getTongDoanhThu() throws SQLException{
        String sql = "SELECT SUM(TongTien) FROM hoadon WHERE TrangThai='Đã thanh toán'";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getDouble(1);
        return 0;
    }
    public double getTongNhap() throws SQLException{
        String sql = "SELECT SUM(TongTien) FROM phieunhap";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getDouble(1);
        return 0;
    }
    // Top 5 sach ban chay nhat
    public List<TopsachModel> getTopSach() throws SQLException{
        List<TopsachModel> list = new ArrayList<>();
        String sql = """
            SELECT s.TenSach, SUM(ct.SoLuongMua) AS SoLuongBan
            FROM chitiethoadon ct JOIN sach s ON ct.MaSach = s.MaSach JOIN hoadon h ON ct.MaHD = h.MaHD WHERE h.TrangThai='Đã thanh toán' GROUP BY s.TenSach ORDER BY SoLuongBan DESC LIMIT 5
        """;
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            list.add(new TopsachModel(
                    rs.getString("TenSach"),
                    rs.getInt("SoLuongBan")
            ));
        }
        return list;
    }
    // Doanh thu theo thang
    public List<DoanhthuthangModel> getDoanhThuTheoThang() throws SQLException{
        List<DoanhthuthangModel> list = new ArrayList<>();
        String sql = """
            SELECT MONTH(NgayLap) AS Thang, SUM(TongTien) AS DoanhThu FROM hoadon WHERE TrangThai='Đã thanh toán' GROUP BY MONTH(NgayLap)
        """;
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            list.add(new DoanhthuthangModel(
                    rs.getInt("Thang"),
                    rs.getDouble("DoanhThu")
            ));
        }
        return list;
    }
    // Thong ke nhanvien
    public List<ThongkenhanvienModel> getThongKeNhanVien() throws SQLException{
        List<ThongkenhanvienModel> list = new ArrayList<>();
        String sql = """
            SELECT nv.MaNV, nv.TenNV, SUM(h.TongTien) AS DoanhThu FROM hoadon h JOIN nhanvien nv ON h.MaNV = nv.MaNV WHERE h.TrangThai='Đã thanh toán' GROUP BY nv.MaNV, nv.TenNV ORDER BY DoanhThu DESC
        """;
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            list.add(new ThongkenhanvienModel(
                    rs.getString("MaNV"),
                    rs.getString("TenNV"),
                    rs.getDouble("DoanhThu")
            ));
        }
        return list;
    }
}