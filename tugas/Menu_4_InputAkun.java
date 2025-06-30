
package com.dikdik.coba_login_warna;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Menu_4_InputAkun {
    public final void simpanData(String username, String password, String nama, String umur, String noTelp, String alamat, String ijazah) {
        try {
            int umurInt = Integer.parseInt(umur);

            try (Connection conn = KoneksiDatabase.getKoneksi()) {
                String sql = "INSERT INTO staff (username, password) VALUES (?, ?)";
                PreparedStatement PerintahStaff = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                PerintahStaff.setString(1, username);
                PerintahStaff.setString(2, password);
                PerintahStaff.executeUpdate();

                String sqlIdentitas = "INSERT INTO identitas_staff (Nama_staff, Umur_staff, No_telp_staff, Alamat_staff, Ijazah_terakhir) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement PerintahIdentitas = conn.prepareStatement(sqlIdentitas);
                PerintahIdentitas.setString(1, nama);
                PerintahIdentitas.setInt(2, umurInt);
                PerintahIdentitas.setString(3, noTelp);
                PerintahIdentitas.setString(4, alamat);
                PerintahIdentitas.setString(5, ijazah);
                PerintahIdentitas.executeUpdate();

                JOptionPane.showMessageDialog(null, "Data berhasil disimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Maff, Umur harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}