package Bus;

import Dao.KhachHangDao;
import Dto.KhachHangDTO;
import java.util.List;

public class KhachHangBus {
    KhachHangDao dao = new KhachHangDao();
    public List<KhachHangDTO> layTatCa(){
        return dao.layTatCa();
    }
    public List<KhachHangDTO> timKiem(String tuKhoa){
        return dao.timKiem(tuKhoa);
    }
    public boolean them(KhachHangDTO k){
        return dao.them(k);
    }
    public boolean capNhat(KhachHangDTO k){
        return dao.capNhat(k);
    }
    public boolean xoa(int MaKH){
        return dao.xoa(MaKH);
    }
}
