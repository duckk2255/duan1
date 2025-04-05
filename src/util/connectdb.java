
package util;

import java.sql.*;

public class connectdb {
    private static Connection conn;

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                String dbUser = "sa";
                String dbPass = "123456";
                String dbUrl = "jdbc:sqlserver://localhost:1433;databaseName=DuAn1N3;encrypt=true;trustServerCertificate=true;";
                conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                System.out.println("Kết nối thành công");
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi kết nối: " + ex);
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Kết nối đã được đóng");
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi khi đóng kết nối: " + ex);
        }
    }

    public static void main(String[] args) {
        getConnection();
    }
}

    
