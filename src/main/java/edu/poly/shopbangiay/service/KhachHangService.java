package edu.poly.shopbangiay.service;

import edu.poly.shopbangiay.model.HoaDon;
import edu.poly.shopbangiay.model.KhachHang;

import java.util.List;

public interface KhachHangService {
    List<KhachHang> getList();
    KhachHang getKHByMa(String ma);
    List<KhachHang> timKiem(String ten);
    Boolean them(KhachHang khachHang);
    Boolean sua(KhachHang khachHang);
    Boolean xoa(KhachHang khachHang);

}
