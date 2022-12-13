package edu.poly.shopbangiay.repository;

import edu.poly.shopbangiay.Ultilities.Hibernate;
import edu.poly.shopbangiay.model.ChucVu;
import edu.poly.shopbangiay.model.NguoiDung;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class NguoiDungRepository {

    Session session = Hibernate.getFACTORY().openSession();
    Transaction transaction = null;

    public List<NguoiDung> getList(){
        Query query = session.createQuery("from NguoiDung ");
        return query.getResultList();
    }

    public List<NguoiDung> timKiem(String ten){
        Query query = session.createQuery("from NguoiDung where ten like: ten");
        query.setParameter("ten", "%" + ten + "%");
        return query.getResultList();
    }

    public NguoiDung getNDByMa(String ma){
        try{
            Query query = session.createQuery("from NguoiDung where ma =: ma");
            query.setParameter("ma", ma);
            return (NguoiDung) query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    public NguoiDung getNDByEmail(String email){
        try{
            Query query = session.createQuery("from NguoiDung where email =: email");
            query.setParameter("email", email);
            return (NguoiDung) query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    public NguoiDung getNDBySDT(String sdt){
        try{
            Query query = session.createQuery("from NguoiDung where sdt =: sdt");
            query.setParameter("sdt", sdt);
            return (NguoiDung) query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }


    public Boolean them(NguoiDung nguoiDung){
        try{
            transaction = session.beginTransaction();

            session.save(nguoiDung);

            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public Boolean sua(NguoiDung nguoiDung){
        try{
            transaction = session.beginTransaction();

            session.update(nguoiDung);

            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public Boolean xoa(NguoiDung nguoiDung){
        try{
            transaction = session.beginTransaction();

            session.delete(nguoiDung);

            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }
}
