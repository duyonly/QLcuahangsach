package Thongke.Model;

public class ThongkenhanvienModel {
    private String maNV;
    private String tenNV;
    private double doanhThu;
    public ThongkenhanvienModel(String maNV, String tenNV, double doanhThu){
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.doanhThu = doanhThu;
    }
    public String getMaNV(){
        return maNV;
    }
    public String getTenNV(){
        return tenNV;
    }
    public double getDoanhThu(){
        return doanhThu;
    }
}
