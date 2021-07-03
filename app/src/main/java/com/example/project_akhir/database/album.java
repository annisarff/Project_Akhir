package com.example.project_akhir.database;

import java.io.Serializable;

public class album implements Serializable {
    String kode;
    String nama;
    String harga;

    public album() {
    }

    public String getKode() {
        return kode;
    }
    public album(String nm, String hrg) {
        this.nama = nama;
        this.harga = hrg;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
