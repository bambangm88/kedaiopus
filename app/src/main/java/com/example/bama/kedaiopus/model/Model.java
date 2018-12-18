package com.example.bama.kedaiopus.model;

public class Model {

    private String id_mkn ;
    private String nama_mkn;
    private String harga_mkn ;
    private String rating;
    private String deskripsi_mkn;
    private String url_mkn;
    private String kategori;

    public Model() {
    }




    public Model (String id_mkn, String kategori, String nama_mkn, String harga_mkn, String  rating, String deskripsi_mkn, String url_mkn) {

        this.id_mkn = id_mkn;
        this.nama_mkn= nama_mkn;
        this.harga_mkn = harga_mkn;
        this.rating= rating;
        this.deskripsi_mkn = deskripsi_mkn;
        this.url_mkn = url_mkn;
        this.kategori = kategori ;
    }

    public String getId_mkn() {
        return id_mkn;
    }

    public void setId_mkn(String id_mkn) {
        this.id_mkn = id_mkn;
    }

    public String getNama_mkn() {
        return nama_mkn;
    }

    public void setNama_mkn(String nama_mkn) {
        this.nama_mkn = nama_mkn;
    }

    public String getHarga_mkn() {
        return harga_mkn;
    }

    public void setHarga_mkn(String harga_mkn) {
        this.harga_mkn = harga_mkn;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDeskripsi_mkn() {
        return deskripsi_mkn;
    }

    public void setDeskripsi_mkn(String deskripsi_mkn) {
        this.deskripsi_mkn = deskripsi_mkn;
    }

    public String getUrl_mkn() {
        return url_mkn;
    }

    public void setUrl_mkn(String url_mkn) {
        this.url_mkn = url_mkn;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }




}
