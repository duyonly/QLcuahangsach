package Nhanvien;

import Dao.ConnectDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
public class NhanvienDao implements NhanvienDaointerface  {

    @Override
    public boolean insert(NhanvienModel nv) {
// thêm trangthai vao
        String sql = "INSERT INTO nhanvien(`manv`, `tennv`, `gioitinh`, `ngaysinh`, `diachi`, `chucvu`, `luong`, `tendangnhap`, `matkhau`, `trang_thai`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, nv.getMaNV());
            pst.setString(2, nv.getTenNV());
            pst.setString(3, nv.getGioiTinh());
            pst.setDate(4, Date.valueOf(nv.getNgaySinh()));
            pst.setString(5, nv.getDiaChi());
            pst.setString(6, nv.getChucVu());
            pst.setDouble(7, nv.getLuong());
            pst.setString(8, nv.getTenDangNhap());
            pst.setString(9, nv.getMatKhau());
            pst.setInt(10, nv.getTrang_thai());
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean update(NhanvienModel nv){

        String sql = """ 
                UPDATE nhanvien SET tennv=?, gioitinh=?,  ngaysinh=?, diachi=?, chucvu=?, luong=?, tendangnhap=?, matkhau=?,trang_thai=? WHERE manv= ? """;
        try (Connection con = ConnectDB.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)){
            pst.setString(1, nv.getTenNV());
            pst.setString(2, nv.getGioiTinh());

            if (nv.getNgaySinh() != null){
                pst.setDate(3, Date.valueOf(nv.getNgaySinh()));
            } else{
                pst.setNull(3, Types.DATE);
            }
            pst.setString(4, nv.getDiaChi());
            pst.setString(5, nv.getChucVu());
            pst.setDouble(6, nv.getLuong());
            pst.setString(7, nv.getTenDangNhap());
            pst.setString(8, nv.getMatKhau());
            pst.setInt(9, nv.getTrang_thai());
            pst.setString(10, nv.getMaNV());

            return pst.executeUpdate() > 0;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean updateTrangThaiNghiViec(String maNV){
        String sql = "UPDATE nhanvien SET trang_thai = 0 WHERE manv = ? AND trang_thai = 1";
        try(
                Connection con = ConnectDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)
        )
        {
            pst.setString(1, maNV);
            return pst.executeUpdate() > 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

public boolean delete(String maNV) {

    String sql = """
        UPDATE nhanvien SET trang_thai = -1 WHERE manv = ?
    """;
    try (Connection con = ConnectDB.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setString(1, maNV);
        return pst.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
    @Override
    public NhanvienModel selectById(String maNV) {
        String sql = "SELECT * FROM nhanvien WHERE manv=?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, maNV);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new NhanvienModel(
                        rs.getString("manv"),
                        rs.getString("tennv"),
                        rs.getString("gioitinh"),
                        rs.getDate("ngaysinh").toLocalDate(),
                        rs.getString("diachi"),
                        rs.getString("chucvu"),
                        rs.getDouble("luong"),
                        rs.getString("tendangnhap"),
                        rs.getString("matkhau"),
                        rs.getInt("trang_thai")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private NhanvienModel mapNhanVien(ResultSet rs) throws Exception {
        NhanvienModel nv = new NhanvienModel();
        nv.setMaNV(rs.getString("manv"));
        nv.setTenNV(rs.getString("tennv"));
        nv.setGioiTinh(rs.getString("gioitinh"));
        java.sql.Date sqlDate = rs.getDate("ngaysinh");
        if (sqlDate != null) {
            nv.setNgaySinh(sqlDate.toLocalDate());
        }
        nv.setDiaChi(rs.getString("diachi"));
        nv.setChucVu(rs.getString("chucvu"));
        nv.setLuong(rs.getDouble("luong"));
        nv.setTenDangNhap(rs.getString("tendangnhap"));
        nv.setTrang_thai(rs.getInt("trang_thai"));
        return nv;
    }
    @Override
    public List<NhanvienModel> selectAll() {
        List<NhanvienModel> list = new ArrayList<>();
        String sql = "SELECT * FROM nhanvien";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Date d = rs.getDate("ngaysinh");
                LocalDate ngaySinh = (d != null) ? d.toLocalDate() : null;
                NhanvienModel nv = new NhanvienModel(
                        rs.getString("manv"),
                        rs.getString("tennv"),
                        rs.getString("gioitinh"),
                       // rs.getDate("ngaysinh").toLocalDate(),
                        ngaySinh,
                        rs.getString("diachi"),
                        rs.getString("chucvu"),
                        rs.getDouble("luong"),
                        rs.getString("tendangnhap"),
                        rs.getString("matkhau"),
                        rs.getInt("trang_thai")
                );
                list.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<NhanvienModel> selectAlll() {
        List<NhanvienModel> list = new ArrayList<>();
        String sql = "SELECT * FROM nhanvien WHERE trang_thai = 1";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                NhanvienModel nv = new NhanvienModel(
                        rs.getString("manv"),
                        rs.getString("tennv"),
                        rs.getString("gioitinh"),
                        rs.getDate("ngaysinh").toLocalDate(),
                        rs.getString("diachi"),
                        rs.getString("chucvu"),
                        rs.getDouble("luong"),
                        rs.getString("tendangnhap"),
                        rs.getString("matkhau"),
                        rs.getInt("trang_thai")
                );
                list.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
public List<NhanvienModel> search(String tenNV, String chucVu, String gioiTinh, Integer trangThai, String diaChi, Double luong){
    List<NhanvienModel> list = new ArrayList<>();
    StringBuilder sql = new StringBuilder("""
        SELECT * FROM nhanvien WHERE trang_thai != -1
    """);
    List<Object> params = new ArrayList<>();
    if(tenNV != null && !tenNV.isEmpty()){
        sql.append(" AND tennv LIKE ?");
        params.add("%" + tenNV + "%");
    }
    if(chucVu != null && !chucVu.equals("Tất cả")){
        sql.append(" AND chucvu = ?");
        params.add(chucVu);
    }
    if (gioiTinh != null && !gioiTinh.isEmpty()){
        sql.append(" AND gioitinh = ?");
        params.add(gioiTinh);
    }
    if (trangThai != null && trangThai != -1){
        sql.append(" AND trang_thai = ?");
        params.add(trangThai);
    }
    if (diaChi != null && !diaChi.isEmpty()){
        sql.append(" AND diachi LIKE ?");
        params.add("%" + diaChi + "%");
    }
    if (luong != null){
        sql.append(" AND luong = ?");
        params.add(luong);
    }
    try (Connection con = ConnectDB.getConnection();
         PreparedStatement pst = con.prepareStatement(sql.toString())){
        for(int i = 0; i < params.size(); i++){
            pst.setObject(i + 1, params.get(i));
        }
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            list.add(mapNhanVien(rs));
        }
    } catch (Exception e){
        e.printStackTrace();
    }
    return list;
}
}



