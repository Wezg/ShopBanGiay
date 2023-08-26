/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.shopbangiay.service.impl;

import edu.poly.shopbangiay.model.ChiTietHoaDon;
import edu.poly.shopbangiay.model.HoaDon;
import edu.poly.shopbangiay.repository.CTHDRepository;
import edu.poly.shopbangiay.repository.HoaDonRepository;
import edu.poly.shopbangiay.repository.ThongKeRepository;
import edu.poly.shopbangiay.response.HoaDonThongKeRespone;
import edu.poly.shopbangiay.response.ViewThongKe;
import edu.poly.shopbangiay.response.ViewThongKeNgay;
import edu.poly.shopbangiay.service.ThongKeService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class ThongKeServiceImpl implements ThongKeService {

    ThongKeRepository repository = new ThongKeRepository();
    private HoaDonRepository hd = new HoaDonRepository();
    private CTHDRepository hdct = new CTHDRepository();

    @Override
    public List<HoaDon> getAll() {
        return repository.getAll();
    }

    public List<HoaDonThongKeRespone> getHDByDate(Date from, Date to) {
        List<HoaDon> lstHd = hd.getHDByDate(from, to);
        List<HoaDonThongKeRespone> lsthdtk = new ArrayList<>();
        for (HoaDon s : lstHd) {
            List<ChiTietHoaDon> lsthdct = hdct.SelectByHoaDonCTID(s.getId());
            HoaDonThongKeRespone hd = new HoaDonThongKeRespone(s, lsthdct);
            lsthdtk.add(hd);
        }
        return lsthdtk;
    }

    @Override
    public ViewThongKeNgay getTongHoaDonNgay(Date ngay) {
        return repository.getTongHoaDonNgay(ngay);
    }

    @Override
    public ViewThongKe getTongHoaDonThang(Date thang) {
        return repository.getTongHoaDonThang(thang);
    }

    @Override
    public ViewThongKe getTongHoaDonNam(Date nam) {
        return repository.getTongHoaDonNam(nam);
    }

    @Override
    public ViewThongKe getTongHoaDonTuyChon(java.util.Date batDau, java.util.Date ketThuc) {
        return repository.getTongHoaDonTuyChon(batDau, ketThuc);

    }

    @Override
    public List<ViewThongKeNgay> getViewNgayTrongThang(int thang, int nam) {
        return repository.getViewNgayTrongThang(thang, nam);

    }

    @Override
    public List<ViewThongKeNgay> getViewThangTrongNam(int nam) {
        return repository.getViewThangTrongNam(nam);

    }

    @Override
    public List<ViewThongKeNgay> getViewTKNam(int nam) {
        return repository.getViewTKNam(nam);

    }

}
