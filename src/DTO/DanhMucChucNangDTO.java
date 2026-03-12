package DTO;
public class DanhMucChucNangDTO {
    private String maChucNang;
    private String tenChucNang;

    public DanhMucChucNangDTO() {
    }

    public DanhMucChucNangDTO(String maChucNang, String tenChucNang) {
        this.maChucNang = maChucNang;
        this.tenChucNang = tenChucNang;
    }

    public String getMaChucNang() { return maChucNang; }
    public void setMaChucNang(String maChucNang) { this.maChucNang = maChucNang; }

    public String getTenChucNang() { return tenChucNang; }
    public void setTenChucNang(String tenChucNang) { this.tenChucNang = tenChucNang; }
}