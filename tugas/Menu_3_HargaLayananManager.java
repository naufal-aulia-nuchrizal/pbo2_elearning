package com.dikdik.coba_login_warna;

import java.sql.*;
import java.util.*;

public class Menu_3_HargaLayananManager {

    public static Connection getKoneksi() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/laundry_login_warna", "root", "");
    }

// Ambil data nama layanan dari database
    public static List<String> getDaftarLayanan() {
        List<String> layananList = new ArrayList<>();
        try (Connection conn = getKoneksi();
             Statement Perintah = conn.createStatement();
             ResultSet Hasil = Perintah.executeQuery("SELECT nama_layanan FROM harga_layanan")) {
            while (Hasil.next()) {
                layananList.add(Hasil.getString("nama_layanan"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return layananList;
    }

    // Ambil harga layanan berdasarkan nama_layanan dari database
    public static int getHargaLayanan(String layanan) {
        int harga = 0;
        try (Connection conn = getKoneksi();
             PreparedStatement Perintah = conn.prepareStatement("SELECT harga_layanan FROM harga_layanan WHERE nama_layanan = ?")) {
            Perintah.setString(1, layanan);
            ResultSet Hasil = Perintah.executeQuery();
            if (Hasil.next()) {
                harga = Hasil.getInt("harga_layanan");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return harga;
    }

    // Menambah layanan baru ke dalam database
    public static void tambahLayanan(String namaLayanan, int hargaLayanan) throws SQLException {
        String sql = "INSERT INTO harga_layanan (nama_layanan, harga_layanan) VALUES (?, ?)";
        try (Connection conn = getKoneksi();
             PreparedStatement Perintah = conn.prepareStatement(sql)) {
            Perintah.setString(1, namaLayanan);
            Perintah.setInt(2, hargaLayanan);
            Perintah.executeUpdate();
        }
    }

    // Mengubah harga layanan yang sudah ada
    public static void ubahHargaLayanan(String namaLayanan, int hargaLayanan) throws SQLException {
        String sql = "UPDATE harga_layanan SET harga_layanan = ? WHERE nama_layanan = ?";
        try (Connection conn = getKoneksi();
             PreparedStatement Perintah = conn.prepareStatement(sql)) {
            Perintah.setInt(1, hargaLayanan);
            Perintah.setString(2, namaLayanan);
            Perintah.executeUpdate();
        }
    }
}

