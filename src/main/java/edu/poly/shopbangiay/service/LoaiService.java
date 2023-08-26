package edu.poly.shopbangiay.service;

import edu.poly.shopbangiay.model.KhachHang;
import edu.poly.shopbangiay.model.Loai;

import java.util.List;

public interface LoaiService {
    List<Loai> getList();
    Loai getLoaiByMa(String ma);
    List<Loai> timKiem(String ten);
    Boolean them(Loai loai);
    Boolean sua(Loai loai);
    Boolean xoa(Loai loai);
}
