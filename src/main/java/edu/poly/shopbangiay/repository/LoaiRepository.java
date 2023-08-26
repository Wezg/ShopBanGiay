package edu.poly.shopbangiay.repository;

import edu.poly.shopbangiay.Ultilities.Hibernate;
import edu.poly.shopbangiay.model.Loai;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class LoaiRepository {

    Session session = Hibernate.getFACTORY().openSession();
    Transaction transaction;

    public List<Loai> getList() {
        Query query = session.createQuery("from Loai ");
        return query.getResultList();
    }

    public List<Loai> timKiem(String ten) {
        Query query = session.createQuery("from Loai where ten like: ten");
        query.setParameter("ten", "%" + ten + "%");
        return query.getResultList();
    }

    public Loai getLoaiByMa(String ma) {
        try{
            Query query = session.createQuery("from Loai where ma =: ma");
            query.setParameter("ma", ma);
            return (Loai) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean them(Loai loai) {
        try {
            transaction = session.beginTransaction();

            session.save(loai);

            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public Boolean sua(Loai loai) {
        try {
            transaction = session.beginTransaction();

            session.update(loai);

            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public Boolean xoa(Loai loai) {
        try {
            transaction = session.beginTransaction();

            session.delete(loai);

            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }
}
