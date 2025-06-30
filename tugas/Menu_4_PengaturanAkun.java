package com.dikdik.coba_login_warna;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Abstract Class untuk Panel Pengaturan Akun
abstract class PanelPengaturanAkun extends JPanel {
    protected Color warnaPanelJudul = new Color(0, 0, 102);
    protected Font Judul = new Font("Arial", Font.BOLD, 18);

    public PanelPengaturanAkun() {
        setLayout(new BorderLayout());
    }

    protected JPanel buatHeader(String teks) {
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(warnaPanelJudul);
        panelHeader.setPreferredSize(new Dimension(0, 50));

        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 20, 14);
        panelHeader.setLayout(flowLayout);

        JLabel labelJUDUL = new JLabel(teks);
        labelJUDUL.setForeground(Color.WHITE);
        labelJUDUL.setFont(Judul);

        panelHeader.add(labelJUDUL);
        return panelHeader;
    }
}

// Class untuk Panel Pengaturan Akun
class Menu_4_PengaturanAkun extends PanelPengaturanAkun {
    private JTextField teksUsername, teksNama, teksUmur, teksNoTelpStaff, teksAlamat;
    private JPasswordField Password;
    private JComboBox<String> pilihanIjazah;
    private JButton tombolSimpan;

    public Menu_4_PengaturanAkun() {
        add(buatHeader("Pengaturan Akun"), BorderLayout.NORTH);
        add(buatForm(), BorderLayout.CENTER);
    }

    private JPanel buatForm() {
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(new Color(235, 235, 235)); 
        GridBagConstraints style = new GridBagConstraints();
        style.insets = new Insets(15, 10, 10, 10);
        style.anchor = GridBagConstraints.WEST;
        style.fill = GridBagConstraints.HORIZONTAL;
        style.weightx = 1;

        JLabel lblUsername = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");
        JLabel lblNama = new JLabel("Nama Staff:");
        JLabel lblUmur = new JLabel("Umur:");
        JLabel lblNoTelp = new JLabel("No Telepon:");
        JLabel lblAlamat = new JLabel("Alamat:");
        JLabel lblIjazah = new JLabel("Ijazah Terakhir:");

        teksUsername = new JTextField(20);
        Password = new JPasswordField(20);
        teksNama = new JTextField(20);
        teksUmur = new JTextField(5);
        teksNoTelpStaff = new JTextField(15);
        teksAlamat = new JTextField(25);
        pilihanIjazah = new JComboBox<>(new String[]{"SD/Sederajat", "SMP/Sederajat", "SMA/Sederajat", "D3", "S1"});

        tombolSimpan = new JButton("Simpan");
        modifikasiButton(tombolSimpan);

        style.gridx = 0; style.gridy = 0; panelForm.add(lblUsername, style);
        style.gridx = 1; style.gridy = 0; panelForm.add(teksUsername, style);
        style.gridx = 0; style.gridy = 1; panelForm.add(lblPassword, style);
        style.gridx = 1; style.gridy = 1; panelForm.add(Password, style);
        style.gridx = 0; style.gridy = 2; panelForm.add(lblNama, style);
        style.gridx = 1; style.gridy = 2; panelForm.add(teksNama, style);
        style.gridx = 0; style.gridy = 3; panelForm.add(lblUmur, style);
        style.gridx = 1; style.gridy = 3; panelForm.add(teksUmur, style);
        style.gridx = 0; style.gridy = 4; panelForm.add(lblNoTelp, style);
        style.gridx = 1; style.gridy = 4; panelForm.add(teksNoTelpStaff, style);
        style.gridx = 0; style.gridy = 5; panelForm.add(lblAlamat, style);
        style.gridx = 1; style.gridy = 5; panelForm.add(teksAlamat, style);
        style.gridx = 0; style.gridy = 6; panelForm.add(lblIjazah, style);
        style.gridx = 1; style.gridy = 6; panelForm.add(pilihanIjazah, style);
        style.gridx = 1; style.gridy = 7; panelForm.add(tombolSimpan, style);

        tombolSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PengelolaAkun.simpanData(teksUsername.getText(),new String(Password.getPassword()),teksNama.getText(),teksUmur.getText(),teksNoTelpStaff.getText(),teksAlamat.getText(),(String) pilihanIjazah.getSelectedItem());
            }
        });
        return panelForm;
    }

    private void modifikasiButton(JButton button) {
        button.setBackground(new Color(70, 130, 180)); 
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 1)); 
        button.setFont(new Font("Arial", Font.BOLD, 14));
    }
}

class PengelolaAkun {
    public static void simpanData(String username, String password, String nama, String umur, String noTelp, String alamat, String ijazah) {
        try {
            int umurInt = Integer.parseInt(umur);

            try (Connection conn = KoneksiDatabase.getKoneksi()) {
                String sqlStaff = "INSERT INTO staff (username, password) VALUES (?, ?)";
                PreparedStatement stmtStaff = conn.prepareStatement(sqlStaff, PreparedStatement.RETURN_GENERATED_KEYS);
                stmtStaff.setString(1, username);
                stmtStaff.setString(2, password);
                stmtStaff.executeUpdate();

                String sqlIdentitas = "INSERT INTO identitas_staff (Nama_staff, Umur_staff, No_telp_staff, Alamat_staff, Ijazah_terakhir) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmtIdentitas = conn.prepareStatement(sqlIdentitas);
                stmtIdentitas.setString(1, nama);
                stmtIdentitas.setInt(2, umurInt);
                stmtIdentitas.setString(3, noTelp);
                stmtIdentitas.setString(4, alamat);
                stmtIdentitas.setString(5, ijazah);
                stmtIdentitas.executeUpdate();

                JOptionPane.showMessageDialog(null, "Data berhasil disimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Umur harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
