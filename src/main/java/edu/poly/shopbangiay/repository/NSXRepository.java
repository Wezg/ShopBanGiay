package edu.poly.shopbangiay.repository;

import edu.poly.shopbangiay.Ultilities.Hibernate;
import edu.poly.shopbangiay.model.NSX;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class NSXRepository {

    Session session = Hibernate.getFACTORY().openSession();
    Transaction transaction = null;

    public List<NSX> getList(){
        Query query = session.createQuery("from NSX ");
        return query.getResultList();
    }

    public List<NSX> timKiem(String ten){
        Query query = session.createQuery("from NSX where ten like: ten");
        query.setParameter("ten", "%" + ten + "%");
        return query.getResultList();
    }

    public NSX getNSXByMa(String ma){
        try{
            Query query = session.createQuery("from NSX where ma =: ma");
            query.setParameter("ma", ma);
            return (NSX) query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    public Boolean them(NSX nsx){
        try{
            transaction = session.beginTransaction();

            session.saveOrUpdate(nsx);

            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public Boolean sua(NSX nsx){
        try{
            transaction = session.beginTransaction();

            session.update(nsx);

            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public Boolean xoa(NSX nsx){
        try{
            transaction = session.beginTransaction();

            session.delete(nsx);

            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }
}
