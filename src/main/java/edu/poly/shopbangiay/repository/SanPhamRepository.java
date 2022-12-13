package edu.poly.shopbangiay.repository;

import edu.poly.shopbangiay.Ultilities.Hibernate;
import edu.poly.shopbangiay.model.SanPham;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class SanPhamRepository {

    Session session = Hibernate.getFACTORY().openSession();
    Transaction transaction = null;

    public List<SanPham> getList() {
        Query query = session.createQuery("from SanPham");
        return query.getResultList();
    }

    public SanPham getSPByMa(String ma) {
        try{
            Query query = session.createQuery("from SanPham where ma =: ma");
            query.setParameter("ma", ma);
            return (SanPham) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public SanPham getOne(String ten) {
        Query query = session.createQuery("from SanPham where ten =: ten");
        query.setParameter("ten", ten);
        return (SanPham) query.getSingleResult();
    }

    public List<SanPham> timKiem(String ma) {
        Query query = session.createQuery("from SanPham where ma like: ma");
        query.setParameter("ma", "%" + ma + "%");
        return query.getResultList();
    }

    public String getID(String ma) {
        Query query = session.createQuery("select sp.id from SanPham sp where sp.ma =: ma");
        query.setParameter("ma", ma);
        return (String) query.getSingleResult();
    }

    public Boolean them(SanPham sanPham) {
        try{
            transaction = session.beginTransaction();

            session.saveOrUpdate(sanPham);

            transaction.commit();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            return false;
        }
    }

    public Boolean sua(SanPham sanPham) {
        try{
            transaction = session.beginTransaction();

            session.update(sanPham);

            transaction.commit();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            return false;
        }
    }

    public Boolean xoa(SanPham sanPham) {
        try{
            transaction = session.beginTransaction();

            session.delete(sanPham);

            transaction.commit();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            return false;
        }
    }

}
