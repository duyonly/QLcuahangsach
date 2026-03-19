package Dto;

public class NhaCungCapDTO {
    private String MaNCC;
    private String TenNCC;
    private String DienThoai;
    private String Email;
    private String DiaChi;

    public NhaCungCapDTO() {}

    public NhaCungCapDTO(String MaNCC, String TenNCC, String DienThoai, String Email, String DiaChi) {
        this.MaNCC = MaNCC;
        this.TenNCC = TenNCC;
        this.DienThoai = DienThoai;
        this.Email = Email;
        this.DiaChi = DiaChi;
    }

    public String getMaNCC() { return MaNCC; }

    public void setMaNCC(String MaNCC) { this.MaNCC = MaNCC; }

    public String getTenNCC() { return TenNCC; }

    public void setTenNCC(String TenNCC) { this.TenNCC = TenNCC; }

    public String getDienThoai() {
        return DienThoai;
    }

    public void setDienThoai(String DienThoai) {
        this.DienThoai = DienThoai;
    }

    public String getEmail() {
return Email }

    public void setEmail(String Email) { this.Email = Email; }

    public String getDiaChi() { return DiaChi }

    public void setDiaChi(String DiaChi) { this.DiaChi = DiaChi; }
}

