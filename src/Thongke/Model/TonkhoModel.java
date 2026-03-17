package Thongke.Model;

public class TonkhoModel{
    private String maSach;
    private String tenSach;
    private int soLuongTon;
    public TonkhoModel(String maSach, String tenSach, int soLuongTon){
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.soLuongTon = soLuongTon;
    }
    public String getMaSach(){
        return maSach;
    }
    public String getTenSach(){
        return tenSach;
    }
    public int getSoLuongTon(){
        return soLuongTon;
    }
}
