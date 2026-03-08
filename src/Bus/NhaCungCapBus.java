package Bus;

import Dao.NhaCungCapDao;
import Dto.NhaCungCapDTO;
import java.util.List;

public class NhaCungCapBus {
    NhaCungCapDao dao = new NhaCungCapDao();
    public List<NhaCungCapDTO> layTatCa(){
        return dao.layTatCa();
    }
    public List<NhaCungCapDTO> timKiem(String tuKhoa){
        return dao.timKiem(tuKhoa);
    }
    public boolean them(NhaCungCapDTO n){
        return dao.them(n);
    }
    public boolean capNhat(NhaCungCapDTO n){
        return dao.capNhat(n);
    }
    public boolean xoa(int MaNCC){
        return dao.xoa(MaNCC);
    }
}
