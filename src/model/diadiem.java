/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LINH
 */
public class diadiem {
    private int MaDiaDiem;
    private String TenDiaDiem;

    public diadiem() {
    }

    public diadiem(int MaDiaDiem, String TenDiaDiem) {
        this.MaDiaDiem = MaDiaDiem;
        this.TenDiaDiem = TenDiaDiem;
    }

    public int getMaDiaDiem() {
        return MaDiaDiem;
    }

    public String getTenDiaDiem() {
        return TenDiaDiem;
    }

    public void setMaDiaDiem(int MaDiaDiem) {
        this.MaDiaDiem = MaDiaDiem;
    }

    public void setTenDiaDiem(String TenDiaDiem) {
        this.TenDiaDiem = TenDiaDiem;
    }
    
}
