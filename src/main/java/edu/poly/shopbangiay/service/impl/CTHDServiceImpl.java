package edu.poly.shopbangiay.service.impl;

import edu.poly.shopbangiay.model.ChiTietHoaDon;
import edu.poly.shopbangiay.repository.CTHDRepository;
import edu.poly.shopbangiay.service.CTHDService;

import java.util.List;

public class CTHDServiceImpl implements CTHDService {
    CTHDRepository cthdRepository = new CTHDRepository();

    @Override
    public List<ChiTietHoaDon> getList() {
        return cthdRepository.getList();
    }

    @Override
    public List<ChiTietHoaDon> timKiem(String ma) {
        return cthdRepository.timKiem(ma);
    }

    @Override
    public ChiTietHoaDon getCTHD(Integer idHD, Integer idCTSP) {
        return cthdRepository.getCTHD(idHD, idCTSP);
    }

    @Override
    public ChiTietHoaDon getByMaHD(String ma) {
        return cthdRepository.getByMaHD(ma);
    }

    @Override
    public List<ChiTietHoaDon> getCTHDByMaHD(String maHD) {
        return cthdRepository.getCTHDByMaHD(maHD);
    }

    @Override
    public Boolean them(ChiTietHoaDon cthd) {
        return cthdRepository.them(cthd);
    }

    @Override
    public Boolean sua(ChiTietHoaDon cthd) {
        return cthdRepository.sua(cthd);
    }

    @Override
    public Boolean xoa(ChiTietHoaDon cthd) {
        return cthdRepository.xoa(cthd);
    }
}
