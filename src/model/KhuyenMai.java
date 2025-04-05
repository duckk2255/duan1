
package model;

import java.util.Date;

public class KhuyenMai {
    private String MaKM;
    private String NoiDungKM;
    private float GiamGia;
    private Date NgayBatDau;
    private Date NgayKetThuc;

    public KhuyenMai() {
    }

    public KhuyenMai(String MaKM, String NoiDungKM, float GiamGia, Date NgayBatDau, Date NgayKetThuc) {
        this.MaKM = MaKM;
        this.NoiDungKM = NoiDungKM;
        this.GiamGia = GiamGia;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
    }

    public String getMaKM() {
        return MaKM;
    }

    public void setMaKM(String MaKM) {
        this.MaKM = MaKM;
    }

    public String getNoiDungKM() {
        return NoiDungKM;
    }

    public void setNoiDungKM(String NoiDungKM) {
        this.NoiDungKM = NoiDungKM;
    }

    public float getGiamGia() {
        return GiamGia;
    }

    public void setGiamGia(float GiamGia) {
        this.GiamGia = GiamGia;
    }

    public Date getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(Date NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public Date getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(Date NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

 
   
}
