package edu.poly.shopbangiay.service;

import edu.poly.shopbangiay.model.HoaDon;

import java.sql.Date;
import java.util.List;

public interface HoaDonService {
    List<HoaDon> getList();
    Double doanhThuNgay(Date date);
    List<HoaDon> locDate(Date date, Date toDate);
    List<HoaDon> locTT(Integer tt);
    HoaDon getHDByMa(String ma);
    List<HoaDon> timKiem(String ten);
    Boolean them(HoaDon hoaDon);
    Boolean sua(HoaDon hoaDon);
    Boolean xoa(HoaDon hoaDon);
}
