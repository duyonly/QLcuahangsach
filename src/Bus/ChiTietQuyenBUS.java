package Bus;
import Dao.ChiTietQuyenDAO;
import Dto.ChiTietQuyenDTO;
import java.util.ArrayList;

public class ChiTietQuyenBUS {
    private ChiTietQuyenDAO dao = new ChiTietQuyenDAO();

    public ArrayList<ChiTietQuyenDTO> getQuyenByMaNhom(String maNhom) {
        return dao.selectByMaNhomQuyen(maNhom);
    }

    public boolean luuDanhSachQuyen(String maNhom, ArrayList<ChiTietQuyenDTO> listQuyen) {
        dao.deleteByMaNhom(maNhom); 
        int count = 0;
        for (ChiTietQuyenDTO ct : listQuyen) {
            count += dao.insert(ct);
        }
        return count == listQuyen.size();
    }
}