package Dto;

import java.util.Date;

public class PhieuXuatDTO {

    private String maphieuxuat;
    private Date thoigian;
    private String trangthai;
    private String nguoitaophieuxuat;
    private String makhachhang;

    public PhieuXuatDTO(){}

    public PhieuXuatDTO(String maphieuxuat, Date thoigian,
            String trangthai, String nguoitaophieuxuat, String makhachhang){

        this.maphieuxuat = maphieuxuat;
        this.thoigian = thoigian;
        this.trangthai = trangthai;
        this.nguoitaophieuxuat = nguoitaophieuxuat;
        this.makhachhang = makhachhang;
    }

    public String getMaphieuxuat() {
        return maphieuxuat;
    }

    public void setMaphieuxuat(String maphieuxuat) {
        this.maphieuxuat = maphieuxuat;
    }

    public Date getThoigian() {
        return thoigian;
    }

    public void setThoigian(Date thoigian) {
        this.thoigian = thoigian;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getNguoitaophieuxuat() {
        return nguoitaophieuxuat;
    }

    public void setNguoitaophieuxuat(String nguoitaophieuxuat) {
        this.nguoitaophieuxuat = nguoitaophieuxuat;
    }

    public String getMakhachhang() {
        return makhachhang;
    }

    public void setMakhachhang(String makhachhang) {
        this.makhachhang = makhachhang;
    }
}