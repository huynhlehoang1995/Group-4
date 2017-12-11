package com.ssense.ssense.ActivityController;

/**
 * Created by yiile on 12/5/2017.
 */

public class SanPham {
    public String name;
    public String key;
    public Integer deal;
    public Float gia;
    public String mau,mau1;
    public String ngaydang;
    public String size,size1,size2;
    public String hinh,hinh1,hinh2;

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDeal() {
        return deal;
    }

    public void setDeal(Integer deal) {
        this.deal = deal;
    }

    public Float getGia() {
        return gia;
    }

    public void setGia(Float gia) {
        this.gia = gia;
    }

    public SanPham() {
    }

    public SanPham(String name, Integer deal, Float gia, String mau, String ngaydang, String size) {
        this.name = name;
        this.deal = deal;
        this.gia = gia;
        this.mau = mau;
        this.ngaydang = ngaydang;
        this.size = size;
    }
}
