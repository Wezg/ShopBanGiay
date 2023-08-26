package edu.poly.shopbangiay.service.impl;

import edu.poly.shopbangiay.model.Loai;
import edu.poly.shopbangiay.repository.LoaiRepository;
import edu.poly.shopbangiay.service.LoaiService;

import java.util.List;

public class LoaiServiceImpl implements LoaiService {
    LoaiRepository loaiRepository = new LoaiRepository();

    @Override
    public List<Loai> getList() {
        return loaiRepository.getList();
    }

    @Override
    public Loai getLoaiByMa(String ma) {
        return loaiRepository.getLoaiByMa(ma);
    }


    @Override
    public List<Loai> timKiem(String ten) {
        return loaiRepository.timKiem(ten);
    }

    @Override
    public Boolean them(Loai loai) {
        return loaiRepository.them(loai);
    }

    @Override
    public Boolean sua(Loai loai) {
        return loaiRepository.sua(loai);
    }

    @Override
    public Boolean xoa(Loai loai) {
        return loaiRepository.xoa(loai);
    }
}
