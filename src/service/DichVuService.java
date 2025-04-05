package service;

import util.connectdb;
import model.DichVu;
import java.sql.*;
import java.util.ArrayList;

public class DichVuService {
    private Connection conn;

    public DichVuService() {
        try {
            conn = connectdb.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<DichVu> findAll() {
        ArrayList<DichVu> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM DichVu";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maDV = rs.getString("MaDV").trim();
                String tenDV = rs.getString("TenDV").trim();
                String moTaDV = rs.getString("MoTaDV").trim();
                float giaDV = rs.getFloat("GiaDV");
                DichVu dv = new DichVu(maDV, tenDV, moTaDV, giaDV);
                list.add(dv);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean exists(String maDV) {
        String sql = "SELECT COUNT(*) FROM DichVu WHERE MaDV = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maDV);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void add(DichVu dv) throws SQLException {
        if (exists(dv.getMaDV())) {
            throw new SQLException("Mã dịch vụ đã tồn tại.");
        }

        String sql = "INSERT INTO DichVu (MaDV, TenDV, MoTaDV, GiaDV) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dv.getMaDV());
            pstmt.setString(2, dv.getTenDV());
            pstmt.setString(3, dv.getMoTaDV());
            pstmt.setFloat(4, dv.getGiaDV());
            pstmt.executeUpdate();
        }
    }

    public void delete(String id) {
        try {
            String sql = "DELETE FROM DichVu WHERE MaDV = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean change(DichVu dv) {
        int row = 0;
        try {
            String query = "UPDATE DichVu SET TenDV = ?, MoTaDV = ?, GiaDV = ? WHERE MaDV = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, dv.getTenDV());
            ps.setString(2, dv.getMoTaDV());
            ps.setFloat(3, dv.getGiaDV());
            ps.setString(4, dv.getMaDV());
            row = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row > 0;
    }

    public DichVu findById(String id) {
        DichVu dv = null;
        try {
            String query = "SELECT * FROM DichVu WHERE MaDV = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String maDV = rs.getString("MaDV");
                String tenDV = rs.getString("TenDV");
                String moTaDV = rs.getString("MoTaDV");
                float giaDV = rs.getFloat("GiaDV");
                dv = new DichVu(maDV, tenDV, moTaDV, giaDV);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dv;
    }

    public void close() {
        connectdb.closeConnection();
    }
}
