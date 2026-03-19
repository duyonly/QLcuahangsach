package Bus;

import Dao.PhieuNhapDAO;
import Dao.CtPhieuNhapDAO;
import Dto.PhieuNhapDTO;
import Dto.CtPhieuNhapDTO;

// 1. IMPORT THÊM LỚP BUS CỦA SẢN PHẨM
import Bus.SanPhamBUS; 

import java.sql.SQLException;
import java.util.ArrayList;

public class PhieuNhapBUS {
    private PhieuNhapDAO phieuNhapDAO = new PhieuNhapDAO();
    private CtPhieuNhapDAO ctPhieuNhapDAO = new CtPhieuNhapDAO();

    public String getMaPhieuMoi() {
        try {
            String maCuoi = phieuNhapDAO.getLastMaPhieuNhap();
            if (maCuoi == null || maCuoi.isEmpty()) {
                return "PN001";
            }
            int so = Integer.parseInt(maCuoi.substring(2));
            so++; 
            
            return String.format("PN%03d", so); 
        } catch (SQLException e) {
            return "ERROR"; 
        }
    }

    public String taoPhieuNhap(PhieuNhapDTO phieuNhap, ArrayList<CtPhieuNhapDTO> chiTietList) {
        if (chiTietList == null || chiTietList.isEmpty()) {
            return "Lỗi: Phiếu nhập phải có ít nhất 1 sản phẩm!";
        }

        try {
            // 1. Lưu thông tin Phiếu Nhập
            int ketQuaPhieu = phieuNhapDAO.insert(phieuNhap);
            
            if (ketQuaPhieu > 0) {
                // 2. Lưu thông tin Chi Tiết Phiếu Nhập
                int ketQuaChiTiet = ctPhieuNhapDAO.insertAll(chiTietList);
                
                if (ketQuaChiTiet == chiTietList.size()) {
                    
                    //cộng dồn số lượng sản phẩm vào kho
                    SanPhamBUS spBUS = new SanPhamBUS();
                    for (CtPhieuNhapDTO ct : chiTietList) {
                        // Gọi hàm cập nhật số lượng
                        spBUS.capNhatSoLuong(ct.getMaSanPham(), ct.getSoLuong());
                    }
                    
                    return "Thành công";
                } else {
                    return "Lỗi: Quá trình lưu chi tiết phiếu nhập gặp sự cố!";
                }
            }
            return "Lỗi: Không thể tạo phiếu nhập mới!";
            
        } catch (SQLException e) {
            return "Lỗi cơ sở dữ liệu: " + e.getMessage();
        }
    }
}