package Nhanvien;
import java.util.List;
public interface NhanvienDaointerface {
    boolean insert(NhanvienModel nv);
    boolean update(NhanvienModel nv);
    boolean delete(String maNV);
    NhanvienModel selectById(String maNV);
    List<NhanvienModel> selectAll();
    // in nhanvien co trangthai = 1
    List<NhanvienModel> selectAlll();
    boolean updateTrangThaiNghiViec(String maNV);
}
