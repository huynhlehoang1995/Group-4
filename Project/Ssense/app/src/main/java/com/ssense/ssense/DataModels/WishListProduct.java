package com.ssense.ssense.DataModels;

/**
 * Created by PC on 12/6/2017.
 */

public class WishListProduct {

    String key, danhmuc, hinh, mau, name, size;
    int gia;

    public WishListProduct() {
    }

    public WishListProduct(String key, String danhmuc, int gia, String hinh, String mau, String name, String size) {
        this.key = key;
        this.danhmuc = danhmuc;
        this.gia = gia;
        this.hinh = hinh;
        this.mau = mau;
        this.name = name;
        this.size = size;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDanhmuc() {
        return danhmuc;
    }

    public void setDanhmuc(String danhmuc) {
        this.danhmuc = danhmuc;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
