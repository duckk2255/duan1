/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.Date;
import util.connectdb;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.tour;
import java.sql.CallableStatement;

/**
 *
 * @author LINH
 */
public class tourservice {
    private Connection conn;

    public tourservice() {
        conn=connectdb.getConnection();
    }
    public ArrayList<tour> getData() throws SQLException{
        ArrayList<tour> lst=new ArrayList();
        String query="select * from Tour";
        Statement stm=conn.createStatement();
        ResultSet rs=stm.executeQuery(query);
        while(rs.next()){
            tour tr=new tour();
            tr.setMaTour(rs.getString("MaTour").trim());
            tr.setTenTour(rs.getString("TenTour").trim());
            tr.setMaDiaDiem(rs.getString("MaDiaDiem").trim());
            tr.setHinhAnhTour(rs.getString("HinhAnhTour").trim());
            tr.setThoiGian(rs.getString("ThoiGian").trim());
            tr.setGiaNguoiLon(rs.getFloat("GiaNguoiLon"));
            tr.setGiaTreEm(rs.getFloat("GiaTreEm"));
            tr.setNgayBatDau(rs.getDate("NgayBatDau"));
            tr.setNgayKetThuc(rs.getDate("NgayKetThuc"));
            tr.setMoTaTour(rs.getString("MoTaTour").trim());
            lst.add(tr);
        }
        return lst;
    }
    public boolean exists (String MaTour){
        String sql="select count(*) from Tour where MaTour=?";
        try(PreparedStatement ps =conn.prepareStatement(sql)){
            ps.setString(1, MaTour);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1)>0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void them(tour tr) throws SQLException{
        if(exists(tr.getMaTour())){
            throw new SQLException("Mã Tour đã tồn tại.");
        }
        String query="insert into Tour values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1, tr.getMaTour()); 
        ps.setString(2, tr.getMaDiaDiem());
        ps.setString(3, tr.getTenTour());
        ps.setString(4, tr.getHinhAnhTour());
        ps.setString(5, tr.getThoiGian());
        ps.setFloat(6, tr.getGiaNguoiLon());
        ps.setFloat(7, tr.getGiaTreEm());
        ps.setDate(8, (Date) tr.getNgayBatDau());
        ps.setDate(9, (Date) tr.getNgayKetThuc());
        ps.setString(10, tr.getMoTaTour());
        ps.execute();
    }
    public void sua(tour tr) throws SQLException{
        String query="update Tour set TenTour=?, HinhAnhTour=?, ThoiGian=?, GiaNguoiLon=?, GiaTreEm=?, NgayBatDau=?, NgayKetThuc=?, MoTaTour=?";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1, tr.getTenTour());
        ps.setString(2, tr.getHinhAnhTour());
        ps.setString(3, tr.getThoiGian());
        ps.setFloat(4, tr.getGiaNguoiLon());
        ps.setFloat(5, tr.getGiaTreEm());
        ps.setDate(6, (Date)tr.getNgayBatDau());
        ps.setDate(7, (Date)tr.getNgayBatDau());
        ps.setString(8, tr.getMoTaTour());
        ps.execute();
    }
    public void xoa(String MaTour) throws SQLException{
        String query="delete from Tour where MaTour=?";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1, MaTour);
        ps.execute();
    }
    public void timkiem(String MaTour) throws SQLException{
        
    }
}
