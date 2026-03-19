package BUS;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import java.util.ArrayList;

public class SanPhamBUS {

    private SanPhamDAO dao = new SanPhamDAO();
    private ArrayList<SanPhamDTO> list;

    // Lấy toàn bộ sản phẩm
    public ArrayList<SanPhamDTO> getAll(){
        list = dao.getAll();
        return list;
    }

    // Thêm sản phẩm
    public boolean add(SanPhamDTO sp){
        return dao.insert(sp);
    }

    // Xóa sản phẩm
    public boolean delete(String masp){
        return dao.delete(masp);
    }

    // Sửa sản phẩm
    public boolean update(SanPhamDTO sp){
        return dao.update(sp);
    }

    // Tìm sản phẩm theo mã
    public SanPhamDTO findByID(String masp){
        return dao.findByID(masp);
    }

    // Tìm sản phẩm theo tên
    public ArrayList<SanPhamDTO> searchByName(String keyword){
        return dao.searchByName(keyword);
    }
}