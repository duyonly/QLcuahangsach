package Dao;

import Dto.KhachHangDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDao {
    public List<KhachHangDTO> layTatCa(){
        List<KhachHangDTO> list=new ArrayList<>();
        String sql="SELECT MaKH,TenKH,DiaChi,SDT,Email FROM khachhang";
        try(Connection con=ConnectDB.getConnection(); PreparedStatement stmt=con.prepareStatement(sql); ResultSet rs=stmt.executeQuery()){
            while(rs.next()){
                KhachHangDTO k=new KhachHangDTO();
                k.setMaKH(rs.getInt("MaKH"));
                k.setTenKH(rs.getString("TenKH"));
                k.setDiaChi(rs.getString("DiaChi"));
                k.setSDT(rs.getString("SDT"));
                k.setEmail(rs.getString("Email"));
                list.add(k);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<KhachHangDTO> timKiem(String tuKhoa){
        List<KhachHangDTO> list=new ArrayList<>();
        String sql="SELECT MaKH,TenKH,DiaChi,SDT,Email FROM khachhang WHERE TenKH LIKE ? OR SDT LIKE ? OR Email LIKE ?";
        try(Connection con=ConnectDB.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
            String q = "%" + tuKhoa + "%";
            stmt.setString(1,q);
            stmt.setString(2,q);
            stmt.setString(3,q);
            try(ResultSet rs=stmt.executeQuery()){
                while(rs.next()){
                    KhachHangDTO k=new KhachHangDTO();
                    k.setMaKH(rs.getInt("MaKH"));
                    k.setTenKH(rs.getString("TenKH"));
                    k.setDiaChi(rs.getString("DiaChi"));
                    k.setSDT(rs.getString("SDT"));
                    k.setEmail(rs.getString("Email"));
                    list.add(k);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public boolean them(KhachHangDTO k){
        String sql="INSERT INTO khachhang(TenKH,DiaChi,SDT,Email) VALUES(?,?,?,?)";
        try(Connection con=ConnectDB.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
            stmt.setString(1, k.getTenKH());
            stmt.setString(2, k.getDiaChi());
            stmt.setString(3, k.getSDT());
            stmt.setString(4, k.getEmail());
            return stmt.executeUpdate()>0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean capNhat(KhachHangDTO k){
        String sql="UPDATE khachhang SET TenKH=?,DiaChi=?,SDT=?,Email=? WHERE MaKH=?";
        try(Connection con=ConnectDB.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
            stmt.setString(1, k.getTenKH());
            stmt.setString(2, k.getDiaChi());
            stmt.setString(3, k.getSDT());
            stmt.setString(4, k.getEmail());
            stmt.setInt(5, k.getMaKH());
            return stmt.executeUpdate()>0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoa(int MaKH){
        String sql="DELETE FROM khachhang WHERE MaKH=?";
        try(Connection con=ConnectDB.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
            stmt.setInt(1, MaKH);
            return stmt.executeUpdate()>0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
