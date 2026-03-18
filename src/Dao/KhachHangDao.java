package Dao;

import Dto.KhachHangDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDao {
    public List<KhachHangDTO> hienDanhSach(){
        List<KhachHangDTO> list=new ArrayList<>();
        String sql="SELECT MaKH,TenKH,GioiTinh,SDT,Email,DiaChi,LoaiKhachHang,DiemTichLuy FROM khachhang";
        try(Connection con=ConnectDB.getConnection(); PreparedStatement stmt=con.prepareStatement(sql); ResultSet rs=stmt.executeQuery()){
            while(rs.next()){
                KhachHangDTO k=new KhachHangDTO();
                k.setMaKH(rs.getString("MaKH"));
                k.setTenKH(rs.getString("TenKH"));
                k.setGioiTinh(rs.getString("GioiTinh"));
                k.setSDT(rs.getString("SDT"));
                k.setEmail(rs.getString("Email"));
                k.setDiaChi(rs.getString("DiaChi"));
                k.setLoaiKhachHang(rs.getString("LoaiKhachHang"));
                k.setDiemTichLuy(rs.getInt("DiemTichLuy"));
                list.add(k);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<KhachHangDTO> timKiem(String tuKhoa){
        List<KhachHangDTO> list=new ArrayList<>();
        String sql="SELECT MaKH,TenKH,GioiTinh,SDT,Email,DiaChi,LoaiKhachHang,DiemTichLuy FROM khachhang WHERE TenKH LIKE ? OR SDT LIKE ? OR Email LIKE ?";
        try(Connection con=ConnectDB.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
            String q = "%" + tuKhoa + "%";
            stmt.setString(1,q);
            stmt.setString(2,q);
            stmt.setString(3,q);
            try(ResultSet rs=stmt.executeQuery()){
                while(rs.next()){
                    KhachHangDTO k=new KhachHangDTO();
                    k.setMaKH(rs.getString("MaKH"));
                    k.setTenKH(rs.getString("TenKH"));
                    k.setGioiTinh(rs.getString("GioiTinh"));
                    k.setSDT(rs.getString("SDT"));
                    k.setEmail(rs.getString("Email"));
                    k.setDiaChi(rs.getString("DiaChi"));
                    k.setLoaiKhachHang(rs.getString("LoaiKhachHang"));
                    k.setDiemTichLuy(rs.getInt("DiemTichLuy"));
                    list.add(k);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public boolean them(KhachHangDTO k){
        String sql="INSERT INTO khachhang(TenKH,GioiTinh,SDT,Email,DiaChi,LoaiKhachHang,DiemTichLuy) VALUES(?,?,?,?,?,?,?)";
        try(Connection con=ConnectDB.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
            stmt.setString(1, k.getTenKH());
            stmt.setString(2, k.getGioiTinh());
            stmt.setString(3, k.getSDT());
            stmt.setString(4, k.getEmail());
            stmt.setString(5, k.getDiaChi());
            stmt.setString(6, k.getLoaiKhachHang());
            stmt.setInt(7, k.getDiemTichLuy());
            return stmt.executeUpdate()>0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean sua(KhachHangDTO k){
        String sql="UPDATE khachhang SET TenKH=?,GioiTinh=?,SDT=?,Email=?,DiaChi=?,LoaiKhachHang=?,DiemTichLuy=? WHERE MaKH=?";
        try(Connection con=ConnectDB.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
            stmt.setString(1, k.getTenKH());
            stmt.setString(2, k.getGioiTinh());
            stmt.setString(3, k.getSDT());
            stmt.setString(4, k.getEmail());
            stmt.setString(5, k.getDiaChi());
            stmt.setString(6, k.getLoaiKhachHang());
            stmt.setInt(7, k.getDiemTichLuy());
            stmt.setString(8, k.getMaKH());
            return stmt.executeUpdate()>0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoa(String MaKH){
        String sql="DELETE FROM khachhang WHERE MaKH=?";
        try(Connection con=ConnectDB.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
            stmt.setString(1, MaKH);
            return stmt.executeUpdate()>0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public KhachHangDTO xemChiTiet(int MaKH){
        String sql = "SELECT MaKH,TenKH,GioiTinh,SDT,Email,DiaChi,LoaiKhachHang,DiemTichLuy FROM khachhang WHERE MaKH=?";
        try(Connection con = ConnectDB.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, MaKH);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    KhachHangDTO k = new KhachHangDTO();
                    k.setMaKH(rs.getString("MaKH"));
                    k.setTenKH(rs.getString("TenKH"));
                    k.setGioiTinh(rs.getString("GioiTinh"));
                    k.setSDT(rs.getString("SDT"));
                    k.setEmail(rs.getString("Email"));
                    k.setDiaChi(rs.getString("DiaChi"));
                    k.setLoaiKhachHang(rs.getString("LoaiKhachHang"));
                    k.setDiemTichLuy(rs.getInt("DiemTichLuy"));
                    return k;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
