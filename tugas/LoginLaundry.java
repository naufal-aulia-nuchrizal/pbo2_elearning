package com.dikdik.coba_login_warna;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginLaundry extends JFrame {
    private JTextField adminUsernameField, staffUsernameField;
    private JPasswordField adminPasswordField, staffPasswordField;

    public LoginLaundry() {
        setTitle("E-Laundry Login");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 2));

        // PANEL ADMIN
        JPanel adminPanel = new JPanel(new GridBagLayout());
        adminPanel.setBackground(new Color(0, 0, 102));
        GridBagConstraints style = new GridBagConstraints();
        style.insets = new Insets(10, 5, 5, 5);
        style.fill = GridBagConstraints.HORIZONTAL;
        style.gridy = 0;
        JLabel adminLabel = new JLabel("Admin", SwingConstants.CENTER);
        adminLabel.setFont(new Font("Arial", Font.BOLD, 18));
        adminLabel.setForeground(Color.WHITE);
        adminPanel.add(adminLabel, style);

        style.gridy++;
        JLabel adminUsernameLabel = new JLabel("Username");
        adminUsernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        adminUsernameLabel.setForeground(Color.WHITE);
        adminPanel.add(adminUsernameLabel, style);

        style.gridy++;
        adminUsernameField = new JTextField(15);
        adminUsernameField.setBackground(Color.WHITE); 
        adminUsernameField.setBorder(BorderFactory.createEmptyBorder()); 
        adminUsernameField.setPreferredSize(new Dimension(250, 25));
        adminPanel.add(adminUsernameField, style);

        style.gridy++;
        JLabel adminPasswordLabel = new JLabel("Password");
        adminPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        adminPasswordLabel.setForeground(Color.WHITE);
        adminPanel.add(adminPasswordLabel, style);

        style.gridy++;
        adminPasswordField = new JPasswordField(15);
        adminPasswordField.setBackground(Color.WHITE); 
        adminPasswordField.setBorder(BorderFactory.createEmptyBorder()); 
        adminPasswordField.setPreferredSize(new Dimension(250, 25)); 
        adminPanel.add(adminPasswordField, style);

        style.gridy++;
        style.insets = new Insets(10, 5, 5, 5); 
        JButton adminLoginButton = new JButton("Login");
        adminLoginButton.setBackground(Color.WHITE); 
        adminLoginButton.setForeground(Color.BLACK); 
        adminLoginButton.setFocusPainted(false);
        adminLoginButton.setBorder(BorderFactory.createEmptyBorder()); 
        adminLoginButton.setPreferredSize(new Dimension(50, 25));
        adminPanel.add(adminLoginButton, style);

        add(adminPanel);

        // PANEL STAFF
        JPanel staffPanel = new JPanel(new GridBagLayout());
        staffPanel.setBackground(Color.WHITE);
        JLabel staffLabel = new JLabel("Staff", SwingConstants.CENTER);
        staffLabel.setFont(new Font("Arial", Font.BOLD, 18));
        staffLabel.setForeground(new Color(0, 0, 102));
        style.gridy = 0;
        staffPanel.add(staffLabel, style);

        style.gridy++;
        JLabel staffUsernameLabel = new JLabel("Username");
        staffUsernameLabel.setFont(new Font("Arial", Font.PLAIN, 14)); 
        staffPanel.add(staffUsernameLabel, style);

        style.gridy++;
        staffUsernameField = new JTextField(15);
        staffUsernameField.setBackground(new Color(240, 240, 240)); 
        staffUsernameField.setBorder(BorderFactory.createEmptyBorder()); 
        staffUsernameField.setPreferredSize(new Dimension(250, 20));
        staffPanel.add(staffUsernameField, style);

        style.gridy++;
        JLabel staffPasswordLabel = new JLabel("Password");
        staffPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 14)); 
        staffPanel.add(staffPasswordLabel, style);

        style.gridy++;
        staffPasswordField = new JPasswordField(15);
        staffPasswordField.setBackground(new Color(240, 240, 240)); 
        staffPasswordField.setBorder(BorderFactory.createEmptyBorder()); 
        staffPasswordField.setPreferredSize(new Dimension(250, 25)); 
        staffPanel.add(staffPasswordField, style);

        style.gridy++;
        style.insets = new Insets(15, 5, 5, 5); 
        JButton staffLoginButton = new JButton("Login");
        staffLoginButton.setBackground(new Color(0, 0, 102)); 
        staffLoginButton.setForeground(Color.WHITE); 
        staffLoginButton.setFocusPainted(false);
        staffLoginButton.setBorder(BorderFactory.createEmptyBorder()); 
        staffLoginButton.setPreferredSize(new Dimension(50, 25));
        staffPanel.add(staffLoginButton, style);

        add(staffPanel);

adminLoginButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = adminUsernameField.getText();
        String password = new String(adminPasswordField.getPassword());

        if (PengecekLogin.login("admin", username, password)) {
            JOptionPane.showMessageDialog(null, "Selamat datang " + username);
            new AdminMenuUI(username);
            dispose();  
            
        } else {
            JOptionPane.showMessageDialog(null, "Username atau Password Salah");
        }
    }
});

staffLoginButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = staffUsernameField.getText();
        String password = new String(staffPasswordField.getPassword());

        if (PengecekLogin.login("staff", username, password)) {
            JOptionPane.showMessageDialog(null, "Selamat datang " + username);
            new StaffMenuUI(username);
        } else {
            JOptionPane.showMessageDialog(null, "Username atau Password Salah");
        }
    }
});
        setVisible(true);
    }
}
