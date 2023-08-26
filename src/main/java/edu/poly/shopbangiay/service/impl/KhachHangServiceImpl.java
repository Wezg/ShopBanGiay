package edu.poly.shopbangiay.service.impl;

import edu.poly.shopbangiay.model.KhachHang;
import edu.poly.shopbangiay.repository.KhachHangRepository;
import edu.poly.shopbangiay.service.KhachHangService;

import java.util.List;

public class KhachHangServiceImpl implements KhachHangService {
    KhachHangRepository khachHangRepository = new KhachHangRepository();

    @Override
    public List<KhachHang> getList() {
        return khachHangRepository.getList();
    }

    @Override
    public KhachHang getKHByMa(String ma) {
        return khachHangRepository.getKHByMa(ma);
    }

    @Override
    public List<KhachHang> timKiem(String ten) {
        return khachHangRepository.timKiem(ten);
    }

    @Override
    public Boolean them(KhachHang khachHang) {
        return khachHangRepository.them(khachHang);
    }

    @Override
    public Boolean sua(KhachHang khachHang) {
        return khachHangRepository.sua(khachHang);
    }

    @Override
    public Boolean xoa(KhachHang khachHang) {
        return khachHangRepository.xoa(khachHang);
    }
}
