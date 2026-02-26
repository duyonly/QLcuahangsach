package Thongke.Model;

public class TopsachModel{
    private String tenSach;
    private int soLuongBan;
    public TopsachModel(String tenSach, int soLuongBan){
        this.tenSach = tenSach;
        this.soLuongBan = soLuongBan;
    }
    public String getTenSach(){
        return tenSach;
    }
    public int getSoLuongBan(){
        return soLuongBan;
    }
}
