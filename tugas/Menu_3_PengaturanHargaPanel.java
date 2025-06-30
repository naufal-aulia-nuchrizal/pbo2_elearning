package com.dikdik.coba_login_warna;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

// ========================== KELAS UTAMA PANEL ==========================
public class Menu_3_PengaturanHargaPanel extends JPanel {
    private PanelJudul PanelJudul;
    private FormPengaturanHarga formPanel;

    public Menu_3_PengaturanHargaPanel() {
        setLayout(new BorderLayout());

        // Tambahkan Header
        PanelJudul = new PanelJudul("Pengaturan Harga");
        add(PanelJudul, BorderLayout.NORTH);
        

        // Tambahkan Form Pengaturan Harga
        formPanel = new FormPengaturanHarga();
        add(formPanel, BorderLayout.CENTER);
    }
}

//###### KELAS HEADER PANEL ######
class PanelJudul extends JPanel {
    private JLabel LabelJudul;

    public PanelJudul(String teks) {
        setBackground(new Color(0, 0, 102)); 
        setPreferredSize(new Dimension(getWidth(), 50)); 
        setLayout(new BorderLayout());

        LabelJudul = new JLabel(teks);
        LabelJudul.setFont(new Font("Arial", Font.BOLD, 18));
        LabelJudul.setForeground(Color.WHITE);
        LabelJudul.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0)); 
        add(LabelJudul, BorderLayout.WEST);
    }

    public void KustomJudul(Color Warna_latar, Font font) {
        setBackground(Warna_latar);
        LabelJudul.setFont(font);
    }
}
// ========================== KELAS FORM PENGATURAN HARGA ==========================
class FormPengaturanHarga extends JPanel {
    private JTextField fieldNamaLayanan, fieldHargaBaru, fieldHargaLayanan;
    private JComboBox<String> cmbLayanan;
    private JButton btnTambah, btnUbah;

    public FormPengaturanHarga() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints style = new GridBagConstraints();
        style.insets = new Insets(15, 6, 15, 6);
        style.fill = GridBagConstraints.HORIZONTAL;

        style.gridx = 0; style.gridy = 0; style.gridwidth = 2;
        JLabel lblTambahLayanan = new JLabel("Menu Tambah Layanan");
        lblTambahLayanan.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTambahLayanan, style);

        style.gridy = 1; style.gridwidth = 1; style.weightx = 0.3;
        add(new JLabel("Nama Layanan:"), style);

        style.gridx = 1; style.weightx = 0.7;
        fieldNamaLayanan = new JTextField(15);
        add(fieldNamaLayanan, style);

        style.gridx = 0; style.gridy = 2;
        add(new JLabel("Harga Layanan:"), style);

        style.gridx = 1;
        fieldHargaLayanan = new JTextField(15);
        add(fieldHargaLayanan, style);

        style.gridx = 1; style.gridy = 3;
        btnTambah = new CustomButton("Tambah Layanan");
        add(btnTambah, style);

        style.gridx = 0; style.gridy = 4; style.gridwidth = 2;
        JLabel lblUbahHarga = new JLabel("Menu Ubah Harga");
        lblUbahHarga.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblUbahHarga, style);

        style.gridy = 5; style.gridwidth = 1;
        add(new JLabel("Pilih Layanan:"), style);

        style.gridx = 1;
        cmbLayanan = new JComboBox<>(Menu_3_HargaLayananManager.getDaftarLayanan().toArray(new String[0]));
        add(cmbLayanan, style);

        style.gridx = 0; style.gridy = 6;
        add(new JLabel("Harga Baru:"), style);

        style.gridx = 1;
        fieldHargaBaru = new JTextField(15);
        add(fieldHargaBaru, style);

        style.gridx = 1; style.gridy = 7;
        btnUbah = new CustomButton("Ubah Harga");
        add(btnUbah, style);

        btnTambah.addActionListener(new TambahLayananListener());
        btnUbah.addActionListener(new UbahHargaListener());
    }
    
    private class TambahLayananListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String namaLayanan = fieldNamaLayanan.getText();
                int hargaLayanan = Integer.parseInt(fieldHargaLayanan.getText());
                Menu_3_HargaLayananManager.tambahLayanan(namaLayanan, hargaLayanan);
                JOptionPane.showMessageDialog(null, "Layanan berhasil ditambahkan!");
                cmbLayanan.addItem(namaLayanan);
            } catch (SQLException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private class UbahHargaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String namaLayanan = (String) cmbLayanan.getSelectedItem();
                int hargaBaru = Integer.parseInt(fieldHargaBaru.getText());
                Menu_3_HargaLayananManager.ubahHargaLayanan(namaLayanan, hargaBaru);
                JOptionPane.showMessageDialog(null, "Harga layanan berhasil diperbarui!");
            } catch (SQLException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

//###### KELAS CUSTOM BUTTON ######
class CustomButton extends JButton {
    public CustomButton(String text) {
        super(text);
        setBackground(new Color(50, 205, 50));
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 14));
        setFocusPainted(false);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }
}