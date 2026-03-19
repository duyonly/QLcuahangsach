package Bus;

import Dao.ChiTietPhieuXuatDAO;
import Dto.ChiTietPhieuXuatDTO;
import java.util.ArrayList;

public class ChiTietPhieuXuatBUS {

    ChiTietPhieuXuatDAO dao = new ChiTietPhieuXuatDAO();

    public ArrayList<ChiTietPhieuXuatDTO> getAll(){
        return dao.getAll();
    }

    public boolean add(ChiTietPhieuXuatDTO ct){
        return dao.insert(ct);
    }

    public boolean delete(String maphieuxuat, String masanpham){
        return dao.delete(maphieuxuat, masanpham);
    }
    public ArrayList<ChiTietPhieuXuatDTO> getByMaPhieuXuat(String maPX){
        return dao.getByMaPhieuXuat(maPX);
    }
}