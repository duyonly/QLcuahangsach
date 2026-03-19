package Dto;

public class KhachHangDTO {
    private String MaKH;
    private String TenKH;
    private String GioiTinh;
    private String SDT;
    private String Email;
    private String DiaChi;
    private String LoaiKhachHang;
    private int DiemTichLuy;

    public KhachHangDTO() {}

    public KhachHangDTO(String MaKH, String TenKH, String GioiTinh, String SDT, String Email, String DiaChi, String LoaiKhachHang, int DiemTichLuy) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.GioiTinh = GioiTinh;
        this.SDT = SDT;
        this.Email = Email;
        this.DiaChi = DiaChi;
        this.LoaiKhachHang = LoaiKhachHang;
        this.DiemTichLuy = DiemTichLuy;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getGioiTinh() { return GioiTinh; }

    public void setGioiTinh(String GioiTinh) { this.GioiTinh = GioiTinh; }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getLoaiKhachHang() { return LoaiKhachHang; }

    public void setLoaiKhachHang(String LoaiKhachHang) { this.LoaiKhachHang = LoaiKhachHang; }

    public int getDiemTichLuy() { return DiemTichLuy; }

    public void setDiemTichLuy(int DiemTichLuy) { this.DiemTichLuy = DiemTichLuy; }
}