/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HungLOL
 */
public class DichVu {
    private String maDV;
    private String tenDV;
    private String moTaDV;
    private float giaDV;

    public DichVu(String maDV, String tenDV, String moTaDV, float giaDV) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.moTaDV = moTaDV;
        this.giaDV = giaDV;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public String getMoTaDV() {
        return moTaDV;
    }

    public void setMoTaDV(String moTaDV) {
        this.moTaDV = moTaDV;
    }

    public float getGiaDV() {
        return giaDV;
    }

    public void setGiaDV(float giaDV) {
        this.giaDV = giaDV;
    }
}
    

