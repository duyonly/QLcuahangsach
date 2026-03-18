package Bus;

import Dao.PhieuXuatDAO;
import Dto.PhieuXuatDTO;
import java.util.ArrayList;

public class PhieuXuatBUS {

    PhieuXuatDAO dao = new PhieuXuatDAO();

    public ArrayList<PhieuXuatDTO> getAll(){
        return dao.getAll();
    }

    public boolean add(PhieuXuatDTO px){
        return dao.insert(px);
    }

    public boolean update(PhieuXuatDTO px){
        return dao.update(px);
    }

    public boolean delete(String ma){
        return dao.delete(ma);
    }
}