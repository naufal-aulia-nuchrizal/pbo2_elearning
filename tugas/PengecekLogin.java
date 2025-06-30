package com.dikdik.coba_login_warna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PengecekLogin {
    public static boolean login(String Peran, String username, String password) {
        boolean isValid = false;
        try {
            Connection conn = KoneksiDatabase.getKoneksi();
            String query = "";

            if (Peran.equalsIgnoreCase("admin")) {
                query = "SELECT * FROM admin WHERE username = ? AND password = ?";
            } else if (Peran.equalsIgnoreCase("staff")) {
                query = "SELECT * FROM staff WHERE username = ? AND password = ?";
            }

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet Hasil = stmt.executeQuery();
            isValid = Hasil.next(); 

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }
}
