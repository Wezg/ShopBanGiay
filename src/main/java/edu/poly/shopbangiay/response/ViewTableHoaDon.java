/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.shopbangiay.response;

import edu.poly.shopbangiay.model.HoaDon;
import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author Dell
 */
public class ViewTableHoaDon {

    private String ma;
    private String khachHang;
    private String nhanVien;
    private Date ngayTao;
    private Date ngayThanhToan;
    private String tinhTrang;
    private String voucher;
    private Float tongTien;

    public ViewTableHoaDon() {
    }

    public ViewTableHoaDon(HoaDon hoaDon) {
        this.ma = hoaDon.getMa();
        this.khachHang = hoaDon.getKhachHang().getTen();
//        this.nhanVien = hoaDon.getUsers().getTen();
        this.ngayTao = (Date) hoaDon.getNgayTao();
        this.ngayThanhToan = (Date) hoaDon.getNgayTT();
        this.tinhTrang = hoaDon.getTinhTrang() == 0 ? "Chờ thanh toán" : "Đã thanh toán";
//        this.voucher = hoaDon.getVoucher().getTen();
        this.tongTien = hoaDon.getTongTien();
    }
}
