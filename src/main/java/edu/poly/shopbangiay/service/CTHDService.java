package edu.poly.shopbangiay.service;

import edu.poly.shopbangiay.model.ChiTietHoaDon;

import java.util.List;

public interface CTHDService {
    List<ChiTietHoaDon> getList();
    List<ChiTietHoaDon> timKiem(String ma);
    ChiTietHoaDon getCTHD(Integer idHD, Integer idCTSP);
    ChiTietHoaDon getByMaHD(String ma);
    List<ChiTietHoaDon> getCTHDByMaHD(String maHD);
    Boolean them(ChiTietHoaDon cthd);
    Boolean sua(ChiTietHoaDon cthd);
    Boolean xoa(ChiTietHoaDon cthd);
}
