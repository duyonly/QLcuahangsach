package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import Dto.CtPhieuNhapDTO;

public class CtPhieuNhapDAO {

    public int insertAll(ArrayList<CtPhieuNhapDTO> listCTPN) throws SQLException {
        int ketQua = 0;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = JDBCUtil.getConnection();
            con.setAutoCommit(false); 
            
            String sql = "INSERT INTO CT_PHIEUNHAP (maphieunhap, masanpham, soluong, dongia) VALUES (?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
            
            for (CtPhieuNhapDTO ctpn : listCTPN) {
                pst.setString(1, ctpn.getMaPhieuNhap());
                pst.setString(2, ctpn.getMaSanPham());
                pst.setInt(3, ctpn.getSoLuong());
                pst.setDouble(4, ctpn.getDonGia());
                pst.addBatch(); 
            }
            
            int[] results = pst.executeBatch(); 
            con.commit(); 
            ketQua = results.length;
            
        } catch (SQLException e) {
            if (con != null) {
                try { con.rollback(); } catch (SQLException ex) {} // Hủy nếu lỗi
            }
            throw e; // RẤT QUAN TRỌNG: Ném thẳng lỗi lên trên BUS để xử lý
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return ketQua;
    }
}