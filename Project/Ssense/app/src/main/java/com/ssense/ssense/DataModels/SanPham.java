package com.ssense.ssense.DataModels;

import android.support.annotation.NonNull;

/**
 * Created by yiile on 12/2/2017.
 */

public class SanPham implements Comparable<SanPham>{
    public String name;
    public Integer deal;

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

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public String getMau1() {
        return mau1;
    }

    public void setMau1(String mau1) {
        this.mau1 = mau1;
    }

    public String getNgaydang() {
        return ngaydang;
    }

    public void setNgaydang(String ngaydang) {
        this.ngaydang = ngaydang;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize1() {
        return size1;
    }

    public void setSize1(String size1) {
        this.size1 = size1;
    }

    public String getSize2() {
        return size2;
    }

    public void setSize2(String size2) {
        this.size2 = size2;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getHinh1() {
        return hinh1;
    }

    public void setHinh1(String hinh1) {
        this.hinh1 = hinh1;
    }

    public String getHinh2() {
        return hinh2;
    }

    public void setHinh2(String hinh2) {
        this.hinh2 = hinh2;
    }

    public Float gia;
    public String mau,mau1;
    public String ngaydang;
    public String size,size1,size2;
    public String hinh,hinh1,hinh2;
    public String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    @Override
    public int compareTo(@NonNull SanPham o) {
        return this.gia.compareTo(o.gia);
    }
}
