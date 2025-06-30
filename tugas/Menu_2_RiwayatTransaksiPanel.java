package com.dikdik.coba_login_warna;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.*;

public class Menu_2_RiwayatTransaksiPanel extends JPanel {
    private JTable tabelRiwayat;
    private DefaultTableModel tabelModel;
    private JScrollPane scroll;
    private JButton tombolRefresh;
    private JLabel lblMenuTitle;

    public Menu_2_RiwayatTransaksiPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 248, 255)); 
        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setBackground(new Color(0, 0, 102));
        panelHeader.setPreferredSize(new Dimension(800, 50));

        lblMenuTitle = new JLabel("Riwayat Transaksi", SwingConstants.LEFT);
        lblMenuTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblMenuTitle.setForeground(Color.WHITE);
        lblMenuTitle.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        
        tombolRefresh = new JButton("Refresh");
        tombolRefresh.setFont(new Font("Arial", Font.PLAIN, 12));
        tombolRefresh.setBackground(new Color(100, 149, 237));
        tombolRefresh.setForeground(Color.WHITE);
        tombolRefresh.setFocusPainted(false);
        tombolRefresh.setPreferredSize(new Dimension(90, 30));
        tombolRefresh.addActionListener(e -> refreshData());
        
        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panelButton.setBackground(new Color(0, 0, 102));
        panelButton.add(tombolRefresh);
        
        panelHeader.add(lblMenuTitle, BorderLayout.WEST);
        panelHeader.add(panelButton, BorderLayout.EAST);
        add(panelHeader, BorderLayout.NORTH);

        String[] kolom = {"ID Pelanggan", "Nama Pelanggan", "Alamat", "No. Telp", "Layanan", "Berat (Kg)", "Total Harga"};
        tabelModel = new DefaultTableModel(kolom, 0);
        tabelRiwayat = new JTable(tabelModel);
        
        // ###### menambahkan gaya pada heading tabel
        tabelRiwayat.setFont(new Font("Arial", Font.PLAIN, 12));
        tabelRiwayat.setRowHeight(30);
        tabelRiwayat.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tabelRiwayat.getTableHeader().setBackground(new Color(100, 149, 237)); // Warna header tabel
        tabelRiwayat.getTableHeader().setForeground(Color.WHITE);
        
        // Mengatur ukuran kolom
        TableColumnModel columnModel = tabelRiwayat.getColumnModel();
        columnModel.getColumn(0).setMinWidth(30);
        columnModel.getColumn(0).setMaxWidth(80);
        columnModel.getColumn(1).setMinWidth(80);
        columnModel.getColumn(1).setMaxWidth(150);
        columnModel.getColumn(2).setMinWidth(100);
        columnModel.getColumn(2).setMaxWidth(1200);
        columnModel.getColumn(3).setMinWidth(80);
        columnModel.getColumn(3).setMaxWidth(100);
        columnModel.getColumn(4).setMinWidth(50);
        columnModel.getColumn(4).setMaxWidth(100);
        columnModel.getColumn(5).setMinWidth(30);
        columnModel.getColumn(5).setMaxWidth(80);
        columnModel.getColumn(6).setMinWidth(100);
        columnModel.getColumn(6).setMaxWidth(100);
        
        scroll = new JScrollPane(tabelRiwayat);
        add(scroll, BorderLayout.CENTER);
        
        LoadData();
    }

    public void refreshData() {
        tabelModel.setRowCount(0); 
        LoadData();
    }

    private void LoadData() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/laundry_login_warna", "root", "");
            String query = "SELECT * FROM transaksi";
            PreparedStatement Perintah = conn.prepareStatement(query);
            ResultSet Hasil = Perintah.executeQuery();
            
            while (Hasil.next()) {
                String idPelanggan = Hasil.getString("id_pelanggan");
                String namaPelanggan = Hasil.getString("nama_pelanggan");
                String alamat = Hasil.getString("alamat_pelanggan");
                String noTelp = Hasil.getString("no_telp");
                String layanan = Hasil.getString("layanan_yang_dipilih");
                String berat = Hasil.getString("berat_cucian");
                String totalHarga = Hasil.getString("total_harga");
                
                tabelModel.addRow(new Object[]{idPelanggan, namaPelanggan, alamat, noTelp, layanan, berat, totalHarga});
            }
            
            Hasil.close();
            Perintah.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil data: " + e.getMessage());
        }
    }
}
