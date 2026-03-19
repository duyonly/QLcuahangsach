package Bus;

import Dao.HoaDonDAO;
import Dto.HoaDonDto;
import java.util.ArrayList;
public class HoaDonBUS {
   
        private HoaDonDAO dao = new HoaDonDAO();
    
        public ArrayList<HoaDonDto> getAll() {
            return dao.getAll();
        }
    
        public boolean add(HoaDonDto hd) {
            return dao.insert(hd);
        }
    
        public boolean update(HoaDonDto hd) {
            return dao.update(hd);
        }
    
        public boolean delete(String maHD) {
            return dao.delete(maHD);
        }
        public ArrayList<HoaDonDto> search(String keyword) {
            return dao.search(keyword);
        }
}
