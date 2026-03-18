package Dao;
import java.sql.*;
import java.util.ArrayList;
import Dto.TaiKhoanDTO;
public class TaiKhoanDao {
   
       
    
        public ArrayList<TaiKhoanDTO> getAll() {
            ArrayList<TaiKhoanDTO> list = new ArrayList<>();
            try ( Connection conn = ConnectDB.getConnection();){
                String sql = "SELECT * FROM taikhoan";
                ResultSet rs = conn.createStatement().executeQuery(sql);
    
                while (rs.next()) {
                    list.add(new TaiKhoanDTO(
                        rs.getString("MaTK"),
                        rs.getString("MaNV"),
                        rs.getString("TenDangNhap"),
                        rs.getString("MatKhau"),
                        rs.getString("QuyenQuanLy"),
                        rs.getString("TrangThai"),
                        rs.getString("Email")
                    ));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
    
        public boolean insert(TaiKhoanDTO tk) {
            try(Connection conn = ConnectDB.getConnection();) {
                String sql = "INSERT INTO taikhoan VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, tk.getMaTK());
                ps.setString(2, tk.getMaNV());
                ps.setString(3, tk.getTenDangNhap());
                ps.setString(4, tk.getMatKhau());
                ps.setString(5, tk.getQuyen());
                ps.setString(6, tk.getTrangThai());
                ps.setString(7, tk.getEmail());
                return ps.executeUpdate() > 0;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    
        public boolean update(TaiKhoanDTO tk) {
            try(Connection conn = ConnectDB.getConnection();) {
                String sql = "UPDATE taikhoan SET MaNV=?, TenDangNhap=?, MatKhau=?, QuyenQuanLy=?, TrangThai=?, Email=? WHERE MaTK=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, tk.getMaNV());
                ps.setString(2, tk.getTenDangNhap());
                ps.setString(3, tk.getMatKhau());
                ps.setString(4, tk.getQuyen());
                ps.setString(5, tk.getTrangThai());
                ps.setString(6, tk.getEmail());
                ps.setString(7, tk.getMaTK());
                return ps.executeUpdate() > 0;
            } catch (Exception e) {
                return false;
            }
        }
    
        public boolean delete(String maTK) {
            try(Connection conn = ConnectDB.getConnection();) {
                String sql = "DELETE FROM taikhoan WHERE MaTK=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, maTK);
                return ps.executeUpdate() > 0;
            } catch (Exception e) {
                return false;
            }
        }
      
public ArrayList<TaiKhoanDTO> search(String value) {
    ArrayList<TaiKhoanDTO> list = new ArrayList<>();
    try (Connection conn = ConnectDB.getConnection()) {
        // Sử dụng JOIN để lấy TenNV và tìm kiếm trên nhiều cột cùng lúc
        String sql = "SELECT tk.* FROM taikhoan tk " +
                     "JOIN nhanvien nv ON tk.MaNV = nv.MaNV " +
                     "WHERE tk.MaTK LIKE ? OR tk.MaNV LIKE ? OR nv.TenNV LIKE ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        String searchKey = "%" + value + "%";
        ps.setString(1, searchKey);
        ps.setString(2, searchKey);
        ps.setString(3, searchKey);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new TaiKhoanDTO(
                rs.getString("MaTK"), rs.getString("MaNV"),
                rs.getString("TenDangNhap"), rs.getString("MatKhau"),
                rs.getString("QuyenQuanLy"), rs.getString("TrangThai"),
                rs.getString("Email")
            ));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}
    
}


