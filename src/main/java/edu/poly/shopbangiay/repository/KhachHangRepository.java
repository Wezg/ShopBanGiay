package edu.poly.shopbangiay.repository;

import edu.poly.shopbangiay.Ultilities.Hibernate;
import edu.poly.shopbangiay.model.KhachHang;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class KhachHangRepository {

    Session session = Hibernate.getFACTORY().openSession();
    Transaction transaction = null;

    public List<KhachHang> getList() {
        Query query = session.createQuery("from KhachHang ");
        return query.getResultList();
    }

    public List<KhachHang> timKiem(String ten) {
        Query query = session.createQuery("from KhachHang where ten like: ten");
        query.setParameter("ten", "%" + ten + "%");
        return query.getResultList();
    }

    public KhachHang getKHByMa(String ma){
        try {
            Query query = session.createQuery("from KhachHang where ma =: ma");
            query.setParameter("ma", ma);
            return (KhachHang) query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    public Boolean them(KhachHang khachHang) {
        try {
            transaction = session.beginTransaction();

            session.save(khachHang);

            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public Boolean sua(KhachHang khachHang) {
        try {
            transaction = session.beginTransaction();

            session.update(khachHang);

            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public Boolean xoa(KhachHang khachHang) {
        try {
            transaction = session.beginTransaction();

            session.delete(khachHang);

            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }
}
