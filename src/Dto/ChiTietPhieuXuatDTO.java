package Dto;

public class ChiTietPhieuXuatDTO {

    private String maphieuxuat;
    private String masanpham;
    private int soluong;
    private double dongia;

    public ChiTietPhieuXuatDTO(){}

    public ChiTietPhieuXuatDTO(String maphieuxuat, String masanpham, int soluong, double dongia){
        this.maphieuxuat = maphieuxuat;
        this.masanpham = masanpham;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public String getMaphieuxuat() {
        return maphieuxuat;
    }

    public void setMaphieuxuat(String maphieuxuat) {
        this.maphieuxuat = maphieuxuat;
    }

    public String getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(String masanpham) {
        this.masanpham = masanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }
}