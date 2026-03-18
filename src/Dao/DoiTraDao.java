package Dao;
import Dto.DoiTraDto;
import java.sql.*;
import java.util.ArrayList;
public class DoiTraDao {
    public boolean  them(DoiTraDto dt){
 String sql="INSERT INTO doitra(MaHD,MaSachCu,MaSachMoi,SoLuong,Loai,LyDo,TienHoan,TienThem,NgayTao,MaNV) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try(Connection con=ConnectDB.getConnection();
            PreparedStatement ps =con.prepareStatement(sql)){
                ps.setString(1, dt.getMaHD());
                ps.setString(2, dt.getMaSachCu());
                ps.setString(3, dt.getMaSachMoi());
                ps.setInt(4, dt.getSoLuong());
                ps.setString(5, dt.getLoai());
                ps.setString(6, dt.getLyDo());
                ps.setDouble(7, dt.getTienHoan());
                ps.setDouble(8, dt.getTienThem());
                ps.setDate(9, new  java.sql.Date(dt.getNgayYeuCau().getTime()));
                ps.setString(10, dt.getMaNV());
                return ps.executeUpdate()>0;

            }
            catch(Exception e){
                e.printStackTrace();
            }
            return false;
        }

        public ArrayList<DoiTraDto> getAll(){
            ArrayList<DoiTraDto> list=new ArrayList<>();
            String sql="SELECT * FROM doitra";
            try 
            (
                Connection con=ConnectDB.getConnection();
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery(sql);
                
            ){ while(rs.next()){
                DoiTraDto dt=new DoiTraDto();
                dt.setMaDoiTra(rs.getInt("MaDoiTra"));
                dt.setMaHD(rs.getString("MaHD"));
                dt.setMaSachCu(rs.getString("MaSachCu"));
                dt.setMaSachMoi(rs.getString("MaSachMoi"));
                dt.setSoLuong(rs.getInt("SoLuong"));
                dt.setLoai(rs.getString("Loai"));
                dt.setLyDo(rs.getString("LyDo"));
                dt.setTienHoan(rs.getDouble("TienHoan"));
            dt.setTienThem(rs.getDouble("TienThem"));
            dt.setNgayYeuCau(rs.getDate("NgayTao"));
            dt.setMaNV(rs.getString("MaNV"));
                
                list.add(dt);
            }
                
            } catch (Exception e) {
                e.printStackTrace();
        }
        return list;
    }
    
}

