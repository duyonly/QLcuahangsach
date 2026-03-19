package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Dto.PhieuNhapDTO;

public class PhieuNhapDAO {

    // 1. Hàm truy vấn mã phiếu nhập cuối cùng (Dùng để sinh mã tự động)
    public String getLastMaPhieuNhap() throws SQLException {
        String lastMa = null;
        Connection con = null;
        try {
            con = ConnectDB.getConnection();
            String sql = "SELECT maphieunhap FROM PHIEU_NHAP ORDER BY maphieunhap DESC LIMIT 1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                lastMa = rs.getString("maphieunhap");
            }
        } finally {
            ConnectDB.close(con); 
        }
        return lastMa;
    }

    // 2. Hàm Thêm (Đã bỏ catch, ném lỗi lên bằng throws)
    public int insert(PhieuNhapDTO pn) throws SQLException {
        int ketQua = 0;
        Connection con = null;
        try {
            con = ConnectDB.getConnection();  
            String sql = "INSERT INTO PHIEU_NHAP (maphieunhap, thoigian, trangthai, manhacungcap, nguoitaophieunhap) "
                       + "VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, pn.getMaPhieuNhap());
            pst.setTimestamp(2, pn.getThoiGian()); 
            pst.setString(3, pn.getTrangThai());
            pst.setString(4, pn.getMaNhaCungCap());
            pst.setString(5, pn.getNguoitaoPhieuNhap());
            
            ketQua = pst.executeUpdate();
            
        } finally {
            ConnectDB.close(con);  
        }
        return ketQua;
    }

    // 3. Hàm Lấy tất cả
    public ArrayList<PhieuNhapDTO> selectAll() throws SQLException {
        ArrayList<PhieuNhapDTO> ketQua = new ArrayList<>();
        Connection con = null;
        try {
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM PHIEU_NHAP";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                PhieuNhapDTO pn = new PhieuNhapDTO();
                pn.setMaPhieuNhap(rs.getString("maphieunhap"));
                pn.setThoiGian(rs.getTimestamp("thoigian"));
                pn.setTrangThai(rs.getString("trangthai"));
                pn.setMaNhaCungCap(rs.getString("manhacungcap"));
                pn.setNguoitaoPhieuNhap(rs.getString("nguoitaophieunhap"));
                ketQua.add(pn);
            }
        } finally {
            ConnectDB.close(con);
        }
        return ketQua;
    }
}