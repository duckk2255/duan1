package service;

import util.connectdb;
import java.sql.*;
import java.util.ArrayList;

public class KhuyenMaiService {

    private Connection conn = null;
    private PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<model.KhuyenMai> getAll() {
        ArrayList<model.KhuyenMai> KM = new ArrayList<>();
        try {
            String sql = "SELECT * FROM KhuyenMai";
            conn = connectdb.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maKM = rs.getString(1).trim();
                String noiDungKM = rs.getString(2).trim();
                float giamGia = rs.getFloat(3);
                Date ngayBatDau = rs.getDate(4);
                Date ngayKetThuc = rs.getDate(5);
                model.KhuyenMai mkm = new model.KhuyenMai(maKM, noiDungKM, giamGia, ngayBatDau, ngayKetThuc);
                KM.add(mkm);
            }
            return KM;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
//         public void add(KhoaHoc kh) throws SQLException{
//        String query="insert into KhoaHoc values(?, ?, ?, ?)";
//        PreparedStatement ps = conn.prepareStatement(query);
//        ps.setInt(1, kh.getMaKH());
//        ps.setFloat(2, kh.getHocPhi());
//        ps.setInt(3, kh.getThoiLuong());
//        ps.setString(4, kh.getGhiChu());
//        
//        ps.execute();

    }

    public void add(model.KhuyenMai cnthem) {
        String sql = "INSERT INTO KhuyenMai(MaKM,NoiDungKM,GiamGia,NgayBatDau,NgayketThuc) values (?,?,?,?,?)";
        try {
            conn = connectdb.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, cnthem.getMaKM());
            ps.setObject(2, cnthem.getNoiDungKM());
            ps.setObject(3, cnthem.getGiamGia());
            ps.setObject(4, cnthem.getNgayBatDau());
            ps.setObject(5, cnthem.getNgayKetThuc());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public boolean checkTrung(String maKM) {
        String sql = "SELECT COUNT(*) FROM KhuyenMai WHERE maKM =0";
        try {
            conn = connectdb.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, maKM);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void delete(String maKM) {
        try {
                String sql = "DELETE FROM KhuyenMai WHERE MaKM = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maKM);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public boolean update(model.KhuyenMai km) {

        try {
            String sql = "UPDATE KhuyenMai SET noiDungKM = ?, GiamGia = ?, ngayBatDau = ?, ngayKetThuc = ? WHERE maKM = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, km.getNoiDungKM());
            ps.setFloat(2, km.getGiamGia());
            ps.setDate(3, (Date) km.getNgayBatDau());
            ps.setDate(4, (Date) km.getNgayKetThuc());
            ps.setString(5, km.getMaKM());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
//            public Model.KhuyenMai findByMaKM(String ma) {
//                Model.KhuyenMai km = null;
//        try {
//            String query = "SELECT * FROM KhuyenMai WHERE MaKM = ?";
//            PreparedStatement ps = conn.prepareStatement(query);
//            ps.setString(1, ma);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                String maKM=rs.getString("maKM");
//                String noiDung=rs.getString("noiDungKM");
//                String giamGia=rs.getString("giamGia");
//                String ngayBatDau=rs.getString("ngayBatDau");
//                String ngayKetThuc=rs.getString("ngayKetThuc");
//                Model.KhuyenMai KM = new Model.KhuyenMai(maKM, noiDung,0, ngayBatDau, ngayKetThuc);
//                
//            }
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return km;
//    }
//
//    public ArrayList<Model.KhuyenMai> search(String keyword, String maKM) {
//        ArrayList<Model.KhuyenMai> km = new ArrayList<>();
//        String sql = "SELECT * FROM KhuyenMai WHERE MaKM =?";
//        if (keyword.length() != 0) {
//            sql += "AND (noiDUngKM LIKE ? )";
//
//        }
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, maKM);
//            if (keyword.length() != 0) {
//                ps.setString(2, "%" + keyword + "%");
//
//            }
//            ps.execute();
//            ResultSet rs = ps.getResultSet();
//            while (rs.next()) {
//                String MaKM = rs.getString(1);
//                String noiDungKM = rs.getString(2);
//                int giamGia = rs.getInt(3);
//                String ngayBatDau = rs.getString(4);
//                String ngayKetThuc = rs.getString(5);
//                Model.KhuyenMai mkm = new Model.KhuyenMai(MaKM, noiDungKM, giamGia, ngayBatDau, ngayKetThuc);
//                km.add(mkm);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return km;
//    }
      public ArrayList<model.KhuyenMai> search(String MaKM) {
        ArrayList<model.KhuyenMai> KM = new ArrayList<>();
        try {
            String sql = "SELECT * FROM KhuyenMai WHere maKM =?";
            conn = connectdb.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, MaKM);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maKM = rs.getString(1);
                String noiDungKM = rs.getString(2);
                float giamGia = rs.getFloat(3);
                Date ngayBatDau = rs.getDate(4);
                Date ngayKetThuc = rs.getDate(5);
                model.KhuyenMai mkm = new model.KhuyenMai(maKM, noiDungKM, giamGia, ngayBatDau, ngayKetThuc);
                KM.add(mkm);
            }
            return KM;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
}
}
