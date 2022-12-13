package edu.poly.shopbangiay.service.impl;

import edu.poly.shopbangiay.model.HoaDon;
import edu.poly.shopbangiay.repository.HoaDonRepository;
import edu.poly.shopbangiay.service.HoaDonService;

import java.sql.Date;
import java.util.List;

public class HoaDonServiceImpl implements HoaDonService {
    HoaDonRepository hoaDonRepository = new HoaDonRepository();

    @Override
    public List<HoaDon> getList() {
        return hoaDonRepository.getList();
    }

    @Override
    public Double doanhThuNgay(Date date) {
        return hoaDonRepository.doanhThuNgay(date);
    }

    @Override
    public List<HoaDon> locDate(Date date, Date toDate) {
        return hoaDonRepository.locDate(date, toDate);
    }

    @Override
    public List<HoaDon> locTT(Integer tt) {
        return hoaDonRepository.locTT(tt);
    }

    @Override
    public HoaDon getHDByMa(String ma) {
        return hoaDonRepository.getHDByMa(ma);
    }

    @Override
    public List<HoaDon> timKiem(String ten) {
        return hoaDonRepository.timKiem(ten);
    }

    @Override
    public Boolean them(HoaDon hoaDon) {
        return hoaDonRepository.them(hoaDon);
    }

    @Override
    public Boolean sua(HoaDon hoaDon) {
        return hoaDonRepository.sua(hoaDon);
    }

    @Override
    public Boolean xoa(HoaDon hoaDon) {
        return hoaDonRepository.xoa(hoaDon);
    }
}
