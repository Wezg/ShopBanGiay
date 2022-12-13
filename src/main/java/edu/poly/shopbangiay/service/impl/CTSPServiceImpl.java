package edu.poly.shopbangiay.service.impl;

import edu.poly.shopbangiay.model.*;
import edu.poly.shopbangiay.repository.CTSPRepository;
import edu.poly.shopbangiay.service.CTSPService;
import edu.poly.shopbangiay.viewModel.VMCTSP;

import java.util.ArrayList;
import java.util.List;

public class CTSPServiceImpl implements CTSPService {
    CTSPRepository ctspRepository = new CTSPRepository();

    @Override
    public List<VMCTSP> getListVMCTSP() {
        List<VMCTSP> listVMCTSP = new ArrayList<>();
        for (ChiTietSanPham ctsp : ctspRepository.getList()) {
            listVMCTSP.add(new VMCTSP(
                    ctsp.getSanPham().getMa(),
                    ctsp.getSanPham().getTen(),
                    ctsp.getLoai().getTen(),
                    ctsp.getGiaNhap(),
                    ctsp.getGiaBan(),
                    ctsp.getSoLuong(),
                    ctsp.getTinhTrang()));
        }
        return listVMCTSP;
    }

    @Override
    public List<ChiTietSanPham> getList() {
        return ctspRepository.getList();
    }

    @Override
    public List<SanPham> listSP() {
        return ctspRepository.listSP();
    }

    @Override
    public List<Loai> listLoai() {
        return ctspRepository.listLoai();
    }

    @Override
    public List<NSX> listNSX() {
        return ctspRepository.listNSX();
    }

    @Override
    public List<Size> listSize() {
        return ctspRepository.listSize();
    }

    @Override
    public List<MauSac> listMS() {
        return ctspRepository.listMS();
    }

    @Override
    public List<ChatLieu> listCL() {
        return ctspRepository.listCL();
    }

    @Override
    public List<ChiTietSanPham> timKiem(String ten) {
        return ctspRepository.timKiem(ten);
    }

    @Override
    public ChiTietSanPham getCTSPByMaSP(String maSP) {
        return ctspRepository.getCTSPByMaSP(maSP);
    }

    @Override
    public Boolean them(ChiTietSanPham ctsp) {
        return ctspRepository.them(ctsp);
    }

    @Override
    public Boolean sua(ChiTietSanPham ctsp) {
        return ctspRepository.sua(ctsp);
    }

    @Override
    public Boolean xoa(ChiTietSanPham ctsp) {
        return ctspRepository.xoa(ctsp);
    }
}
