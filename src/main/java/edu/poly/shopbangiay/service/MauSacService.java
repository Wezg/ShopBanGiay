package edu.poly.shopbangiay.service;

import edu.poly.shopbangiay.model.MauSac;

import java.util.List;

public interface MauSacService {
    List<MauSac> getList();
    MauSac getMSByMa(String ma);
    List<MauSac> timKiem(String ten);
    Boolean them(MauSac mauSac);
    Boolean sua(MauSac mauSac);
    Boolean xoa(MauSac mauSac);
}
