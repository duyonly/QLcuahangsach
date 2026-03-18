package Dto;
import java.util.Date;
public class DoiTraDto {
    private int MaDoiTra;
    private String maHD;
    private String maSachCu;
    private String maSachMoi;
    private int soLuong;
    private String Loai;
    private String lyDo;
    private double tienHoan;
    private double tienThem;
    private Date ngayYeuCau;
    
    private String maNV;

    public DoiTraDto(){};
    public DoiTraDto(String maHD,String maSachCu,String maSachMoi,int soLuong,String Loai,
        String lyDo,double tienHoan,double tienThem,String maNV){
        this.maHD=maHD;
        this.maSachCu=maSachCu;
        this.maSachMoi=maSachMoi;
        this.soLuong=soLuong;
        this.Loai=Loai;
        this.lyDo=lyDo;
       this.tienHoan=tienHoan;
       this.tienThem=tienThem;
       this.maNV=maNV;
    }
    public int getMaDoiTra(){
        return MaDoiTra;
    }
    public void setMaDoiTra(int MaDoiTra) { this.MaDoiTra = MaDoiTra; }

    public String getMaHD() { return maHD; }
    public void setMaHD(String maHD) { this.maHD = maHD; }

    public String getMaSachCu() { return maSachCu; }
    public void setMaSachCu(String maSachCu) { this.maSachCu = maSachCu; }

    public String getMaSachMoi() { return maSachMoi; }
    public void setMaSachMoi(String maSachMoi) { this.maSachMoi = maSachMoi; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public String getLoai(){
return Loai;
    }
    public void setLoai(String Loai){
        this.Loai=Loai;
    } 
    public String getLyDo() { return lyDo; }
    public void setLyDo(String lyDo) { this.lyDo = lyDo; }
    public double getTienHoan(){
        return tienHoan;
    }
    public void setTienHoan(double tienHoan){
        this.tienHoan=tienHoan;
    }
    public double getTienThem(){
        return tienThem;
    }
    public void setTienThem(double tienThem){
        this.tienThem=tienThem;
    }
    public Date getNgayYeuCau(){
        return ngayYeuCau;
    }
    public void setNgayYeuCau(Date ngayYeuCau){
        this.ngayYeuCau=ngayYeuCau;
    }
    public String getMaNV(){
        return maNV;
    }
    public void setMaNV(String maNV){
        this.maNV=maNV;
    }
}
