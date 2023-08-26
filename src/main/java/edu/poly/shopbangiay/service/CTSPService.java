package edu.poly.shopbangiay.service;

import edu.poly.shopbangiay.model.*;
import edu.poly.shopbangiay.viewModel.VMCTSP;

import java.util.List;

public interface CTSPService {
    List<VMCTSP> getListVMCTSP();
    List<ChiTietSanPham> getList();
    List<SanPham> listSP();
    List<Loai> listLoai();
    List<NSX> listNSX();
    List<Size> listSize();
    List<MauSac> listMS();
    List<ChatLieu> listCL();
    List<ChiTietSanPham> timKiem(String ten);

    ChiTietSanPham getCTSPByMaSP(String maSP);
    Boolean them(ChiTietSanPham ctsp);
    Boolean sua(ChiTietSanPham ctsp);
    Boolean xoa(ChiTietSanPham ctsp);
}
