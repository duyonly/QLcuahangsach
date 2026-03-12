
package DAO;

import DTO.DanhMucChucNangDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DanhMucChucNangDAO {

    public ArrayList<DanhMucChucNangDTO> selectAll() {
        ArrayList<DanhMucChucNangDTO> list = new ArrayList<>();
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM DANHMUCCHUCNANG";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DanhMucChucNangDTO dm = new DanhMucChucNangDTO();
                dm.setMaChucNang(rs.getString("machucnang"));
                dm.setTenChucNang(rs.getString("tenchucnang"));
                list.add(dm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(con);
        }
        return list;
    }
}