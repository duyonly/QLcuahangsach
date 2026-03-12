package Dao;
import Dto.SanPhamDTO;
import java.sql.*;
import java.util.ArrayList;
public class SanPhamDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // Lấy danh sách sản phẩm
    public ArrayList<SanPhamDTO> getAll(){
        ArrayList<SanPhamDTO> list = new ArrayList<>();
        try{
            conn = ConnectDB.getConnection();
            String sql = "SELECT * FROM SanPham";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                SanPhamDTO sp = new SanPhamDTO();
                sp.setMasp(rs.getString("masp"));
                sp.setTensp(rs.getString("tensp"));
                sp.setTentg(rs.getString("tentacgia"));
                sp.setMatheloai(rs.getString("matheloai"));
                sp.setNamxuatban(rs.getInt("namxuatban"));
                sp.setGianhap(rs.getInt("gianhap"));
                sp.setGiaban(rs.getInt("giaban"));
                sp.setSoluongton(rs.getInt("soluongton"));
                sp.setMota(rs.getString("mota"));
                sp.setTrangthai(rs.getInt("trangthai"));

                list.add(sp);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    // Thêm sản phẩm
    public boolean insert(SanPhamDTO sp){
        try{
            conn = ConnectDB.getConnection();
            String sql = "INSERT INTO SanPham VALUES(?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);

            ps.setString(1, sp.getMasp());
            ps.setString(2, sp.getTensp());
            ps.setString(3, sp.getTentg());
            ps.setString(4, sp.getMatheloai());
            ps.setString(5, sp.getNhaxuatban());
            ps.setInt(6, sp.getNamxuatban());
            ps.setInt(7, sp.getGianhap());
            ps.setInt(8, sp.getGiaban());
            ps.setInt(9, sp.getSoluongton());
            ps.setString(10, sp.getMota());
            ps.getInt(11, sp.getTrangthai());

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    // Xóa sản phẩm
    public boolean delete(String masp){
        try{
            conn = ConnectDB.getConnection();
            String sql = "DELETE FROM SanPham WHERE masp=?";
            ps = conn.prepareStatement(sql);

            ps.setString(1, masp);

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    // Sửa sản phẩm
    public boolean update(SanPhamDTO sp){
        try{
            conn = ConnectDB.getConnection();
            String sql = "UPDATE SanPham SET tensp=?, tentacgia=?, matheloai=?, nhaxuatban=?, namxuatban=?, gianhap=? WHERE masp=?";
            ps = conn.prepareStatement(sql);

            ps.setString(1, sp.getTensp());
            ps.setString(2, sp.getTentg());
            ps.setString(3, sp.getMatheloai());
            ps.setString(4, sp.getNhaxuatban());
            ps.setInt(5, sp.getNamxuatban());
            ps.setInt(6, sp.getGianhap());
            ps.setString(7, sp.getMasp());
            ps.setInt(8, sp.getGiaban());
            ps.setInt(9, sp.getSoluongton());
            ps.setString(10, sp.getMota());
            ps.getInt(11, sp.getTrangthai());


            return ps.executeUpdate() > 0;

        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    // Tìm kiếm theo mã sản phẩm
    public SanPhamDTO findByID(String masp){
        try{
            conn = ConnectDB.getConnection();
            String sql = "SELECT * FROM SanPham WHERE masp=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, masp);

            rs = ps.executeQuery();

            if(rs.next()){
                SanPhamDTO sp = new SanPhamDTO();
                sp.setMasp(rs.getString("masp"));
                sp.setTensp(rs.getString("tensp"));
                sp.setTentg(rs.getString("tentacgia"));
                sp.setMatheloai(rs.getString("matheloai"));
                sp.setNamxuatban(rs.getInt("namxuatban"));
                sp.setGianhap(rs.getInt("gianhap"));

                return sp;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // Tìm kiếm theo tên
    public ArrayList<SanPhamDTO> searchByName(String keyword){
        ArrayList<SanPhamDTO> list = new ArrayList<>();
        try{
            conn = ConnectDB.getConnection();
            String sql = "SELECT * FROM SanPham WHERE tensp LIKE ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+keyword+"%");

            rs = ps.executeQuery();

            while(rs.next()){
                SanPhamDTO sp = new SanPhamDTO();
                sp.setMasp(rs.getString("masp"));
                sp.setTensp(rs.getString("tensp"));
                sp.setTentg(rs.getString("tentacgia"));
                sp.setMatheloai(rs.getString("matheloai"));
                sp.setNamxuatban(rs.getInt("namxuatban"));
                sp.setGianhap(rs.getInt("gianhap"));
                sp.setGiaban(rs.getInt("giaban"));
                sp.setSoluongton(rs.getInt("soluongton"));
                sp.setMota(rs.getString("mota"));
                sp.setTrangthai(rs.getInt("trangthai"));

                list.add(sp);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
