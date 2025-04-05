
package service;

import util.connectdb;
import model.NhanVien;
import java.sql.*;
import java.util.ArrayList;

public class NhanVienService {
    
    private Connection conn=null;
    private PreparedStatement ps=null ;
    private ResultSet rs =null ;
    public ArrayList<NhanVien> getAll(){
        String sql ="""
                    SELECT * FROM NhanVien
                    """;
        ArrayList<NhanVien> list = new ArrayList<>();
        try {
            conn=connectdb.getConnection();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                NhanVien nv  = new NhanVien();
                nv.setMaNV(rs.getString(1).trim());
                nv.setTenNV(rs.getString(2).trim());
                nv.setUser(rs.getString(3).trim());
                nv.setPass(rs.getString(4).trim());
                nv.setHinh(rs.getString(5).trim());
                nv.setSoDienThoai(rs.getString(6).trim());
                nv.setEmailNV(rs.getString(7).trim());
                list.add(nv);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return list;
    }
    public int Them(NhanVien nv ){
        String sql = """
                     INSERT INTO NhanVien(MaNV,TenNV,TenDangNhap,MatKhau,HinhAnhNV,SdtNV,EmailNV) VALUES (?, ?, ?, ?, ?, ?, ?)
                     """;
        try {
            conn=connectdb.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getTenNV());
            ps.setString(3, nv.getUser());
            ps.setString(4, nv.getPass());
            ps.setString(5, nv.getHinh());
            ps.setString(6, nv.getSoDienThoai());
            ps.setString(7, nv.getEmailNV());
            int x = ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
      public int Xoa(NhanVien nv ){
        String sql = """
                     DELETE FROM NhanVien
                     WHERE MaNV = ?
                     """;
        try {
            conn=connectdb.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1, nv.getMaNV());
           
            int x = ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
        
    }
       public int Sua(NhanVien nv ){
        String sql = """
                     UPDATE NhanVien
                     SET  TenNV=?, TenDangNhap=?, MatKhau=?, HinhAnhNV=?, SdtNV=?, EmailNV=?
                     WHERE MaNV = ?
                     """;
        try {
            conn=connectdb.getConnection();
            ps=conn.prepareStatement(sql);
         
            ps.setString(1, nv.getTenNV());
            ps.setString(2, nv.getUser());
            ps.setString(3, nv.getPass());
            ps.setString(4, nv.getHinh());
            ps.setString(5, nv.getSoDienThoai());
            ps.setString(6, nv.getEmailNV());
            int x = ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    
}
        public ArrayList<NhanVien> search(String maNv){
        String sql ="""
                    SELECT * FROM NhanVien WHERE MaNV =?
                    """;
        ArrayList<NhanVien> list = new ArrayList<>();
        try {
            conn=connectdb.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setObject(1, maNv);
            rs=ps.executeQuery();
            while (rs.next()) {                
                NhanVien nv  = new NhanVien();
                nv.setMaNV(rs.getString(1));
                nv.setTenNV(rs.getString(2));
                nv.setHinh(rs.getString(3));
                nv.setSoDienThoai(rs.getString(4));
                nv.setEmailNV(rs.getString(5));
                list.add(nv);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return list;
    }
}
