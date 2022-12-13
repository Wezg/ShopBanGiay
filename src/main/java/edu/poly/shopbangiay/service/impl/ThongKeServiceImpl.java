/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.shopbangiay.service.impl;

import edu.poly.shopbangiay.model.HoaDon;
import edu.poly.shopbangiay.repository.ThongKeRepository;
import edu.poly.shopbangiay.response.ViewThongKe;
import edu.poly.shopbangiay.response.ViewThongKeNgay;
import edu.poly.shopbangiay.service.ThongKeService;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Dell
 */
public class ThongKeServiceImpl implements ThongKeService {

    ThongKeRepository repository = new ThongKeRepository();

    @Override
    public List<HoaDon> getAll() {
        return repository.getAll();
    }

    @Override
    public ViewThongKeNgay getTongHoaDonNgay(Date ngay) {
        return repository.getTongHoaDonNgay(ngay);
    }

    @Override
    public ViewThongKe getTongHoaDonThang(Date thang) {
        return repository.getTongHoaDonNam(thang);
    }

    @Override
    public ViewThongKe getTongHoaDonNam(Date nam) {
        return repository.getTongHoaDonThang(nam);
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
