package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.PhieuNhapDTO;
import DAO.JDBCUtil;

public class PhieuNhapDAO {

    // Hàm thêm mới một Phiếu Nhập vào Database
    public int insert(PhieuNhapDTO pn) {
        int ketQua = 0;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = JDBCUtil.getConnection();  
            String sql = "INSERT INTO PHIEUNHAP (maphieunhap, thoigian, trangthai, manhacungcap, nguoitaophieunhap) "
                       + "VALUES (?, ?, ?, ?, ?)";
            
            pst = con.prepareStatement(sql);
            pst.setString(1, pn.getMaPhieuNhap());
            pst.setDate(2, pn.getThoiGian()); // Sử dụng java.sql.Date
            pst.setString(3, pn.getTrangThai());
            pst.setString(4, pn.getMaNhaCungCap());
            pst.setString(5, pn.getNguoitaoPhieuNhap());
            
            ketQua = pst.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);  
        }
        return ketQua;
    }

    // Hàm lấy danh sách tất cả phiếu nhập 
    public ArrayList<PhieuNhapDTO> selectAll() {
        ArrayList<PhieuNhapDTO> ketQua = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM PHIEUNHAP";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                PhieuNhapDTO pn = new PhieuNhapDTO();
                pn.setMaPhieuNhap(rs.getString("maphieunhap"));
                pn.setThoiGian(rs.getDate("thoigian"));
                pn.setTrangThai(rs.getString("trangthai"));
                pn.setMaNhaCungCap(rs.getString("manhacungcap"));
                pn.setNguoitaoPhieuNhap(rs.getString("nguoitaophieunhap"));
                ketQua.add(pn);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }
}
