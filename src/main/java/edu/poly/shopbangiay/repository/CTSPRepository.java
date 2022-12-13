package edu.poly.shopbangiay.repository;

import edu.poly.shopbangiay.Ultilities.Hibernate;
import edu.poly.shopbangiay.model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class CTSPRepository {

    Session session = Hibernate.getFACTORY().openSession();
    Transaction transaction = null;

    public List<ChiTietSanPham> getList(){
        Query query = session.createQuery("from ChiTietSanPham ");
        return query.getResultList();
    }


    public List<ChiTietSanPham> timKiem(String ten){
        Query query = session.createQuery("from ChiTietSanPham ctsp where ctsp.sanPham.ten like: ten or ctsp.sanPham.ma like: ma");
        query.setParameter("ten", "%" + ten + "%");
        query.setParameter("ma", "%" + ten + "%");
        return query.getResultList();
    }

    public ChiTietSanPham getCTSPByMaSP(String maSP){
        try{
            Query query = session.createQuery("from ChiTietSanPham ctsp where ctsp.sanPham.ma =: ma ");
            query.setParameter("ma", maSP);
            return (ChiTietSanPham) query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    public List<SanPham> listSP(){
        Query query = session.createQuery("from SanPham ");
        return query.getResultList();
    }

    public List<Loai> listLoai(){
        Query query = session.createQuery("from Loai ");
        return query.getResultList();
    }

    public List<NSX> listNSX(){
        Query query = session.createQuery("from NSX ");
        return query.getResultList();
    }

    public List<Size> listSize(){
        Query query = session.createQuery("from Size ");
        return query.getResultList();
    }

    public List<MauSac> listMS(){
        Query query = session.createQuery("from MauSac ");
        return query.getResultList();
    }
    public List<ChatLieu> listCL(){
        Query query = session.createQuery("from ChatLieu ");
        return query.getResultList();
    }


    public Boolean them(ChiTietSanPham ctsp){
        try{
            transaction = session.beginTransaction();

            session.saveOrUpdate(ctsp);

            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public Boolean sua(ChiTietSanPham ctsp){
        try{
            transaction = session.beginTransaction();

            session.update(ctsp);

            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public Boolean xoa(ChiTietSanPham ctsp){
        try{
            transaction = session.beginTransaction();

            session.delete(ctsp);

            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }
}
