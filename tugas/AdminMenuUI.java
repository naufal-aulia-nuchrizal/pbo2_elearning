package com.dikdik.coba_login_warna;

import javax.swing.*;
import java.awt.*;

public class AdminMenuUI extends JFrame {
    private JPanel panelKiri, panelKanan;
    private JButton btnTransaksi, btnRiwayatTransaksi, btnPengaturanHarga, btnPengaturanAkun, btnLogout;
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

    public AdminMenuUI(String username) {
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

        // Panel Atas Kiri (Selamat Datang)
        JPanel panelAtasKiri = new JPanel(new BorderLayout());
        panelAtasKiri.setBackground(warnaPanelAtas);
        panelAtasKiri.setPreferredSize(new Dimension(220, 50));

        lblSelamatDatang = new JLabel("Selamat Datang, " + username, SwingConstants.CENTER);
        lblSelamatDatang.setForeground(Color.WHITE);
        lblSelamatDatang.setFont(fontJudul);
        panelAtasKiri.add(lblSelamatDatang, BorderLayout.CENTER);

        // Panel Menu (Menggunakan BorderLayout agar menu rata kiri)
        JPanel panelMenu = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); 
        panelMenu.setBackground(warnaPanelKiri);

        btnTransaksi = buatTombolMenu("Transaksi");
        btnRiwayatTransaksi = buatTombolMenu("Riwayat Transaksi");
        btnPengaturanHarga = buatTombolMenu("Pengaturan Harga");
        btnPengaturanAkun = buatTombolMenu("Pengaturan Akun");

        panelMenu.add(btnTransaksi);
        panelMenu.add(btnRiwayatTransaksi);
        panelMenu.add(btnPengaturanHarga);
        panelMenu.add(btnPengaturanAkun);

        // Panel Bawah Kiri (Logout & E-LAUNDRY)
        JPanel panelBawahKiri = new JPanel(new BorderLayout());
        panelBawahKiri.setBackground(warnaPanelAtas);
        panelBawahKiri.setPreferredSize(new Dimension(220, 100));

        btnLogout = new JButton("Log Out");
        btnLogout.setFont(fontMenu);
        btnLogout.setBackground(warnaLogout);
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFocusPainted(false);
        btnLogout.setBorderPainted(false);
        btnLogout.setPreferredSize(new Dimension(220, 40));

        lblELaundry = new JLabel("E-LAUNDRY", SwingConstants.CENTER);
        lblELaundry.setForeground(Color.WHITE);
        lblELaundry.setFont(fontELaundry);

        panelBawahKiri.add(btnLogout, BorderLayout.NORTH);
        panelBawahKiri.add(lblELaundry, BorderLayout.CENTER);

        // Menambahkan semua bagian ke panel kiri
        panelKiri.add(panelAtasKiri, BorderLayout.NORTH);
        panelKiri.add(panelMenu, BorderLayout.CENTER);
        panelKiri.add(panelBawahKiri, BorderLayout.SOUTH);

        // Panel Kanan (Konten)
        panelKanan = new JPanel(new BorderLayout());
        panelKanan.setBackground(Color.WHITE);

        cardLayout = new CardLayout();
        panelKonten = new JPanel(cardLayout);

        // Menambahkan panel konten untuk setiap menu
        panelKonten.add(new Menu_1_TransaksiPanel(), "Transaksi");
        panelKonten.add(new Menu_2_RiwayatTransaksiPanel(), "Riwayat Transaksi");
        panelKonten.add(new Menu_3_PengaturanHargaPanel(), "Pengaturan Harga");
        panelKonten.add(new Menu_4_PengaturanAkun(), "Pengaturan Akun");

        panelKanan.add(panelKonten, BorderLayout.CENTER);

        // Menambahkan panel ke frame
        add(panelKiri, BorderLayout.WEST);
        add(panelKanan, BorderLayout.CENTER);

        // Menambahkan aksi tombol
        btnTransaksi.addActionListener(e -> gantiPanel("Transaksi", btnTransaksi));
        btnRiwayatTransaksi.addActionListener(e -> gantiPanel("Riwayat Transaksi", btnRiwayatTransaksi));
        btnPengaturanHarga.addActionListener(e -> gantiPanel("Pengaturan Harga", btnPengaturanHarga));
        btnPengaturanAkun.addActionListener(e -> gantiPanel("Pengaturan Akun", btnPengaturanAkun));

        // Aksi tombol logout
        btnLogout.addActionListener(e -> {
            int konfirmasi = JOptionPane.showConfirmDialog(this,"Apa Anda yakin untuk Log Out?","Konfirmasi Log Out",JOptionPane.YES_NO_OPTION);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                dispose();
                new LoginLaundry(); // Kembali ke login
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
        tombol.setPreferredSize(new Dimension(200, 40)); // Ukuran lebih kecil
        return tombol;
    }

    private void gantiPanel(String namaPanel, JButton tombolDipilih) {
        cardLayout.show(panelKonten, namaPanel);

        // Mengembalikan warna semua tombol ke biru
        btnTransaksi.setBackground(warnaMenuNormal);
        btnRiwayatTransaksi.setBackground(warnaMenuNormal);
        btnPengaturanHarga.setBackground(warnaMenuNormal);
        btnPengaturanAkun.setBackground(warnaMenuNormal);

        btnTransaksi.setForeground(Color.BLACK);
        btnRiwayatTransaksi.setForeground(Color.BLACK);
        btnPengaturanHarga.setForeground(Color.BLACK);
        btnPengaturanAkun.setForeground(Color.BLACK);

        // Mengubah warna tombol yang dipilih menjadi putih
        tombolDipilih.setBackground(warnaMenuAktif);
        tombolDipilih.setForeground(Color.WHITE);
    }
}