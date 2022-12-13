package edu.poly.shopbangiay.service.impl;

import edu.poly.shopbangiay.model.ChucVu;
import edu.poly.shopbangiay.model.NguoiDung;
import edu.poly.shopbangiay.repository.NguoiDungRepository;
import edu.poly.shopbangiay.service.NguoiDungService;

import java.util.List;

public class NguoiDungServiceImpl implements NguoiDungService {
    NguoiDungRepository nguoiDungRepository = new NguoiDungRepository();

    @Override
    public List<NguoiDung> getList() {
        return nguoiDungRepository.getList();
    }

    @Override
    public NguoiDung getNDByMa(String ma) {
        return nguoiDungRepository.getNDByMa(ma);
    }

    @Override
    public NguoiDung getNDBySDT(String sdt) {
        return nguoiDungRepository.getNDBySDT(sdt);
    }

    @Override
    public List<NguoiDung> timKiem(String ten) {
        return nguoiDungRepository.timKiem(ten);
    }

    @Override
    public Boolean them(NguoiDung nguoiDung) {
        return nguoiDungRepository.them(nguoiDung);
    }

    @Override
    public Boolean sua(NguoiDung nguoiDung) {
        return nguoiDungRepository.sua(nguoiDung);
    }

    @Override
    public Boolean xoa(NguoiDung nguoiDung) {
        return nguoiDungRepository.xoa(nguoiDung);
    }
}
