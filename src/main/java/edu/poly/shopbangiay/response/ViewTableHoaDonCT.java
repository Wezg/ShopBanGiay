/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.shopbangiay.response;

import edu.poly.shopbangiay.model.ChiTietHoaDon;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewTableHoaDonCT {

    private String maHD;
    private String ten;
    private Integer soLuong;
    private Float donGia;
    private Float thanhTien;

    public ViewTableHoaDonCT() {
    }

    public ViewTableHoaDonCT(ChiTietHoaDon chiTiet) {
        this.maHD = chiTiet.getHoaDon().getMa();
        this.ten = chiTiet.getChiTietSanPham().getSanPham().getTen();
        this.soLuong = chiTiet.getSoLuong();
        this.donGia = chiTiet.getDonGia();
        this.thanhTien = chiTiet.tongTien();
    }
}
