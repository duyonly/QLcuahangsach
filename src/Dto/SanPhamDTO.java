package Dto;

public class SanPhamDTO(){
    private String masp;
    private String tensp;
    private String tentacgia;
    private String matheloai;
    private String nhaxuatban;
    private int namxuatban;
    private int gianhap;
    private int giaban;
    private int soluongton;
    private String mota;
    private int trangthai;
    public SanPhamDTO(){};
    public SanPhamDTO(String masp, String tensp, String tentacgia, String matheloai,
                      String nhaxuatban, int namxuatban, int gianhap,
                      int giaban, int soluongton, String mota, int trangthai){
        this.masp = masp;
        this.tensp = tensp;
        this.tentacgia = tentacgia;
        this.matheloai = matheloai;
        this.nhaxuatban = nhaxuatban;
        this.namxuatban = namxuatban;
        this.gianhap = gianhap;
        this.giaban = giaban;
        this.soluongton = soluongton;
        this.mota = mota;
        this.trangthai = trangthai;
    }
    public SanPhamDTO(SanPhamDTO sp){
        this.masp = sp.masp;
        this.tensp = sp.tensp;
        this.tentacgia = sp.tentacgia;
        this.matheloai = sp.matheloai;
        this.nhaxuatban = sp.nhaxuatban;
        this.namxuatban = sp.namxuatban;
        this.gianhap = sp.gianhap;
        this.giaban = sp.giaban;
        this.soluongton = sp.soluongton;
        this.mota = sp.mota;
        this.trangthai = sp.trangthai;
    }
    public String getMasp(){return masp;}
    public void setMasp(String masp){this.masp = masp;}
    public String getTensp(){return tensp;}
    public void setTensp(String tensp){this.tensp = tensp;}
    public String getTentg(){return tentacgia;}
    public void setTentg(String tentacgia){this.tentacgia = tentacgia;}
    public String getMatheloai(){return matheloai;}
    public void setMatheloai(String matheloai){this.matheloai = matheloai;}
    public String getNhaxuatban(){return nhaxuatban;}
    public void setNhaxuatban(String nhaxuatban){this.nhaxuatban = nhaxuatban;}
    public int getNamxuatban(){return namxuatban;}
    public void setNamxuatban(int namxuatban){this.namxuatban = namxuatban;}
    public int getGianhap(){return gianhap;}
    public void setGianhap(int gianhap){this.gianhap = gianhap;}
    public int getGiaban(){ return giaban; }
    public void setGiaban(int giaban){ this.giaban = giaban; }
    public int getSoluongton(){ return soluongton; }
    public void setSoluongton(int soluongton){ this.soluongton = soluongton; }
    public String getMota(){ return mota; }
    public void setMota(String mota){ this.mota = mota; }
    public int getTrangthai(){ return trangthai; }
    public void setTrangthai(int trangthai){ this.trangthai = trangthai; }
}