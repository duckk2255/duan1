/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.chitiethoadon;
import util.connectdb;
/**
 *
 * @author LINH
 */
public class chitiethoadonservice {
    private Connection conn;

    public chitiethoadonservice() {
        conn=connectdb.getConnection();
    }
    public ArrayList<chitiethoadon> getData() throws SQLException{
        ArrayList<chitiethoadon> lst=new ArrayList();
        String query="select * from ChiTietHD";
        Statement stm =conn.createStatement();
        ResultSet rs=stm.executeQuery(query);
        while(rs.next()){
            chitiethoadon cthd=new chitiethoadon();
            cthd.setMaHD(rs.getString("MaHD").trim());
            cthd.setMaDV(rs.getString("MaDV"));
            cthd.setTenDV(rs.getString("TenDV"));
            cthd.setGiaDV(rs.getFloat("GiaDV"));
            cthd.setSLDV(rs.getInt("SLDV"));
            cthd.setMaTour(rs.getString("MaTour"));
            cthd.setTenTour(rs.getString("TenTour"));
            cthd.setGiaNL(rs.getFloat("GiaNL"));
            cthd.setSLNL(rs.getInt("SLNL"));
            cthd.setGiaTE(rs.getFloat("GiaTE"));
            cthd.setSLTE(rs.getInt("SLTE"));
            cthd.setMaKM(rs.getString("MaKM"));
            cthd.setNoiDungKM(rs.getString("NoiDungKM"));
            cthd.setPhanTramKM(rs.getFloat("PhanTramKM"));
            cthd.setTongTien(rs.getFloat("TongTien"));
            lst.add(cthd);
        }
        return lst;
    }
    public void them(chitiethoadon cthd) throws SQLException{
        String query="insert into ChiTietHD values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1, cthd.getMaHD());
        ps.setString(2, cthd.getMaDV());
        ps.setString(3, cthd.getTenDV());
        ps.setFloat(4, cthd.getGiaDV());
        ps.setInt(5, cthd.getSLDV());
        ps.setString(6, cthd.getMaTour());
        ps.setString(7, cthd.getTenTour());
        ps.setFloat(8, cthd.getGiaNL());
        ps.setInt(9, cthd.getSLNL());
        ps.setFloat(10, cthd.getGiaTE());
        ps.setInt(11, cthd.getSLTE());
        ps.setString(12, cthd.getMaHD());
        ps.setString(13, cthd.getNoiDungKM());
        ps.setFloat(14, cthd.getPhanTramKM());
        ps.setFloat(15, cthd.getTongTien());
        ps.execute();
    }   
    public void sua(chitiethoadon cthd) throws SQLException{
        String query="update ChiTietHD set MaDV=?, TenDV=?, GiaDV=?, SLDV=?, MaTour=?, TenTour=?, GiaNL=?, SLNL=?, GiaTE=?, SLTE=?, MaKM=?, NoiDungKM=?, PhanTramKM=?, TongTien=? where MaHD=?";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1, cthd.getMaDV());
        ps.setString(2, cthd.getTenDV());
        ps.setFloat(3, cthd.getGiaDV());
        ps.setInt(4, cthd.getSLDV());
        ps.setString(5, cthd.getMaTour());
        ps.setString(6, cthd.getTenTour());
        ps.setFloat(7, cthd.getGiaNL());
        ps.setInt(8, cthd.getSLNL());
        ps.setFloat(9, cthd.getGiaTE());
        ps.setInt(10, cthd.getSLTE());
        ps.setString(11, cthd.getMaKM());
        ps.setString(12, cthd.getNoiDungKM());
        ps.setFloat(13, cthd.getPhanTramKM());
        ps.setFloat(14, cthd.getPhanTramKM());
        ps.setString(15, cthd.getMaHD());
        ps.execute();
    }
    public void xoa(String MaHD) throws SQLException{
        String query="delete from ChiTietHD where MaHD=?";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1, MaHD);
        ps.execute();
    }
}
