package edu.poly.shopbangiay.repository;

import edu.poly.shopbangiay.Ultilities.Hibernate;
import edu.poly.shopbangiay.model.Voucher;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

public class VCRepository {
    Session session = Hibernate.getFACTORY().openSession();
    Transaction transaction;

    public List<Voucher> getList() {
        Query query = session.createQuery("from Voucher order by ngayTao desc");
        return query.getResultList();
    }


    public List<Voucher> getList(Date date) {
        try {
            Query query = session.createQuery("from Voucher where ngayBD <: date and ngayKT >: date order by ngayTao asc ");
            query.setParameter("date", date);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Voucher> timKiem(String ten) {
        Query query = session.createQuery("from Voucher where ten like: ten order by ngayTao desc");
        query.setParameter("ten", "%" + ten + "%");
        return query.getResultList();
    }

    public Voucher getVCByMa(String ma) {
        try {
            Query query = session.createQuery("from Voucher where ma =: ma");
            query.setParameter("ma", ma);
            return (Voucher) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean them(Voucher voucher) {
        try {
            transaction = session.beginTransaction();

            session.save(voucher);

            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public Boolean sua(Voucher voucher) {
        try {
            transaction = session.beginTransaction();

            session.update(voucher);

            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public Boolean xoa(Voucher voucher) {
        try {
            transaction = session.beginTransaction();

            session.delete(voucher);

            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }
}
