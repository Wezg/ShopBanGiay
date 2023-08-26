package edu.poly.shopbangiay.service;

import edu.poly.shopbangiay.model.ChucVu;

import java.util.List;

public interface ChucVuService {
    List<ChucVu> getList();
    ChucVu getCVByMa(String ma);
    List<ChucVu> timKiem(String ten);
    Boolean them(ChucVu chucVu);
    Boolean sua(ChucVu chucVu);
    Boolean xoa(ChucVu chucVu);
}
