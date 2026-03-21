package Bus;

import Dao.KhachHangDao;
import Dto.KhachHangDTO;
import java.util.List;

public class KhachHangBus {
    KhachHangDao dao = new KhachHangDao();
    public List<KhachHangDTO> hienDanhSach(){
        return dao.hienDanhSach();
    }
    public List<KhachHangDTO> timKiem(String tuKhoa){
        return dao.timKiem(tuKhoa);
    }
    public boolean them(KhachHangDTO k){
        return dao.them(k);
    }
    public boolean sua(KhachHangDTO k){
        return dao.sua(k);
    }
    public boolean xoa(String MaKH){
        return dao.xoa(MaKH);
    }
    public KhachHangDTO xemChiTiet(String MaKH){
        return dao.xemChiTiet(MaKH);
    }
}
