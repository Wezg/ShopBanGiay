/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.poly.shopbangiay.service;

import edu.poly.shopbangiay.model.HoaDon;
import edu.poly.shopbangiay.response.ViewThongKe;
import edu.poly.shopbangiay.response.ViewThongKeNgay;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface ThongKeService {
    public List<HoaDon> getAll();
    
    public ViewThongKeNgay getTongHoaDonNgay(Date ngay);
    
    public ViewThongKe getTongHoaDonThang(Date thang);
    
    public ViewThongKe getTongHoaDonNam(Date nam);
    
    public ViewThongKe getTongHoaDonTuyChon(java.util.Date batDau, java.util.Date ketThuc);
    
    public List<ViewThongKeNgay> getViewNgayTrongThang(int thang, int nam);
    
    public List<ViewThongKeNgay> getViewThangTrongNam(int nam);
    
    public List<ViewThongKeNgay> getViewTKNam(int nam);
}
