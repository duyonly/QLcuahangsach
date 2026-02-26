
package Nhanvien;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class NhanvienBus{
    private NhanvienDao dao;
    private List<NhanvienModel> listNV;

    public NhanvienBus(){
        dao = new NhanvienDao();
        listNV = dao.selectAll();
    }
    public List<NhanvienModel> search(String tenNV, String chucVu, String gioiTinh, Integer trangThai, String diaChi, Double luong){
        return dao.search(tenNV, chucVu, gioiTinh, trangThai, diaChi,luong);
    }
    //Lay danh sach
    public List<NhanvienModel> getAll(){
        return listNV;
    }
    //TẢI LẠI DỮ LIỆU
    public void refresh(){
        listNV = dao.selectAll();
    }
    //Them nv
    public boolean add(NhanvienModel nv){
        // kiểm tra nanv
        if (dao.selectById(nv.getMaNV()) != null){
            return false;
        }
        nv.setTrang_thai(1);
        if (dao.insert(nv)){
            listNV.add(nv);
            return true;
        }
        return false;
    }
    //Cap nhat thongtin
    public boolean update(NhanvienModel nv){
        if (dao.update(nv)){
            int index = getIndexById(nv.getMaNV());
            if (index != -1) {
                listNV.set(index, nv);
            }
            return true;
        }
        return false;
    }

    public boolean updateTrangThaiNghiViec(String maNV){
        if(dao.updateTrangThaiNghiViec(maNV)){
            int index = getIndexById(maNV);
            if(index != -1){
                listNV.get(index).setTrang_thai(0);
            }
            return true;
        }
        return false;
    }

    //Xoa(soft delete)
//    public boolean delete(String maNV){
//        if (dao.delete(maNV)){
//
//            for (NhanvienModel nv : listNV){
//                if (nv.getMaNV().equals(maNV)) {
//                    nv.setTrang_thai(-1);
//                    break;
//                }
//            }
//            return true;
//        }
//        return false;
//    }
    public boolean deleteNhanVien(String maNV){
        NhanvienModel nv = dao.selectById(maNV);
        if (nv == null) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên");
            return false;
        }
        if (nv.getTrang_thai() == 1){
            JOptionPane.showMessageDialog(null, "Chỉ được xóa nhân viên đã nghỉ việc!");
            return false;
        }
        if (dao.delete(maNV)){
            int index = getIndexById(maNV);
            if (index != -1) {
                listNV.get(index).setTrang_thai(-1);
            }
            return true;
        }
        return dao.delete(maNV);
    }
    //Tim kiem manv
    public NhanvienModel getById(String maNV){
        return dao.selectById(maNV);
    }
    //index
    public int getIndexById(String maNV){
        for (int i = 0; i < listNV.size(); i++) {
            if (listNV.get(i).getMaNV().equals(maNV)){
                return i;
            }
        }
        return -1;
    }
    //Kiem tra dang nhap(taikhoan va matkhau co dung hay khong)
    public NhanvienModel login(String username, String password){
        if(username == null || password == null){
            return null;
        }
        username = username.trim();
        password = password.trim();
        for (NhanvienModel nv : listNV){
            if (nv.getTenDangNhap().equals(username)
                    && nv.getMatKhau().equals(password)
                    && nv.getTrang_thai() == 1){
                return nv;
            }
        }
        return null;
    }
}