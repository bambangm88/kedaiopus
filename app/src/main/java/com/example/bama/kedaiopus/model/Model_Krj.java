package com.example.bama.kedaiopus.model;

public class Model_Krj {

    private String id_psn ;
    private String nama_pesan;
    private String nama_makanan;
    private String harga;
    private String url_img;




    public Model_Krj (String id_psn, String nama_pesan, String nama_makanan, String harga, String url_img) {

        this.id_psn = id_psn;
        this.nama_pesan= nama_pesan;
        this.nama_makanan = nama_makanan;
        this.harga= harga;
        this.url_img = url_img;

    }

    public Model_Krj() {
    }


    public String getId_psn() {
        return id_psn;
    }

    public void setId_psn(String id_psn) {
        this.id_psn = id_psn;
    }

    public String getNama_pesan() {
        return nama_pesan;
    }

    public void setNama_pesan(String nama_pesan) {
        this.nama_pesan = nama_pesan;
    }

    public String getNama_makanan() {
        return nama_makanan;
    }

    public void setNama_makanan(String nama_makanan) {
        this.nama_makanan = nama_makanan;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }






}
