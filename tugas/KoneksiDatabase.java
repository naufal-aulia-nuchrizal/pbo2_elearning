package com.dikdik.coba_login_warna;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KoneksiDatabase {
    private static Connection koneksi;

    public static Connection getKoneksi() {
        try {
            if (koneksi == null || koneksi.isClosed()) {
                String url = "jdbc:mysql://localhost:3306/laundry_login_warna";
                String user = "root";
                String password = "";

                koneksi = DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return koneksi;
    }
}
