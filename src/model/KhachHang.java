/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HungLOL
 */
public class KhachHang {

    private String maKH;
    private String tenKH;
    private int tuoiKH;
    private String emailKH;
    private String diaChiKH;
    private String sdtKH;
    private int treEm;
    private int nguoiLon;
    private String ghiChu;

    public KhachHang() {
    }

    public KhachHang(String maKH, String tenKH, int tuoiKH, String emailKH, String diaChiKH, String sdtKH, int treEm, int nguoiLon, String ghiChu) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.tuoiKH = tuoiKH;
        this.emailKH = emailKH;
        this.diaChiKH = diaChiKH;
        this.sdtKH = sdtKH;
        this.treEm = treEm;
        this.nguoiLon = nguoiLon;
        this.ghiChu = ghiChu;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public int getTuoiKH() {
        return tuoiKH;
    }

    public void setTuoiKH(int tuoiKH) {
        this.tuoiKH = tuoiKH;
    }

    public String getEmailKH() {
        return emailKH;
    }

    public void setEmailKH(String emailKH) {
        this.emailKH = emailKH;
    }

    public String getDiaChiKH() {
        return diaChiKH;
    }

    public void setDiaChiKH(String diaChiKH) {
        this.diaChiKH = diaChiKH;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public int getTreEm() {
        return treEm;
    }

    public void setTreEm(int treEm) {
        this.treEm = treEm;
    }

    public int getNguoiLon() {
        return nguoiLon;
    }

    public void setNguoiLon(int nguoiLon) {
        this.nguoiLon = nguoiLon;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

}
