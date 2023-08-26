package edu.poly.shopbangiay.service.impl;

import edu.poly.shopbangiay.model.SanPham;
import edu.poly.shopbangiay.repository.SanPhamRepository;
import edu.poly.shopbangiay.service.SanPhamService;

import java.util.List;

public class SanPhamServiceImpl implements SanPhamService {
    SanPhamRepository sanPhamRepository = new SanPhamRepository();

    @Override
    public List<SanPham> getList() {
        return sanPhamRepository.getList();
    }

    @Override
    public SanPham getOne(String ten) {
        return sanPhamRepository.getOne(ten);
    }

    @Override
    public List<SanPham> timKiem(String ten) {
        return sanPhamRepository.timKiem(ten);
    }

    @Override
    public SanPham getSPByMa(String ma) {
        return sanPhamRepository.getSPByMa(ma);
    }

    @Override
    public String getID(String ma) {
        return sanPhamRepository.getID(ma);
    }

    @Override
    public Boolean them(SanPham sanPham) {
        return sanPhamRepository.them(sanPham);
    }

    @Override
    public Boolean sua(SanPham sanPham) {
        return sanPhamRepository.sua(sanPham);
    }

    @Override
    public Boolean xoa(SanPham sanPham) {
        return sanPhamRepository.xoa(sanPham);
    }
}
