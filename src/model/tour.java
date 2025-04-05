/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.Date;
/**
 *
 * @author LINH
 */
public class tour {
    private String MaTour;
    private String TenTour;
    private String MaDiaDiem;
    private String HinhAnhTour;
    private String ThoiGian;
    private float GiaNguoiLon;
    private float GiaTreEm;
    private Date NgayBatDau;
    private Date NgayKetThuc;
    private String MoTaTour;

    public tour() {
    }

    public tour(String MaTour, String TenTour, String MaDiaDiem, String HinhAnhTour, String ThoiGian, float GiaNguoiLon, float GiaTreEm, Date NgayBatDau, Date NgayKetThuc, String MoTaTour) {
        this.MaTour = MaTour;
        this.TenTour = TenTour;
        this.MaDiaDiem = MaDiaDiem;
        this.HinhAnhTour = HinhAnhTour;
        this.ThoiGian = ThoiGian;
        this.GiaNguoiLon = GiaNguoiLon;
        this.GiaTreEm = GiaTreEm;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.MoTaTour = MoTaTour;
    }

    public String getMaTour() {
        return MaTour;
    }

    public String getTenTour() {
        return TenTour;
    }

    public String getMaDiaDiem() {
        return MaDiaDiem;
    }

    public String getHinhAnhTour() {
        return HinhAnhTour;
    }

    public String getThoiGian() {
        return ThoiGian;
    }

    public float getGiaNguoiLon() {
        return GiaNguoiLon;
    }

    public float getGiaTreEm() {
        return GiaTreEm;
    }

    public Date getNgayBatDau() {
        return NgayBatDau;
    }

    public Date getNgayKetThuc() {
        return NgayKetThuc;
    }

    public String getMoTaTour() {
        return MoTaTour;
    }

    public void setMaTour(String MaTour) {
        this.MaTour = MaTour;
    }

    public void setTenTour(String TenTour) {
        this.TenTour = TenTour;
    }

    public void setMaDiaDiem(String MaDiaDiem) {
        this.MaDiaDiem = MaDiaDiem;
    }

    public void setHinhAnhTour(String HinhAnhTour) {
        this.HinhAnhTour = HinhAnhTour;
    }

    public void setThoiGian(String ThoiGian) {
        this.ThoiGian = ThoiGian;
    }

    public void setGiaNguoiLon(float GiaNguoiLon) {
        this.GiaNguoiLon = GiaNguoiLon;
    }

    public void setGiaTreEm(float GiaTreEm) {
        this.GiaTreEm = GiaTreEm;
    }

    public void setNgayBatDau(Date NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public void setNgayKetThuc(Date NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

    public void setMoTaTour(String MoTaTour) {
        this.MoTaTour = MoTaTour;
    }
    
}
