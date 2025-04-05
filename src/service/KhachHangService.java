/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import util.connectdb;
import model.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HungLOL
 */
public class KhachHangService {

    private Connection conn;

    public KhachHangService() {
        try {
            conn = connectdb.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<KhachHang> findAll() {
        ArrayList<KhachHang> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM KhachHang";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maKH = rs.getString("MaKH").trim();
                String tenKH = rs.getString("TenKH").trim();
                int tuoiKH = rs.getInt("TuoiKH");
                String emailKH = rs.getString("EmailKH").trim();
                String diaChiKH = rs.getString("DiaChiKH").trim();
                String sdtKH = rs.getString("SdtKH").trim();
                int treEm = rs.getInt("TreEm");
                int nguoiLon = rs.getInt("NguoiLon");
                String ghiChu = rs.getString("GhiChu").trim();
                KhachHang kh = new KhachHang(maKH, tenKH, tuoiKH, emailKH, diaChiKH, sdtKH, treEm, nguoiLon, ghiChu);
                list.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean exists(String maKH) {
        String sql = "SELECT COUNT(*) FROM KhachHang WHERE MaKH = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maKH);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void add(KhachHang kh) throws SQLException {
        if (exists(kh.getMaKH())) {
            throw new SQLException("Mã khách hàng đã tồn tại.");
        }
        String sql = "INSERT INTO KhachHang (MaKH, TenKH, TuoiKH, EmailKH, DiaChiKH, SdtKH, TreEm, NguoiLon, GhiChu) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, kh.getMaKH());
            pstmt.setString(2, kh.getTenKH());
            pstmt.setInt(3, kh.getTuoiKH());
            pstmt.setString(4, kh.getEmailKH());
            pstmt.setString(5, kh.getDiaChiKH());
            pstmt.setString(6, kh.getSdtKH());
            pstmt.setInt(7, kh.getTreEm());
            pstmt.setInt(8, kh.getNguoiLon());
            pstmt.setString(9, kh.getGhiChu());
            pstmt.executeUpdate();
        
    }

    public void delete(String maKH) {
        try {
            String sql = "DELETE FROM KhachHang WHERE MaKH = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, maKH);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean change(KhachHang kh) throws SQLException {
        int row = 0;
            String query = "UPDATE KhachHang SET TenKH = ?, TuoiKH = ?, EmailKH = ?, DiaChiKH = ?, SdtKH = ?, TreEm = ?, NguoiLon = ?, GhiChu = ? WHERE MaKH = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, kh.getTenKH());
                pstmt.setInt(2, kh.getTuoiKH());
                pstmt.setString(3, kh.getEmailKH());
                pstmt.setString(4, kh.getDiaChiKH());
                pstmt.setString(5, kh.getSdtKH());
                pstmt.setInt(6, kh.getTreEm());
                pstmt.setInt(7, kh.getNguoiLon());
                pstmt.setString(8, kh.getGhiChu());
                pstmt.setString(9, kh.getMaKH());
                row = pstmt.executeUpdate();
        return row > 0;
    }

    public KhachHang findById(String maKH) {
        KhachHang kh = null;
        try {
            String query = "SELECT * FROM KhachHang WHERE MaKH = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, maKH);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    String tenKH = rs.getString("TenKH");
                    int tuoiKH = rs.getInt("TuoiKH");
                    String emailKH = rs.getString("EmailKH");
                    String diaChiKH = rs.getString("DiaChiKH");
                    String sdtKH = rs.getString("SdtKH");
                    int treEm = rs.getInt("TreEm");
                    int nguoiLon = rs.getInt("NguoiLon");
                    String ghiChu = rs.getString("GhiChu");
                    kh = new KhachHang(maKH, tenKH, tuoiKH, emailKH, diaChiKH, sdtKH, treEm, nguoiLon, ghiChu);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kh;
    }
}
