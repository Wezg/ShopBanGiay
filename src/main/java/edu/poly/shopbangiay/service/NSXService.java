package edu.poly.shopbangiay.service;

import edu.poly.shopbangiay.model.NSX;

import java.util.List;

public interface NSXService {
    List<NSX> getList();

    NSX getNSXByMa(String ma);
    List<NSX> timKiem(String ten);
    Boolean them(NSX nsx);
    Boolean sua(NSX nsx);
    Boolean xoa(NSX nsx);
}
