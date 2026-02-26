package Thongke;

import Thongke.Model.*;
import java.util.List;
public class ThongkeBus {
    private ThongkeDao dao = new ThongkeDao();
    public double getTongDoanhThu() throws Exception{
        return dao.getTongDoanhThu();
    }
    public double getTongNhap() throws Exception{
        return dao.getTongNhap();
    }
    public double getLoiNhuan() throws Exception{
        return getTongDoanhThu() - getTongNhap();
    }
    public List<TopsachModel> getTopSach() throws Exception{
        return dao.getTopSach();
    }
    public List<DoanhthuthangModel> getDoanhThuTheoThang() throws Exception{
        return dao.getDoanhThuTheoThang();
    }
    public List<ThongkenhanvienModel> getThongKeNhanVien() throws Exception{
        return dao.getThongKeNhanVien();
    }
}