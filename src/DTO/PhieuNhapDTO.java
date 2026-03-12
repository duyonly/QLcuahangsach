package DTO;
import java.sql.Date;

public class PhieuNhapDTO {
    private String maPhieuNhap;
    private Date thoiGian;
    private String trangThai;
    private String maNhaCungCap;
    private String nguoitaoPhieuNhap; // FK trỏ về maNV

    public PhieuNhapDTO() {
    }

    public PhieuNhapDTO(String maPhieuNhap, Date thoiGian, String trangThai, String maNhaCungCap, String nguoitaoPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
        this.thoiGian = thoiGian;
        this.trangThai = trangThai;
        this.maNhaCungCap = maNhaCungCap;
        this.nguoitaoPhieuNhap = nguoitaoPhieuNhap;
    }

    public String getMaPhieuNhap() { return maPhieuNhap; }
    public void setMaPhieuNhap(String maPhieuNhap) { this.maPhieuNhap = maPhieuNhap; }

    public Date getThoiGian() { return thoiGian; }
    public void setThoiGian(Date thoiGian) { this.thoiGian = thoiGian; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public String getMaNhaCungCap() { return maNhaCungCap; }
    public void setMaNhaCungCap(String maNhaCungCap) { this.maNhaCungCap = maNhaCungCap; }

    public String getNguoitaoPhieuNhap() { return nguoitaoPhieuNhap; }
    public void setNguoitaoPhieuNhap(String nguoitaoPhieuNhap) { this.nguoitaoPhieuNhap = nguoitaoPhieuNhap; }
}