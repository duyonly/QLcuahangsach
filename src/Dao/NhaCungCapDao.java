package Dao;

import Dto.NhaCungCapDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhaCungCapDao {
    public List<NhaCungCapDTO> layTatCa(){
        List<NhaCungCapDTO> list=new ArrayList<>();
        String sql="SELECT MaNCC,TenNCC,DiaChi,SDT,Email FROM nhacungcap";
        try(Connection con=ConnectDB.getConnection(); PreparedStatement stmt=con.prepareStatement(sql); ResultSet rs=stmt.executeQuery()){
            while(rs.next()){
                NhaCungCapDTO n=new NhaCungCapDTO();
                n.setMaNCC(rs.getInt("MaNCC"));
                n.setTenNCC(rs.getString("TenNCC"));
                n.setDiaChi(rs.getString("DiaChi"));
                n.setSDT(rs.getString("SDT"));
                n.setEmail(rs.getString("Email"));
                list.add(n);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<NhaCungCapDTO> timKiem(String tuKhoa){
        List<NhaCungCapDTO> list=new ArrayList<>();
        String sql="SELECT MaNCC,TenNCC,DiaChi,SDT,Email FROM nhacungcap WHERE TenNCC LIKE ? OR SDT LIKE ? OR Email LIKE ?";
        try(Connection con=ConnectDB.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
            String q = "%" + tuKhoa + "%";
            stmt.setString(1,q);
            stmt.setString(2,q);
            stmt.setString(3,q);
            try(ResultSet rs=stmt.executeQuery()){
                while(rs.next()){
                    NhaCungCapDTO n=new NhaCungCapDTO();
                    n.setMaNCC(rs.getInt("MaNCC"));
                    n.setTenNCC(rs.getString("TenNCC"));
                    n.setDiaChi(rs.getString("DiaChi"));
                    n.setSDT(rs.getString("SDT"));
                    n.setEmail(rs.getString("Email"));
                    list.add(n);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public boolean them(NhaCungCapDTO n){
        String sql="INSERT INTO nhacungcap(TenNCC,DiaChi,SDT,Email) VALUES(?,?,?,?)";
        try(Connection con=ConnectDB.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
            stmt.setString(1, n.getTenNCC());
            stmt.setString(2, n.getDiaChi());
            stmt.setString(3, n.getSDT());
            stmt.setString(4, n.getEmail());
            return stmt.executeUpdate()>0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean capNhat(NhaCungCapDTO n){
        String sql="UPDATE nhacungcap SET TenNCC=?,DiaChi=?,SDT=?,Email=? WHERE MaNCC=?";
        try(Connection con=ConnectDB.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
            stmt.setString(1, n.getTenNCC());
            stmt.setString(2, n.getDiaChi());
            stmt.setString(3, n.getSDT());
            stmt.setString(4, n.getEmail());
            stmt.setInt(5, n.getMaNCC());
            return stmt.executeUpdate()>0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoa(int MaNCC){
        String sql="DELETE FROM nhacungcap WHERE MaNCC=?";
        try(Connection con=ConnectDB.getConnection(); PreparedStatement stmt=con.prepareStatement(sql)){
            stmt.setInt(1, MaNCC);
            return stmt.executeUpdate()>0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
