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
import model.hoadon;
import model.chitiethoadon;
/**
 *
 * @author LINH
 */
public class hoadonservice {
    private Connection conn;

    public hoadonservice() {
        conn=connectdb.getConnection();
    }
    public ArrayList<hoadon> getData() throws SQLException{
        ArrayList<hoadon> lst =new ArrayList();
        String query="select * from HoaDon";
        Statement stm =conn.createStatement();
        ResultSet rs=stm.executeQuery(query);
        while(rs.next()){
            hoadon hd=new hoadon();
            hd.setMaHD(rs.getString("MaHD").trim());
            hd.setMaKH(rs.getString("MaKH").trim());
            hd.setMaNV(rs.getString("MaNV").trim());
            hd.setNgayTaoHD(rs.getDate("NgayTaoHD"));
            lst.add(hd);
        }
    return lst;
    }
    public void sua(hoadon hd) throws SQLException{
        String query="update HoaDon set NgayTaoHD=? where MaHD=?";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setDate(1, (Date) hd.getNgayTaoHD());
        ps.setString(2, hd.getMaHD());
        ps.execute();
    }
    public void xoa(String MaHD) throws SQLException{
        String query="delete from HoaDon where MaHD=?";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1, MaHD);
        ps.execute();
    }
}
