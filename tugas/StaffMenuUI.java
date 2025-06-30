package com.dikdik.coba_login_warna;

import javax.swing.*;
import java.awt.*;

public class StaffMenuUI extends JFrame {
    private JPanel panelKiri, panelKanan;
    private JButton Transaksi, RiwayatTransaksi, Logout;
    private JLabel lblSelamatDatang, lblELaundry;
    private CardLayout cardLayout;
    private JPanel panelKonten;
    private String username;

    // Warna yang bisa dikustomisasi
    private Color warnaPanelKiri = new Color(236, 236, 236); // Biru
    private Color warnaPanelAtas = new Color(0, 0, 102);  // Biru lebih gelap
    private Color warnaMenuNormal = new Color(220, 220, 220); // Biru muda
    private Color warnaMenuAktif = new Color(0, 0, 102); // Putih
    private Color warnaLogout = Color.RED; // Merah

    // Font yang bisa dikustomisasi
    private Font fontJudul = new Font("Arial", Font.BOLD, 14);
    private Font fontMenu = new Font("Arial", Font.BOLD, 12);
    private Font fontELaundry = new Font("Arial", Font.BOLD, 16);

    public StaffMenuUI(String username) {
        this.username = username;

        setTitle("Admin - E-LAUNDRY");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel Kiri (220 x 600)
        panelKiri = new JPanel();
        panelKiri.setPreferredSize(new Dimension(220, 600));
        panelKiri.setBackground(warnaPanelKiri);
        panelKiri.setLayout(new BorderLayout());

        // Panel Atas Kiri
        JPanel panelAtasKiri = new JPanel(new BorderLayout());
        panelAtasKiri.setBackground(warnaPanelAtas);
        panelAtasKiri.setPreferredSize(new Dimension(220, 50));

        lblSelamatDatang = new JLabel("Selamat Datang, " + username, SwingConstants.CENTER);
        lblSelamatDatang.setForeground(Color.WHITE);
        lblSelamatDatang.setFont(fontJudul);
        panelAtasKiri.add(lblSelamatDatang, BorderLayout.CENTER);

        JPanel panelMenu = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); 
        panelMenu.setBackground(warnaPanelKiri);

        Transaksi = buatTombolMenu("Transaksi");
        RiwayatTransaksi = buatTombolMenu("Riwayat Transaksi");
//        btnPengaturanHarga = buatTombolMenu("Pengaturan Harga");
//        btnPengaturanAkun = buatTombolMenu("Pengaturan Akun");

        panelMenu.add(Transaksi);
        panelMenu.add(RiwayatTransaksi);
//        panelMenu.add(btnPengaturanHarga);
//        panelMenu.add(btnPengaturanAkun);

        // Panel Bawah Kiri (Logout & E-LAUNDRY)
        JPanel panelBawahKiri = new JPanel(new BorderLayout());
        panelBawahKiri.setBackground(warnaPanelAtas);
        panelBawahKiri.setPreferredSize(new Dimension(220, 100));

        Logout = new JButton("Log Out");
        Logout.setFont(fontMenu);
        Logout.setBackground(warnaLogout);
        Logout.setForeground(Color.WHITE);
        Logout.setFocusPainted(false);
        Logout.setBorderPainted(false);
        Logout.setPreferredSize(new Dimension(220, 40));

        lblELaundry = new JLabel("E-LAUNDRY", SwingConstants.CENTER);
        lblELaundry.setForeground(Color.WHITE);
        lblELaundry.setFont(fontELaundry);

        panelBawahKiri.add(Logout, BorderLayout.NORTH);
        panelBawahKiri.add(lblELaundry, BorderLayout.CENTER);

        panelKiri.add(panelAtasKiri, BorderLayout.NORTH);
        panelKiri.add(panelMenu, BorderLayout.CENTER);
        panelKiri.add(panelBawahKiri, BorderLayout.SOUTH);

        // Panel Kanan (Konten)
        panelKanan = new JPanel(new BorderLayout());
        panelKanan.setBackground(Color.WHITE);

        cardLayout = new CardLayout();
        panelKonten = new JPanel(cardLayout);

        panelKonten.add(new Menu_1_TransaksiPanel(), "Transaksi");
        panelKonten.add(new Menu_2_RiwayatTransaksiPanel(), "Riwayat Transaksi");
//        panelKonten.add(new Menu_3_PengaturanHargaPanel(), "Pengaturan Harga");
//        panelKonten.add(new Menu_4_PengaturanAkun(), "Pengaturan Akun");

        panelKanan.add(panelKonten, BorderLayout.CENTER);

// Menambahkan panel ke frame
        add(panelKiri, BorderLayout.WEST);
        add(panelKanan, BorderLayout.CENTER);

        Transaksi.addActionListener(e -> gantiPanel("Transaksi", Transaksi));
        RiwayatTransaksi.addActionListener(e -> gantiPanel("Riwayat Transaksi", RiwayatTransaksi));
//        btnPengaturanHarga.addActionListener(e -> gantiPanel("Pengaturan Harga", btnPengaturanHarga));
//        btnPengaturanAkun.addActionListener(e -> gantiPanel("Pengaturan Akun", btnPengaturanAkun));

        Logout.addActionListener(e -> {
            int konfirmasi = JOptionPane.showConfirmDialog(this,"Apa Anda yakin untuk Log Out?","Konfirmasi Log Out",JOptionPane.YES_NO_OPTION);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                dispose();
                new LoginLaundry(); 
            }
        });
        setVisible(true);
    }

    private JButton buatTombolMenu(String teks) {
        JButton tombol = new JButton(teks);
        tombol.setFont(fontMenu);
        tombol.setBackground(warnaMenuNormal);
        tombol.setForeground(Color.BLACK);
        tombol.setFocusPainted(false);
        tombol.setBorderPainted(false);
        tombol.setPreferredSize(new Dimension(200, 40));
        return tombol;
    }

    private void gantiPanel(String namaPanel, JButton tombolDipilih) {
        cardLayout.show(panelKonten, namaPanel);

        Transaksi.setBackground(warnaMenuNormal);
        RiwayatTransaksi.setBackground(warnaMenuNormal);
//        btnPengaturanHarga.setBackground(warnaMenuNormal);
//        btnPengaturanAkun.setBackground(warnaMenuNormal);

        Transaksi.setForeground(Color.BLACK);
        RiwayatTransaksi.setForeground(Color.BLACK);
//        btnPengaturanHarga.setForeground(Color.BLACK);
//        btnPengaturanAkun.setForeground(Color.BLACK);

        tombolDipilih.setBackground(warnaMenuAktif);
        tombolDipilih.setForeground(Color.WHITE);
    }
}