package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.CtPhieuNhapDTO;

public class CtPhieuNhapDAO {

    // Hàm thêm một dòng Chi tiết phiếu nhập
    public int insert(CtPhieuNhapDTO ctpn) {
        int ketQua = 0;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "INSERT INTO CTPHIEUNHAP (maphieunhap, masanpham, soluong, dongia) VALUES (?, ?, ?, ?)";
            
            pst = con.prepareStatement(sql);
            pst.setString(1, ctpn.getMaPhieuNhap());
            pst.setString(2, ctpn.getMaSanPham());
            pst.setInt(3, ctpn.getSoLuong());
            pst.setDouble(4, ctpn.getDonGia());
            
            ketQua = pst.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return ketQua;
    }

    public int insertAll(ArrayList<CtPhieuNhapDTO> listCTPN) {
        int ketQua = 0;
        for (CtPhieuNhapDTO ctpn : listCTPN) {
            ketQua += this.insert(ctpn);
        }
        return ketQua;
    }
}
