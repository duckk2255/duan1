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
public class hoadon {
    private String MaHD;
    private String MaKH;
    private String MaNV;
    private Date NgayTaoHD;

    public hoadon() {
    }

    public hoadon(String MaHD, String MaKH, String MaNV, Date NgayTaoHD) {
        this.MaHD = MaHD;
        this.MaKH = MaKH;
        this.MaNV = MaNV;
        this.NgayTaoHD = NgayTaoHD;
    }

    public String getMaHD() {
        return MaHD;
    }

    public String getMaKH() {
        return MaKH;
    }

    public String getMaNV() {
        return MaNV;
    }

    public Date getNgayTaoHD() {
        return NgayTaoHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public void setNgayTaoHD(Date NgayTaoHD) {
        this.NgayTaoHD = NgayTaoHD;
    }
    
}
