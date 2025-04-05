
package service;

import util.connectdb;
import model.guide;
import java.sql.*;
import java.util.ArrayList;
import javax.management.modelmbean.ModelMBean;

public class guideService {
    private Connection conn=null;
    private PreparedStatement ps =null;
    private ResultSet rs = null;
    
    
    public ArrayList<guide> fillAll(){
        ArrayList<guide> HDV = new ArrayList<>();
        try {
            String sql="SELECT * FROM HDV";
            
            conn = (Connection) connectdb.getConnection();
            ps =conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                String maHDV=rs.getString(1).trim();
                String maTour=rs.getString(2).trim();
                String tenHDV=rs.getString(3).trim();
                int soDienThoaiHDV=rs.getInt(4);
                String emailHDV=rs.getString(5).trim();
                guide hdv = new guide(maHDV, maTour, tenHDV, soDienThoaiHDV, emailHDV);
                HDV.add(hdv);
                
            }
            return HDV;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean Them(guide cnThem){
        String sql="INSERT INTO HDV(MaHDV,MaTour,TenHDV,SdtHDV,EmailHDV) values (?,?,?,?,?)";
        int check=0;
        try (Connection conn =connectdb.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setObject(1, cnThem.getMaHDV());
            ps.setObject(2, cnThem.getMaTour());
            ps.setObject(3, cnThem.getTenHDV());
            ps.setObject(4, cnThem.getSoDienThoaiHDV());
            ps.setObject(5, cnThem.getEmailHDV());
            check =ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check >0;
    }
    public void Sua(guide cnSua,String maHDV){
        String sql="""
                   UPDATE [dbo].[HDV]
   
     SET   [MaTour] = ?
      ,[TenHDV] = ?
      ,[SdtHDV] = ?
      ,[EmailHDV] = ?
 WHERE [MaHDV] = ?
 """;
                             
        try (Connection conn = connectdb.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
                ){
//            ps.setObject(1, cnSua.getMaHDV());
            ps.setObject(1, cnSua.getMaTour());
            ps.setObject(2, cnSua.getTenHDV());
            ps.setObject(3, cnSua.getSoDienThoaiHDV());
            ps.setObject(4, cnSua.getEmailHDV());
             ps.setObject(5, maHDV);
            ps.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean checkTrung(String maHDV){
        String sql="SELECT COUNT(*) FROM HDV WHERE MaHDV =0";
        try {
            conn=connectdb.getConnection();
            ps =conn.prepareStatement(sql);
            ps.setString(0, maHDV);
            rs=ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1)>0;
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean delete(String maHDV){
        try {
             String sql="DELETE FROM HDV WHERE MaHDV = ?";
             PreparedStatement ps = conn.prepareStatement(sql);
             ps.setString(1, maHDV);
             ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
      public ArrayList<guide> search(String MaHDV){
        ArrayList<guide> HDV = new ArrayList<>();
        try {
            String sql="SELECT * FROM HDV where MaHDV =?";
            
            conn = (Connection) connectdb.getConnection();
            ps =conn.prepareStatement(sql);
            ps.setObject(1, MaHDV);
            rs=ps.executeQuery();
            while(rs.next()){
                String maHDV=rs.getString(1);
                String maTour=rs.getString(2);
                String tenHDV=rs.getString(3);
                int soDienThoaiHDV=rs.getInt(4);
                String emailHDV=rs.getString(5);
                guide hdv = new guide(maHDV, maTour, tenHDV, soDienThoaiHDV, emailHDV);
                HDV.add(hdv);
                
            }
            return HDV;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
       
        
      
       
    
