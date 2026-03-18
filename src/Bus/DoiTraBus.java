package Bus;

import java.util.ArrayList;

import Dao.DoiTraDao;
import Dao.chitiethoadondao;
import Dto.DoiTraDto;
import Dto.chitiethoadon;

public class DoiTraBus {
    DoiTraDao dao=new DoiTraDao();
        public boolean taoYeuCau(DoiTraDto dt){
            if(dt.getMaHD().isEmpty()) return false;
            if(dt.getMaSachCu().isEmpty()) return false;
            if(dt.getLoai().equals("Đổi") && dt.getMaSachMoi().isEmpty()) return false;
            if(dt.getLoai().isEmpty()) return false;
            if(dt.getSoLuong()<=0) return false;
            if(dt.getLyDo().isEmpty()) return false;
            return dao.them(dt);
        }
        public ArrayList<DoiTraDto> DanhSach(){
            return dao.getAll();
        }
        chitiethoadondao ct=new chitiethoadondao();
        public ArrayList<chitiethoadon> CThoadon(String maHD){
            return ct.CTHD(maHD);
        }
}
