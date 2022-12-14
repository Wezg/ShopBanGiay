/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.shopbangiay.repository;

import edu.poly.shopbangiay.Ultilities.Hibernate;
import edu.poly.shopbangiay.model.HoaDon;
import edu.poly.shopbangiay.response.ViewThongKe;
import edu.poly.shopbangiay.response.ViewThongKeNgay;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Dell
 */
public class ThongKeRepository {

    private String fromTable = "From HoaDon";

    private Session session = Hibernate.getFACTORY().openSession();

    public List<HoaDon> getAll() {
        Query query = session.createQuery(fromTable, HoaDon.class);
        List<HoaDon> list = query.getResultList();
        return list;
    }

    public ViewThongKeNgay getTongHoaDonNgay(Date ngay) {
        Query q = session.createQuery("SELECT ngayTao, SUM(tongTien) AS doanhThu,"
                + " COUNT(*) AS soHD FROM HoaDon GROUP BY ngayTao "
                + "HAVING ngayTao = :ngayTao");
        q.setParameter("ngayTao", ngay);
        List<Object[]> listOj = q.getResultList();
        Date ngayTK = null;
        Long soHD = null;
        Float doanhThu = null;
        for (Object[] objects : listOj) {
            ngayTK = (Date) objects[0];
            soHD = (Long) objects[2];
            doanhThu = (Float.parseFloat(objects[1].toString()));
        }
        ViewThongKeNgay viewThongKe = new ViewThongKeNgay(ngayTK, doanhThu, soHD);
        return viewThongKe;
    }

    public ViewThongKe getTongHoaDonThang(Date thang) {
        Query q = session.createQuery("SELECT SUM(tongTien) AS doanhThu,\n"
                + "                COUNT(*) AS soHD FROM HoaDon \n"
                + "                WHERE MONTH(ngayTao) = MONTH(:thang)");
        q.setParameter("thang", thang);
        List<Object[]> listOj = q.getResultList();
        Long soHD = null;
        Float doanhThu = null;
        for (Object[] objects : listOj) {
            soHD = (Long) objects[1];
            doanhThu = (Float.parseFloat(objects[0].toString()));
        }
        ViewThongKe viewThongKe = new ViewThongKe(soHD, doanhThu);
        return viewThongKe;
    }

    public ViewThongKe getTongHoaDonNam(Date nam) {
        Query q = session.createQuery("SELECT SUM(tongTien) AS doanhThu,\n"
                + "                COUNT(*) AS soHD FROM HoaDon \n"
                + "                WHERE YEAR(ngayTao) = YEAR(:nam)");
        q.setParameter("nam", nam);
        List<Object[]> listOj = q.getResultList();
        Long soHD = null;
        Float doanhThu = null;
        for (Object[] objects : listOj) {
            soHD = (Long) objects[1];
            doanhThu = (Float.parseFloat(objects[0].toString()));
        }
        ViewThongKe viewThongKe = new ViewThongKe(soHD, doanhThu);
        return viewThongKe;
    }

    public ViewThongKe getTongHoaDonTuyChon(java.util.Date batDau, java.util.Date ketThuc) {
        Query q = session.createQuery("SELECT SUM(tongTien) AS doanhThu,\n"
                + "                COUNT(*) AS soHD FROM HoaDon \n"
                + "                WHERE ngayTao BETWEEN :batDau AND :ketThuc");
        q.setParameter("batDau", batDau);
        q.setParameter("ketThuc", ketThuc);
        List<Object[]> listOj = q.getResultList();
        Long soHD = null;
        Float doanhThu = null;
        for (Object[] objects : listOj) {
            soHD = (Long) objects[1];
            doanhThu = (Float.parseFloat(objects[0].toString()));
        }
        ViewThongKe viewThongKe = new ViewThongKe(soHD, doanhThu);
        return viewThongKe;
    }

    public List<ViewThongKeNgay> getViewNgayTrongThang(int thang, int nam) {
        Query q = session.createQuery("SELECT day(ngayTao), SUM(tongTien) AS doanhThu, COUNT(*) AS soHD "
                + "FROM HoaDon GROUP BY ngayTao having month(ngayTao) =: thang and year(ngayTao) =: nam");
        q.setParameter("thang", thang);
        q.setParameter("nam", nam);
        List<Object[]> listOb = q.getResultList();
        List<ViewThongKeNgay> list = new ArrayList<>();
        for (Object[] objects : listOb) {
            int ngayTao = (int) objects[0];
            Float tongTien = (Float.parseFloat(objects[1].toString()));
            Long soHD = (Long) objects[2];
            ViewThongKeNgay vtkn = new ViewThongKeNgay(ngayTao, tongTien, soHD);
            list.add(vtkn);
        }
        return list;
    }

    public List<ViewThongKeNgay> getViewThangTrongNam(int nam) {
        Query q = session.createQuery("SELECT MONTH(ngayTao) AS Thang, SUM(tongTien) AS doanhThu, COUNT(*) AS soHD "
                + "FROM HoaDon GROUP BY ngayTao having year(ngayTao) =: nam ");
        q.setParameter("nam", nam);
        List<Object[]> listOb = q.getResultList();
        List<ViewThongKeNgay> list = new ArrayList<>();
        for (Object[] objects : listOb) {
            int thang = (int) objects[0];
            Float tongTien = (Float.parseFloat(objects[1].toString()));
            Long soHD = (Long) objects[2];
            ViewThongKeNgay vtkn = new ViewThongKeNgay(thang, tongTien, soHD);
            list.add(vtkn);
        }
        return list;
    }

    public List<ViewThongKeNgay> getViewTKNam(int nam) {
        Query q = session.createQuery("SELECT YEAR(ngayTao) AS Thang, SUM(tongTien) AS doanhThu,\n"
                + "                COUNT(*) AS soHD FROM HoaDon GROUP BY YEAR(ngayTao) ");
        List<Object[]> listOb = q.getResultList();
        List<ViewThongKeNgay> list = new ArrayList<>();
        for (Object[] objects : listOb) {
            int thang = (int) objects[0];
            Float tongTien = (Float.parseFloat(objects[1].toString()));
            Long soHD = (Long) objects[2];
            ViewThongKeNgay vtkn = new ViewThongKeNgay(thang, tongTien, soHD);
            list.add(vtkn);
        }
        return list;
    }

//    public static void main(String[] args) {
//        List<ViewThongKeNgay> list = new ThongKeRepository().getViewThangTrongNam(2022);
//        for (ViewThongKeNgay objects : list) {
//            System.out.println(objects.getDoanhThu());
//            System.out.println(objects.getSoHD());
//            System.out.println(objects.getThang());
//        }
//    }

}
