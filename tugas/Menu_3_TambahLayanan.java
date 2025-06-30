package com.dikdik.coba_login_warna;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Menu_3_TambahLayanan extends JPanel {

    private JTextField fieldNamaLayanan, fieldHargaLayanan;

    public Menu_3_TambahLayanan() {
        setLayout(new FlowLayout());

        JLabel labelNamaLayanan = new JLabel("Nama Layanan:");
        fieldNamaLayanan = new JTextField(15);
        JLabel lblHargaLayanan = new JLabel("Harga Layanan:");
        fieldHargaLayanan = new JTextField(10);

        JButton btnTambahLayanan = new JButton("Tambah Layanan");
        btnTambahLayanan.addActionListener(e -> {
            String namaLayanan = fieldNamaLayanan.getText();
            String hargaLayananStr = fieldHargaLayanan.getText();
            try {
                int hargaLayanan = Integer.parseInt(hargaLayananStr);
                Menu_3_HargaLayananManager.tambahLayanan(namaLayanan, hargaLayanan);
                JOptionPane.showMessageDialog(this, "Layanan berhasil ditambahkan");
            } catch (SQLException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menambah layanan");
            }
        });

        add(labelNamaLayanan);
        add(fieldNamaLayanan);
        add(lblHargaLayanan);
        add(fieldHargaLayanan);
        add(btnTambahLayanan);
    }
}
