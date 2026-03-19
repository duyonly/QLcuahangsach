package Bus;

import Dao.NhaCungCapDao;
import Dto.NhaCungCapDTO;
import java.util.List;

public class NhaCungCapBus {
    NhaCungCapDao dao = new NhaCungCapDao();
    public List<NhaCungCapDTO> hienDanhSach(){
        return dao.hienDanhSach();
    }
    public List<NhaCungCapDTO> timKiem(String tuKhoa){
        return dao.timKiem(tuKhoa);
    }
    public boolean them(NhaCungCapDTO n){
        return dao.them(n);
    }
    public boolean sua(NhaCungCapDTO n){
        return dao.sua(n);
    }
    public boolean xoa(int MaNCC){
        return dao.xoa(MaNCC);
    }
    public NhaCungCapDTO xemChiTiet(String MaNCC){
        return dao.xemChiTiet(MaNCC);
    }
}
