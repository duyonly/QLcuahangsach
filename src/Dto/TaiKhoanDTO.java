package Dto;

public class TaiKhoanDTO {
    private String TenDangNhap;
    private String MatKhau;
    private String Email;
    public TaiKhoanDTO(){};
    public TaiKhoanDTO(String TenDangNhap,String MatKhau,String Email){
        this.TenDangNhap=TenDangNhap;
        this.MatKhau=MatKhau;
        this.Email=Email;
    }
    public String getTenDangNhap(){
        return TenDangNhap;
    }
    public void setTenDangNhap(String TenDangNhap){
        this.TenDangNhap=TenDangNhap;
    }
    public String getMatKhau(){
        return MatKhau;
    }
    public void setMatKhau(String MatKhau){
        this.MatKhau=MatKhau;
    }
    public String getEmail(){
        return Email;
    }
    public void setEmail(String Email){
        this.Email=Email;
    }
}
