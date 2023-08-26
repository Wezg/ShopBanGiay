package edu.poly.shopbangiay.service;

import edu.poly.shopbangiay.model.Voucher;

import java.sql.Date;
import java.util.List;

public interface VCService {
    List<Voucher> getList(Date date);
    List<Voucher> getList();
    Voucher getVCByMa(String ma);
    List<Voucher> timKiem(String ten);
    Boolean them(Voucher voucher);
    Boolean sua(Voucher voucher);
    Boolean xoa(Voucher voucher);
}
