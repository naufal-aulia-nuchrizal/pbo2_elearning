package com.dikdik.coba_login_warna;

import java.util.List;
import javax.swing.JComboBox;

class LayananManager {
    public static void isiComboBoxLayanan(JComboBox<String> PilihanLayanan) {
        List<String> layananList = Menu_3_HargaLayananManager.getDaftarLayanan();
        for (String layanan : layananList) {
            PilihanLayanan.addItem(layanan);
        }
    }
}

