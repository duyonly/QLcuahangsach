package Bus;
import Dao.chitiethoadondao;
import Dto.chitiethoadon;
import java.util.ArrayList;
public class ChiTietHoaDonBUS {


    private chitiethoadondao dao = new chitiethoadondao();

    public ArrayList<chitiethoadon> getByMaHD(String maHD) {
        return dao.CTHD(maHD);
    }

    public boolean add(chitiethoadon ct) {
        return dao.insert(ct);
    }

    public boolean update(chitiethoadon ct) {
        return dao.update(ct);
    }

    public boolean delete(String maHD, String maSach) {
        return dao.delete(maHD, maSach);
    }
    public ArrayList<chitiethoadon> search(String keyword) {
        return dao.search(keyword);
    }
}