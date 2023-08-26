package edu.poly.shopbangiay.service.impl;

import edu.poly.shopbangiay.model.MauSac;
import edu.poly.shopbangiay.repository.MauSacRepository;
import edu.poly.shopbangiay.service.MauSacService;

import java.util.List;

public class MauSacServiceImpl implements MauSacService {
    MauSacRepository mauSacRepository = new MauSacRepository();

    @Override
    public List<MauSac> getList() {
        return mauSacRepository.getList();
    }

    @Override
    public MauSac getMSByMa(String ma) {
        return mauSacRepository.getMSByMa(ma);
    }


    @Override
    public List<MauSac> timKiem(String ten) {
        return mauSacRepository.timKiem(ten);
    }

    @Override
    public Boolean them(MauSac mauSac) {
        return mauSacRepository.them(mauSac);
    }

    @Override
    public Boolean sua(MauSac mauSac) {
        return mauSacRepository.sua(mauSac);
    }

    @Override
    public Boolean xoa(MauSac mauSac) {
        return mauSacRepository.xoa(mauSac);
    }
}
