package com.dikdik.coba_login_warna;

import javax.swing.*;
import java.awt.*;

public class Menu_1_TransaksiPanel extends JPanel {

    // === KONFIGURASI KUSTOMISASI ===
    private final Color Warna_Latar_Panel = new Color(240, 248, 255); 
    private final Color Warna_Judul = new Color(0, 0, 102); 
    private final Color Warna_Font_Judul = Color.WHITE; 
    private final Font Font_Judul = new Font("Arial", Font.BOLD, 18); 

    private final Color BUTTON_HITUNG = new Color(50, 205, 50); 
    private final Color BUTTON_SIMPAN = new Color(30, 144, 255); 
    private final Color BUTTON_REFRESH = new Color(30, 144, 255); 
    private final Color BUTTON_TEXT_COLOR = Color.WHITE; 

    private static final int Jarak_Panel = 20; 
    private static final int Tinggi_Field = 30; 

    // === KOMPONEN UI ===
    private JTextField fieldNama, fieldAlamat, fieldNoTelp, fieldBeratCucian, FieldTotalHarga;
    private JComboBox<String> pilihanLayanan;
    private JButton tombolHitungTotal, tombolSimpan, tombolRefresh;
    private Menu_1_HitungHarga hitungHarga;

    public Menu_1_TransaksiPanel() {
        setLayout(new BorderLayout());
        setBackground(Warna_Latar_Panel);

// ###### HEADER PANEL ######
        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setBackground(Warna_Judul);
        panelHeader.setPreferredSize(new Dimension(800, 50));

        JLabel lblTitle = new JLabel("Form Transaksi Laundry", SwingConstants.LEFT);
        lblTitle.setFont(Font_Judul);
        lblTitle.setForeground(Warna_Font_Judul);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
        JPanel panelButtonHeader = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panelButtonHeader.setBackground(Warna_Judul);

        tombolRefresh = createButton("Refresh", BUTTON_REFRESH);
        tombolRefresh.setPreferredSize(new Dimension(100, 30));
        tombolRefresh.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); 
        tombolRefresh.setFocusPainted(false);

        panelButtonHeader.add(tombolRefresh);

        panelHeader.add(lblTitle, BorderLayout.WEST);
        panelHeader.add(panelButtonHeader, BorderLayout.EAST);
        add(panelHeader, BorderLayout.NORTH);

 
        
// ###### FORM PANEL ######
        JPanel panelForm = new JPanel(new GridLayout(7, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(Jarak_Panel, Jarak_Panel, Jarak_Panel, Jarak_Panel));

        fieldNama = createTextField();
        fieldAlamat = createTextField();
        fieldNoTelp = createTextField();
        fieldBeratCucian = createTextField();
        FieldTotalHarga = createTextField();
        FieldTotalHarga.setEditable(false);

        pilihanLayanan = new JComboBox<>();
        LayananManager.isiComboBoxLayanan(pilihanLayanan);

        panelForm.add(new JLabel("Nama Pelanggan:"));
        panelForm.add(fieldNama);
        panelForm.add(new JLabel("Alamat:"));
        panelForm.add(fieldAlamat);
        panelForm.add(new JLabel("No Telepon:"));
        panelForm.add(fieldNoTelp);
        panelForm.add(new JLabel("Layanan:"));
        panelForm.add(pilihanLayanan);
        panelForm.add(new JLabel("Berat Cucian (kg):"));
        panelForm.add(fieldBeratCucian);
        panelForm.add(new JLabel("Total Harga:"));
        panelForm.add(FieldTotalHarga);

        add(panelForm, BorderLayout.CENTER);

        
        
// ###### BUTTON PANEL ######
        JPanel Tombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        tombolHitungTotal = createButton("Hitung Total", BUTTON_HITUNG);
        tombolSimpan = createButton("Simpan Transaksi", BUTTON_SIMPAN);

        Tombol.add(tombolHitungTotal);
        Tombol.add(tombolSimpan);
        add(Tombol, BorderLayout.SOUTH);

        hitungHarga = new Menu_1_HitungHarga();

        tombolHitungTotal.addActionListener(e -> hitungTotalHarga());
        tombolSimpan.addActionListener(e -> simpanTransaksi());
        tombolRefresh.addActionListener(e -> refreshComboBoxLayanan());
    }

   
    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, Tinggi_Field));
        return textField;
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(BUTTON_TEXT_COLOR);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

// ###### PERHITUNGAN TOTAL HARGA ######
    private void hitungTotalHarga() {
        try {
            String namaLayanan = (String) pilihanLayanan.getSelectedItem();
            double berat = Double.parseDouble(fieldBeratCucian.getText());

            if (berat <= 0) {
                JOptionPane.showMessageDialog(this, "Berat cucian harus lebih dari 0!");
                return;
            }

            int hargaPerKg = Menu_3_HargaLayananManager.getHargaLayanan(namaLayanan);
            double totalHarga = hitungHarga.hitungTotal(berat, hargaPerKg);

            FieldTotalHarga.setText(String.valueOf(totalHarga));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan berat cucian dengan benar (angka).", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

// ###### SIMPAN TRANSAKSI KE DATABASE ######
    private void simpanTransaksi() {
        try {
            String nama = fieldNama.getText();
            String alamat = fieldAlamat.getText();
            String noTelp = fieldNoTelp.getText();
            String namaLayanan = (String) pilihanLayanan.getSelectedItem();
            double berat = Double.parseDouble(fieldBeratCucian.getText());
            double totalHarga = Double.parseDouble(FieldTotalHarga.getText());

            if (berat <= 0) {
                JOptionPane.showMessageDialog(this, "Berat cucian harus lebih dari 0!");
                return;
            }

            boolean Berhasil = Menu_1_TransaksiManager.simpanTransaksi(nama, alamat, noTelp, namaLayanan, berat, totalHarga);

            if (Berhasil) {
                JOptionPane.showMessageDialog(this, "Transaksi berhasil disimpan!");
            } else {
                JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menyimpan transaksi.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan berat cucian dengan benar (angka).", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshComboBoxLayanan() {
        pilihanLayanan.removeAllItems();
        LayananManager.isiComboBoxLayanan(pilihanLayanan);
        JOptionPane.showMessageDialog(this, "Data layanan diperbarui!");
    }
}
