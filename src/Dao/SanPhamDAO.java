package DAO;
import DTO.SanPhamDTO;
import Dao.ConnectDB;
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
            String sql = "SELECT * FROM sach";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                SanPhamDTO sp = new SanPhamDTO();
                sp.setMasp(rs.getString("MaSach"));
                sp.setTensp(rs.getString("TenSach"));
                sp.setTentg(rs.getString("TenTacGia"));
                sp.setMatheloai(rs.getString("MaTL"));
                sp.setNhaxuatban(rs.getString("NhaXuatBan"));
                sp.setNamxuatban(rs.getInt("NamXuatBan"));
                sp.setGianhap(rs.getInt("GiaNhap"));
                sp.setGiaban(rs.getInt("GiaBan"));
                sp.setSoluongton(rs.getInt("SoLuongTon"));
                sp.setTrangthai(rs.getString("TrangThai"));

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
            String sql = "INSERT INTO sach(MaSach, TenSach, TenTacGia, MaTL, NhaXuatBan, NamXuatBan, GiaNhap, GiaBan, SoLuongTon, MoTa, TrangThai) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
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
            ps.setString(11, sp.getTrangthai());

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
            String sql = "UPDATE sach SET TrangThai='Ngung ban' WHERE MaSach=?";
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
            String sql = "UPDATE sach SET TenSach=?, TenTacGia=?, MaTL=?, NhaXuatBan=?, NamXuatBan=?, GiaNhap=?, GiaBan=?, SoLuongTon=?, MoTa=?, TrangThai=? WHERE MaSach=?";
            ps = conn.prepareStatement(sql);

            ps.setString(1, sp.getTensp());
            ps.setString(2, sp.getTentg());
            ps.setString(3, sp.getMatheloai());
            ps.setString(4, sp.getNhaxuatban());
            ps.setInt(5, sp.getNamxuatban());
            ps.setInt(6, sp.getGianhap());
            ps.setInt(7, sp.getGiaban());
            ps.setInt(8, sp.getSoluongton());
            ps.setString(9, sp.getMota());
            ps.setString(10, sp.getTrangthai());
            ps.setString(11, sp.getMasp());


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
            String sql = "SELECT * FROM sach WHERE MaSach=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, masp);

            rs = ps.executeQuery();

            if(rs.next()){
                SanPhamDTO sp = new SanPhamDTO();
                sp.setMasp(rs.getString("MaSach"));
                sp.setTensp(rs.getString("TenSach"));
                sp.setTentg(rs.getString("TenTacGia"));
                sp.setMatheloai(rs.getString("MaTL"));
                sp.setNamxuatban(rs.getInt("NamXuatBan"));
                sp.setGianhap(rs.getInt("GiaNhap"));
                sp.setSoluongton(rs.getInt("SoLuongTon"));
                sp.setMota(rs.getString("MoTa"));
                sp.setTrangthai(rs.getString("TrangThai"));
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
            String sql = "SELECT * FROM sach WHERE TenSach LIKE ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+keyword+"%");

            rs = ps.executeQuery();

            while(rs.next()){
                SanPhamDTO sp = new SanPhamDTO();
                sp.setMasp(rs.getString("MaSach"));
                sp.setTensp(rs.getString("TenSach"));
                sp.setTentg(rs.getString("TenTacGia"));
                sp.setMatheloai(rs.getString("MaTL"));
                sp.setNamxuatban(rs.getInt("NamXuatBan"));
                sp.setGianhap(rs.getInt("GiaNhap"));
                sp.setGiaban(rs.getInt("GiaBan"));
                sp.setSoluongton(rs.getInt("SoLuongTon"));
                sp.setMota(rs.getString("MoTa"));
                sp.setTrangthai(rs.getString("TrangThai"));

                list.add(sp);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public boolean updateTrangThai(String maSP, String trangThai) {
        String sql = "UPDATE sach SET TrangThai = ? WHERE MaSach = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, trangThai);
            ps.setString(2, maSP);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
