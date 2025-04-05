
package model;


public class guide {
    private String maHDV;
    private String maTour;
    private String tenHDV;
    private int soDienThoaiHDV;
    private String emailHDV;

    public guide() {
    }

    public guide(String maHDV, String maTour, String tenHDV, int soDienThoaiHDV, String emailHDV) {
        this.maHDV = maHDV;
        this.maTour = maTour;
        this.tenHDV = tenHDV;
        this.soDienThoaiHDV = soDienThoaiHDV;
        this.emailHDV = emailHDV;
    }

    public String getMaHDV() {
        return maHDV;
    }

    public void setMaHDV(String maHDV) {
        this.maHDV = maHDV;
    }

    public String getMaTour() {
        return maTour;
    }

    public void setMaTour(String maTour) {
        this.maTour = maTour;
    }

    public String getTenHDV() {
        return tenHDV;
    }

    public void setTenHDV(String tenHDV) {
        this.tenHDV = tenHDV;
    }

    public int getSoDienThoaiHDV() {
        return soDienThoaiHDV;
    }

    public void setSoDienThoaiHDV(int soDienThoaiHDV) {
        this.soDienThoaiHDV = soDienThoaiHDV;
    }

    public String getEmailHDV() {
        return emailHDV;
    }

    public void setEmailHDV(String emailHDV) {
        this.emailHDV = emailHDV;
    }
}
