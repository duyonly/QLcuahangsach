package Bus;
import Dao.*;
import Dto.*;
import java.util.ArrayList;

public class DanhMucChucNangBUS {
    private DanhMucChucNangDAO dao = new DanhMucChucNangDAO();
    public ArrayList<DanhMucChucNangDTO> getAll() {
        return dao.selectAll();
    }
}