package edu.poly.shopbangiay.service;

import edu.poly.shopbangiay.model.ChucVu;
import edu.poly.shopbangiay.model.NguoiDung;

import java.util.List;

public interface NguoiDungService {
    List<NguoiDung> getList();
    NguoiDung getNDByMa(String ma);
    NguoiDung getNDBySDT(String sdt);
    NguoiDung getNDByEmail(String email);
    List<NguoiDung> timKiem(String ten);
    Boolean them(NguoiDung nguoiDung);
    Boolean sua(NguoiDung nguoiDung);
    Boolean xoa(NguoiDung nguoiDung);
}
