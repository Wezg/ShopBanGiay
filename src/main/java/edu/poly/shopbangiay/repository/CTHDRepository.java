package edu.poly.shopbangiay.repository;

import edu.poly.shopbangiay.Ultilities.Hibernate;
import edu.poly.shopbangiay.model.ChiTietHoaDon;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class CTHDRepository {

    Session session = Hibernate.getFACTORY().openSession();
    Transaction transaction = null;

    public List<ChiTietHoaDon> getList() {
        Query query = session.createQuery("from ChiTietHoaDon ");
        return query.getResultList();
    }

    public List<ChiTietHoaDon> timKiem(String ma) {
        Query query = session.createQuery("from ChiTietHoaDon cthd where cthd.hoaDon.ma like: maHD or cthd.chiTietSanPham.sanPham.ma like: maSP");
        query.setParameter("maHD", "%" + ma + "%");
        query.setParameter("maSP", "%" + ma + "%");
        return query.getResultList();
    }

    public ChiTietHoaDon getCTHD(Integer idHD, Integer idCTSP) {
        ChiTietHoaDon cthd = null;
        try {
            Query query = session.createQuery("select cthd from ChiTietHoaDon cthd where cthd.hoaDon.id =: idHD and cthd.chiTietSanPham.id =: idCTSP ");
            query.setParameter("idHD", idHD);
            query.setParameter("idCTSP", idCTSP);
            cthd = (ChiTietHoaDon) query.getSingleResult();
        } catch (Exception ignored) {
        }
        return cthd;
    }

    public ChiTietHoaDon getByMaHD(String ma) {
        Query query = session.createQuery("from ChiTietHoaDon cthd where cthd.hoaDon.ma =: ma");
        query.setParameter("ma", ma);
        return (ChiTietHoaDon) query.getSingleResult();
    }


    public List<ChiTietHoaDon> getCTHDByMaHD(String ma) {
        Query query = session.createQuery("select cthd from ChiTietHoaDon cthd where cthd.hoaDon.ma = : ma");
        query.setParameter("ma", ma);
        return query.getResultList();
    }

    public List<ChiTietHoaDon> SelectByHoaDonCTID(int id) {
        List<ChiTietHoaDon> listHoaDonChiTiet = new ArrayList<>();
        try {
            Query query = session.createQuery("FROM ChiTietHoaDon cthd where  cthd.hoaDon = :id");
            query.setParameter("id", id);
            if (query.getResultList() != null && !query.getResultList().isEmpty()) {
                listHoaDonChiTiet = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHoaDonChiTiet;
    }

    public Boolean them(ChiTietHoaDon cthd) {
        try {
            transaction = session.beginTransaction();

            session.save(cthd);

            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public Boolean sua(ChiTietHoaDon cthd) {
        try {
            transaction = session.beginTransaction();

            session.update(cthd);

            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public Boolean xoa(ChiTietHoaDon cthd) {
        try {
            transaction = session.beginTransaction();

            session.delete(cthd);

            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }
}
