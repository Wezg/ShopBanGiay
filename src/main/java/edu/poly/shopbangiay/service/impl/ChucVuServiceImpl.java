package edu.poly.shopbangiay.service.impl;

import edu.poly.shopbangiay.model.ChucVu;
import edu.poly.shopbangiay.repository.ChucVuRepository;
import edu.poly.shopbangiay.service.ChucVuService;

import java.util.List;

public class ChucVuServiceImpl implements ChucVuService {
    ChucVuRepository chucVuRepository = new ChucVuRepository();

    @Override
    public List<ChucVu> getList() {
        return chucVuRepository.getList();
    }

    @Override
    public ChucVu getCVByMa(String ma) {
        return chucVuRepository.getCVByMa(ma);
    }


    @Override
    public List<ChucVu> timKiem(String ten) {
        return chucVuRepository.timKiem(ten);
    }

    @Override
    public Boolean them(ChucVu chucVu) {
        return chucVuRepository.them(chucVu);
    }

    @Override
    public Boolean sua(ChucVu chucVu) {
        return chucVuRepository.sua(chucVu);
    }

    @Override
    public Boolean xoa(ChucVu chucVu) {
        return chucVuRepository.xoa(chucVu);
    }
}
