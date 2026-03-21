package Dao;

import Dto.NhaCungCapDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhaCungCapDao {
    // Chức năng hiện danh sách
    public List<NhaCungCapDTO> hienDanhSach(){
        List<NhaCungCapDTO> list=new ArrayList<>();
        String sql="SELECT MaNCC,TenNCC,DienThoai,Email,DiaChi FROM nhacungcap";
        try(Connection con=ConnectDB.getConnection(); PreparedStatement stmt=con.prepareStatement(sql); ResultSet rs=stmt.executeQuery()){
            while(rs.next()){
                NhaCungCapDTO n=new NhaCungCapDTO();
                n.setMaNCC(rs.getString("MaNCC"));
                n.setTenNCC(rs.getString("TenNCC"));
                n.setDienThoai(rs.getString("DienThoai"));
                n.setEmail(rs.getString("Email"));
                n.setDiaChi(rs.getString("DiaChi"));
                list.add(n);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<NhaCungCapDTO> timKiem(String tuKhoa){
        List<NhaCungCapDTO> list=new ArrayList<>();
        String sql="SELECT MaNCC,TenNCC,DienThoai,Email,DiaChi FROM nhacungcap WHERE TenNCC LIKE ? OR SDT LIKE ? OR Email LIKE ?";
        try(Connection con=ConnectDB.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
            String q = "%" + tuKhoa + "%";
            stmt.setString(1,q);
            stmt.setString(2,q);
            stmt.setString(3,q);
            try(ResultSet rs=stmt.executeQuery()){
                while(rs.next()){
                    NhaCungCapDTO n=new NhaCungCapDTO();
                    n.setMaNCC(rs.getString("MaNCC"));
                    n.setTenNCC(rs.getString("TenNCC"));
                    n.setDienThoai(rs.getString("DienThoai"));
                    n.setEmail(rs.getString("Email"));
                    n.setDiaChi(rs.getString("DiaChi"));
                    list.add(n);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public boolean them(NhaCungCapDTO n){
        String sql="INSERT INTO nhacungcap(TenNCC,DienThoai,Email,DiaChi) VALUES(?,?,?,?)";
        try(Connection con=ConnectDB.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
            stmt.setString(1, n.getTenNCC());
            stmt.setString(2, n.getDienThoai());
            stmt.setString(3, n.getEmail());
            stmt.setString(4, n.getDiaChi());
            return stmt.executeUpdate()>0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean sua(NhaCungCapDTO n){
        String sql="UPDATE nhacungcap SET TenNCC=?,DienThoai=?,Email=?,DiaChi=? WHERE MaNCC=?";
        try(Connection con=ConnectDB.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
            stmt.setString(1, n.getTenNCC());
            stmt.setString(2, n.getDienThoai());
            stmt.setString(3, n.getEmail());
            stmt.setString(4, n.getDiaChi());
            stmt.setString(5, n.getMaNCC());
            return stmt.executeUpdate()>0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoa(String MaNCC){
        String sql="DELETE FROM nhacungcap WHERE MaNCC=?";
        try(Connection con=ConnectDB.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
            stmt.setString(1, MaNCC);
            return stmt.executeUpdate()>0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public NhaCungCapDTO xemChiTiet(String MaNCC){
        String sql = "SELECT MaNCC,TenNCC,DienThoai,Email,DiaChi FROM nhacungcap WHERE MaNCC=?";
        try(Connection con = ConnectDB.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, MaNCC);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    NhaCungCapDTO n = new NhaCungCapDTO();
                    n.setMaNCC(rs.getString("MaNCC"));
                    n.setTenNCC(rs.getString("TenNCC"));
                    n.setDienThoai(rs.getString("DienThoai"));
                    n.setEmail(rs.getString("Email"));
                    n.setDiaChi(rs.getString("DiaChi"));
                    return n;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
