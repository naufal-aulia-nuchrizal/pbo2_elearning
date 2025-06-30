package com.dikdik.coba_login_warna;

import java.sql.*;
import javax.swing.JOptionPane;

class Menu_1_TransaksiManager {
    public static boolean simpanTransaksi(String nama, String alamat, String noTelp, String namaLayanan, double berat, double totalHarga) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/laundry_login_warna", "root", "");
            String sql = "INSERT INTO transaksi (nama_pelanggan, alamat_pelanggan, no_telp, layanan_yang_dipilih, berat_cucian, total_harga) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement Perintah = conn.prepareStatement(sql);
            Perintah.setString(1, nama);
            Perintah.setString(2, alamat);
            Perintah.setString(3, noTelp);
            Perintah.setString(4, namaLayanan);
            Perintah.setDouble(5, berat);
            Perintah.setDouble(6, totalHarga);

            int result = Perintah.executeUpdate();
            Perintah.close();
            conn.close();

            return result > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan transaksi: " + e.getMessage());
            return false;
        }
    }
}

