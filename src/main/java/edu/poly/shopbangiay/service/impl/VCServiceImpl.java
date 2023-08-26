package edu.poly.shopbangiay.service.impl;

import edu.poly.shopbangiay.model.Voucher;
import edu.poly.shopbangiay.repository.VCRepository;
import edu.poly.shopbangiay.service.VCService;

import java.sql.Date;
import java.util.List;

public class VCServiceImpl implements VCService {
    VCRepository vcRepository = new VCRepository();

    @Override
    public List<Voucher> getList(Date date) {
        return vcRepository.getList(date);
    }

    @Override
    public List<Voucher> getList() {
        return vcRepository.getList();
    }

    @Override
    public Voucher getVCByMa(String ma) {
        return vcRepository.getVCByMa(ma);
    }

    @Override
    public List<Voucher> timKiem(String ten) {
        return vcRepository.timKiem(ten);
    }

    @Override
    public Boolean them(Voucher voucher) {
        return vcRepository.them(voucher);
    }

    @Override
    public Boolean sua(Voucher voucher) {
        return vcRepository.sua(voucher);
    }

    @Override
    public Boolean xoa(Voucher voucher) {
        return vcRepository.xoa(voucher);
    }
}
