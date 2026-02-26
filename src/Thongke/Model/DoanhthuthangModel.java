package Thongke.Model;

public class DoanhthuthangModel {
    private int thang;
    private double doanhThu;
    public DoanhthuthangModel(int thang, double doanhThu){
        this.thang = thang;
        this.doanhThu = doanhThu;
    }
    public int getThang() {
        return thang;
    }
    public double getDoanhThu(){
        return doanhThu;
    }
}
