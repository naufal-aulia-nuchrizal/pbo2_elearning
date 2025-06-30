package com.dikdik.coba_login_warna;

abstract class HargaLayanan {
    protected abstract double hitungTotal(double berat, int hargaPerKg);
}


class Menu_1_HitungHarga extends HargaLayanan {
    @Override
    public double hitungTotal(double berat, int hargaPerKg) {
        return berat * hargaPerKg;
    }
}
