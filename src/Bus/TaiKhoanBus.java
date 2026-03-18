package Bus;
import java.util.ArrayList;

import Dao.XuLyDangNhap;
import Dto.TaiKhoanDTO;
import Dao.TaiKhoanDao;
public class TaiKhoanBus {
    XuLyDangNhap DaoLogin= new XuLyDangNhap();
    public TaiKhoanDTO dangNhap(TaiKhoanDTO tk){
        return DaoLogin.dangNhap(tk);
    }
    public boolean QuenMK(TaiKhoanDTO tk){
        if(DaoLogin.kiemTraEmail(tk)){
            return DaoLogin.capNhatMatKhau(tk);
        }
        return false;
    }
    TaiKhoanDao dao = new TaiKhoanDao();

    public ArrayList<TaiKhoanDTO> getList() {
        return dao.getAll();
    }

    public boolean add(TaiKhoanDTO tk) {
        if (tk.getTenDangNhap().isEmpty() || tk.getMatKhau().isEmpty()) {
            return false;
        }
        return dao.insert(tk);
    }

    public boolean update(TaiKhoanDTO tk) {
        return dao.update(tk);
    }

    public boolean delete(String maTK) {
        return dao.delete(maTK);
    }
public ArrayList<TaiKhoanDTO> search( String value) {
    return dao.search(value);
}
    
}
