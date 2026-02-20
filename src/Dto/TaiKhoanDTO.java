package Dto;

public class TaiKhoanDTO {
    private String Ten;
    private String MatKhau;
    private String Email;
    public TaiKhoanDTO(){};
    public TaiKhoanDTO(String Ten,String MatKhau,String Email){
        this.Ten=Ten;
        this.MatKhau=MatKhau;
        this.Email=Email;
    }
    public String getTen(){
        return Ten;
    }
    public void setTen(String Ten){
        this.Ten=Ten;
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
