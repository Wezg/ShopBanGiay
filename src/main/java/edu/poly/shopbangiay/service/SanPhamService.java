package edu.poly.shopbangiay.service;

import edu.poly.shopbangiay.model.SanPham;

import java.util.List;

public interface SanPhamService {
    List<SanPham> getList();
    SanPham getOne(String ten);
    List<SanPham> timKiem(String ten);
    SanPham getSPByMa(String ma);
    String getID(String ma);
    Boolean them(SanPham sanPham);
    Boolean sua(SanPham sanPham);
    Boolean xoa(SanPham sanPham);
}
