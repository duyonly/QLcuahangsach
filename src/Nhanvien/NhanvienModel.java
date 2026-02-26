package Nhanvien;
import java.time.LocalDate;
import java.util.Objects;

public class NhanvienModel{
    private String manv;
    private String tennv;
    private String gioitinh;
    private LocalDate ngaysinh;
    private String diachi;
    private String chucvu;
    private double luong;
    private String tendangnhap;
    private String matkhau;
    private int trang_thai;  //(0: đã nghỉ việc; 1: đang làm việc)

    public NhanvienModel(){}

    public NhanvienModel(String manv, String tennv, String gioitinh,
                    LocalDate ngaysinh, String diachi, String chucvu,
                    double luong, String tendangnhap, String matkhau, int trang_thai) {
        this.manv = manv;
        this.tennv = tennv;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.chucvu = chucvu;
        this.luong = luong;
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
        this.trang_thai = trang_thai;
    }

    public String getMaNV(){ return manv; }
    public void setMaNV(String manv){ this.manv = manv; }

    public String getTenNV(){ return tennv; }
    public void setTenNV(String tennv) { this.tennv = tennv; }

    public String getGioiTinh(){ return gioitinh; }
    public void setGioiTinh(String gioitinh){ this.gioitinh = gioitinh; }

    public LocalDate getNgaySinh(){ return ngaysinh; }
    public void setNgaySinh(LocalDate ngaysinh){ this.ngaysinh = ngaysinh; }

    public String getDiaChi(){ return diachi; }
    public void setDiaChi(String diachi){ this.diachi = diachi; }

    public String getChucVu(){ return chucvu; }
    public void setChucVu(String chucvu){ this.chucvu = chucvu; }

    public double getLuong(){ return luong; }
    public void setLuong(double luong){ this.luong = luong; }

    public String getTenDangNhap(){ return tendangnhap; }
    public void setTenDangNhap(String tendangnhap){ this.tendangnhap = tendangnhap; }

    public String getMatKhau(){ return matkhau; }
    public void setMatKhau(String matkhau){ this.matkhau = matkhau; }

    public int getTrang_thai(){ return this.trang_thai;}
    public void setTrang_thai(int trangthai){ this.trang_thai = trangthai;}

    public boolean laQuanLy() {
        return "Quản lý".equalsIgnoreCase(this.chucvu);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NhanvienModel)) return false;
        NhanvienModel other = (NhanvienModel) o;
        return manv != null && manv.equals(other.manv);
    }
    @Override
    public int hashCode() {
        return manv != null ? manv.hashCode() : 0;
    }
    @Override
    public String toString() {
        return "NhanVien{" +
                "maNV:'" + manv + '\'' +
                ", tenNV:'" + tennv + '\'' +
                ", gioitinh:'" + gioitinh + '\'' +
                ", ngaysinh:" + ngaysinh +
                ", diachi:'" + diachi + '\'' +
                ", chucvu:'" + chucvu + '\'' +
                ", luong:" + luong +
                ", tendangnhap:'" + tendangnhap + '\'' +
             //   ", matkhau:'" + matkhau + '\'' +
                ", trangthai:'" + trang_thai + '}';
    }
}
