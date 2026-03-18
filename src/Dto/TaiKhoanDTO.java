package Dto;

public class TaiKhoanDTO {
    
        private String maTK, maNV, tenDangNhap, matKhau, quyen, trangThai, email;
    
        public TaiKhoanDTO() {}
    
        public TaiKhoanDTO(String maTK, String maNV, String tenDangNhap,
                          String matKhau, String quyen, String trangThai, String email) {
            this.maTK = maTK;
            this.maNV = maNV;
            this.tenDangNhap = tenDangNhap;
            this.matKhau = matKhau;
            this.quyen = quyen;
            this.trangThai = trangThai;
            this.email = email;
        }
    
        public String getMaTK() { return maTK; }
        public void setMaTK(String maTK) { this.maTK = maTK; }
    
        public String getMaNV() { return maNV; }
        public void setMaNV(String maNV) { this.maNV = maNV; }
    
        public String getTenDangNhap() { return tenDangNhap; }
        public void setTenDangNhap(String tenDangNhap) { this.tenDangNhap = tenDangNhap; }
    
        public String getMatKhau() { return matKhau; }
        public void setMatKhau(String matKhau) { this.matKhau = matKhau; }
    
        public String getQuyen() { return quyen; }
        public void setQuyen(String quyen) { this.quyen = quyen; }
    
        public String getTrangThai() { return trangThai; }
        public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
    
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }

