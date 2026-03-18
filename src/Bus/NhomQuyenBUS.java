package Bus;
import Dao.NhomQuyenDAO;
import Dao.ChiTietQuyenDAO;
import Dto.NhomQuyenDTO;
import java.util.ArrayList;
public class NhomQuyenBUS {
    private NhomQuyenDAO nhomQuyenDAO = new NhomQuyenDAO();
    private ArrayList<NhomQuyenDTO> listNhomQuyen = new ArrayList<>();

    public NhomQuyenBUS() {
        docDanhSach(); 
    }

    public void docDanhSach() {
        listNhomQuyen = nhomQuyenDAO.selectAll();
    }

    public ArrayList<NhomQuyenDTO> getList() {
        return listNhomQuyen;
    }

    public String themNhomQuyen(NhomQuyenDTO nq) {
        if (nq.getMaNhomQuyen().trim().isEmpty() || nq.getTenNhomQuyen().trim().isEmpty()) {
            return "Lỗi: Mã và tên nhóm quyền không được để trống!";
        }
        for (NhomQuyenDTO item : listNhomQuyen) {
            if (item.getMaNhomQuyen().equals(nq.getMaNhomQuyen())) {
                return "Lỗi: Mã nhóm quyền này đã tồn tại!";
            }
        }
        int result = nhomQuyenDAO.insert(nq);
        if (result > 0) {
            listNhomQuyen.add(nq);
            return "Thành công";
        }
        return "Lỗi: Thêm vào Database thất bại!";
    }

    public String suaNhomQuyen(NhomQuyenDTO nq) {
        if (nq.getTenNhomQuyen().trim().isEmpty()) {
            return "Lỗi: Tên nhóm quyền không được để trống!";
        }
        int result = nhomQuyenDAO.update(nq);
        if (result > 0) {
            docDanhSach();
            return "Thành công";
        }
        return "Lỗi: Cập nhật thất bại!";
    }

    public String xoaNhomQuyen(String maNhom) {
        try {
             new ChiTietQuyenDAO().deleteByMaNhom(maNhom);
            
             int result = nhomQuyenDAO.delete(maNhom);
            if (result > 0) {
                docDanhSach(); 
                return "Thành công";
            }
            return "Lỗi: Xóa thất bại!";
        } catch (Exception e) {
             return "Lỗi: Không thể xóa nhóm quyền đang có tài khoản sử dụng!";
        }
    }

}
