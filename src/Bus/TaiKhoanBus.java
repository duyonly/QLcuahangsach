package Bus;
import Dao.XuLyDangNhap;
import Dto.TaiKhoanDTO;
public class TaiKhoanBus {
    XuLyDangNhap Dao= new XuLyDangNhap();
    public boolean dangNhap(TaiKhoanDTO tk){
        return Dao.dangNhap(tk);
    }
    public boolean QuenMK(TaiKhoanDTO tk){
        if(Dao.kiemTraEmail(tk)){
            return Dao.capNhatMatKhau(tk);
        }
        return false;
    }
    
}
