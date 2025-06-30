package com.dikdik.coba_login_warna;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class Menu_3_UbahHarga extends JPanel {

    private JComboBox<String> pilihLayanan;
    private JTextField fieldHargaBaru;

    public Menu_3_UbahHarga() {
        setLayout(new FlowLayout());

        List<String> layananList = Menu_3_HargaLayananManager.getDaftarLayanan();
        pilihLayanan = new JComboBox<>(layananList.toArray(new String[0]));
        fieldHargaBaru = new JTextField(10);

        JButton btnUbahHarga = new JButton("Ubah Harga");
        btnUbahHarga.addActionListener(e -> {
            String layanan = (String) pilihLayanan.getSelectedItem();
            String hargaBaruStr = fieldHargaBaru.getText();
            try {
                int hargaBaru = Integer.parseInt(hargaBaruStr);
                Menu_3_HargaLayananManager.ubahHargaLayanan(layanan, hargaBaru);
                
                JOptionPane.showMessageDialog(this, "Harga layanan berhasil diubah");
                
            } catch (SQLException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat mengubah harga");
            }
        });
        add(new JLabel("Pilih Layanan:"));
        add(pilihLayanan);
        add(new JLabel("Harga Baru:"));
        add(fieldHargaBaru);
        add(btnUbahHarga);
    }
}
