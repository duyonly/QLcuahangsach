package Dto;

public class chitiethoadon {
private String MaHD;
private String maSach;
private int soLuong;
private double donGia;
private double thanhTien;
public chitiethoadon(){};
public chitiethoadon(String MaHD,String maSach,int soLuong,double donGia,double thanhTien){
    this.MaHD=MaHD;
    this.maSach=maSach;
    this.soLuong=soLuong;
    this.donGia=donGia;
    this.thanhTien=thanhTien;
}
public String getMaHD(){
    return MaHD;
}
public void setMaHD(String MaHD){
    this.MaHD=MaHD;
}
public String getMaSach() {
    return maSach;
}
public void setMaSach(String maSach) {
    this.maSach = maSach;
}
public int getSoLuong() {
    return soLuong;
}
public void setSoLuong(int soLuong) {
    this.soLuong = soLuong;
}
public double getDonGia() {
    return donGia;
}
public void setDonGia(double donGia) {
    this.donGia = donGia;
}
public double getThanhTien() {
    return thanhTien;
}
public void setThanhTien(double thanhTien) {
    this.thanhTien = thanhTien;
}
}