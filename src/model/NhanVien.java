
package model;


public class NhanVien {
    
    public String maNV;
    public String tenNV;
    public String user;
    public String pass;
   
    public String hinh;
    public String soDienThoai;
    public String emailNV;

    public NhanVien() {
    }

    public NhanVien(String maNV, String tenNV, String user, String pass, String hinh, String soDienThoai, String emailNV) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.user = user;
        this.pass = pass;
        this.hinh = hinh;
        this.soDienThoai = soDienThoai;
        this.emailNV = emailNV;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmailNV() {
        return emailNV;
    }

    public void setEmailNV(String emailNV) {
        this.emailNV = emailNV;
    }

   
  
    
     public Object[]toDataRow(){
        return new Object[]{
        maNV,tenNV,hinh,soDienThoai,emailNV
        };
    }

//    @Override
//    public String toString() {
//        return "NhanVien{" + "maNV=" + maNV + ", tenNV=" + tenNV + ", tenDangNhap=" + tenDangNhap + ", matKhau=" + matKhau + ", hinh=" + hinh + ", soDienThoai=" + soDienThoai + ", emailNV=" + emailNV + '}';
//    }

   
}
