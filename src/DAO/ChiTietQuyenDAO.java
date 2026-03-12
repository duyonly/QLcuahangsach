package DAO;

import DTO.ChiTietQuyenDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChiTietQuyenDAO {

    // Lấy danh sách chi tiết quyền dựa trên Mã Nhóm Quyền (Dùng để hiển thị lên bảng giao diện)
    public ArrayList<ChiTietQuyenDTO> selectByMaNhomQuyen(String maNhomQuyen) {
        ArrayList<ChiTietQuyenDTO> list = new ArrayList<>();
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM CTQUYEN WHERE manhomquyen = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, maNhomQuyen);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ChiTietQuyenDTO ct = new ChiTietQuyenDTO();
                ct.setMaNhomQuyen(rs.getString("manhomquyen"));
                ct.setMaChucNang(rs.getString("machucnang"));
                ct.setHanhDong(rs.getString("hanhdong"));
                list.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return list;
    }

    // Thêm một dòng chi tiết quyền
    public int insert(ChiTietQuyenDTO ct) {
        int ketQua = 0;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "INSERT INTO CTQUYEN (manhomquyen, machucnang, hanhdong) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, ct.getMaNhomQuyen());
            pst.setString(2, ct.getMaChucNang());
            pst.setString(3, ct.getHanhDong());
            ketQua = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return ketQua;
    }

    // Cập nhật quyền cho một chức năng cụ thể của một nhóm
    public int update(ChiTietQuyenDTO ct) {
        int ketQua = 0;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "UPDATE CTQUYEN SET hanhdong = ? WHERE manhomquyen = ? AND machucnang = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, ct.getHanhDong());
            pst.setString(2, ct.getMaNhomQuyen());
            pst.setString(3, ct.getMaChucNang());
            ketQua = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return ketQua;
    }

    // Xóa tất cả các quyền của một nhóm (Thường dùng trước khi lưu phân quyền mới để làm sạch dữ liệu cũ)
    public int deleteByMaNhom(String maNhomQuyen) {
        int ketQua = 0;
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "DELETE FROM CTQUYEN WHERE manhomquyen = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, maNhomQuyen);
            ketQua = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return ketQua;
    }
}