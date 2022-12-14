package edu.poly.shopbangiay.repository;

import edu.poly.shopbangiay.Ultilities.Hibernate;
import edu.poly.shopbangiay.model.HoaDon;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class HoaDonRepository {

    Session session = Hibernate.getFACTORY().openSession();
    Transaction transaction = null;

    public List<HoaDon> getList() {
        Query query = session.createQuery("from HoaDon order by ngayTao desc");
        return query.getResultList();
    }

    public List<HoaDon> timKiem(String ma) {
        Query query = session.createQuery("from HoaDon where ma like: ma order by ngayTao desc");
        query.setParameter("ma", "%" + ma + "%");
        return query.getResultList();
    }

    public List<HoaDon> locTT(Integer tt) {
        Query query = session.createQuery("from HoaDon where tinhTrang =: tt order by ngayTao desc");
        query.setParameter("tt", tt);
        return query.getResultList();
    }

    public HoaDon getHDByMa(String ma) {
        try {
            Query query = session.createQuery("from HoaDon where ma=: ma order by ngayTao desc");
            query.setParameter("ma", ma);
            return (HoaDon) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Double doanhThuNgay(Date date) {
        Query query = session.createQuery("select sum(thanhTien) from HoaDon where day (ngayTT) =: date");
        query.setParameter("date", date);
        return (Double) query.getSingleResult();
    }

    public List<HoaDon> locDate(Date date, Date toDate){
        Query query = session.createQuery("select hd from HoaDon hd where hd.ngayTao between :date and :toDate");
        query.setParameter("date", date);
        query.setParameter("toDate", toDate);
        return query.getResultList();
    }

    public Double doanhThuHomNay(int ngay, int thang, int nam){
        Query query = session.createQuery("select sum (tongTien) from HoaDon where day(ngayTT) =: ngay and month (ngayTT) =: thang and year (ngayTT) =: nam");
        query.setParameter("ngay", ngay);
        query.setParameter("thang", thang);
        query.setParameter("nam", nam);
        return (Double) query.getSingleResult();
    }

    public Double doanhThuThangNay(int thang, int nam){
        Query query = session.createQuery("select sum (tongTien) from HoaDon where month (ngayTT) =: thang and year (ngayTT) =: nam");
        query.setParameter("thang", thang);
        query.setParameter("nam", nam);
        return (Double) query.getSingleResult();
    }

    public Double doanhThuNamNay(int nam){
        Query query = session.createQuery("select sum (tongTien) from HoaDon where year (ngayTT) =: nam");
        query.setParameter("nam", nam);
        return (Double) query.getSingleResult();
    }

//    public static void main(String[] args) {
//        HoaDonRepository hoaDonRepository = new HoaDonRepository();
//        int ngay = LocalDate.now().getDayOfMonth();
//        System.out.println(ngay);
//        int thang = LocalDate.now().getMonthValue();
//        System.out.println(thang);
//        int nam = LocalDate.now().getYear();
//        System.out.println(nam);
//        System.out.println(hoaDonRepository.doanhThuHomNay(ngay, thang, nam));
//        System.out.println(hoaDonRepository.doanhThuThangNay(thang, nam));
//        System.out.println(hoaDonRepository.doanhThuNamNay( nam));
//    }
//
    public Long HDngay(int ngay, int thang, int nam){
        Query query = session.createQuery("select count(ma) from HoaDon where day(ngayTT) =: ngay and month(ngayTT) =: thang and year(ngayTT) =: nam");
        query.setParameter("ngay", ngay);
        query.setParameter("thang", thang);
        query.setParameter("nam", nam);
        return (Long) query.getSingleResult();
    }

    public Long HDthang(int thang, int nam){
        Query query = session.createQuery("select count(ma) from HoaDon where month(ngayTT) =: thang and year(ngayTT) =: nam");

        query.setParameter("thang", thang);
        query.setParameter("nam", nam);
        return (Long) query.getSingleResult();
    }

    public Long HDnam(int nam){
        Query query = session.createQuery("select count(ma) from HoaDon where year(ngayTT) =: nam");

        query.setParameter("nam", nam);
        return (Long) query.getSingleResult();
    }

//    public static void main(String[] args) {
//        int ngay = LocalDate.now().getDayOfMonth();
//        System.out.println(ngay);
//        int thang = LocalDate.now().getMonthValue();
//        System.out.println(thang);
//        int nam = LocalDate.now().getYear();
//        System.out.println(nam);
//        HoaDonRepository hoaDonRepository = new HoaDonRepository();
//        System.out.println(hoaDonRepository.HDngay(ngay, thang, nam));
//    }

    public Object getDTThang(){
        Query query = session.createQuery("select month(ngayTT), sum(thanhTien) from HoaDon where tinhTrang = 1 group by month(ngayTT)");
        return query.getResultList();
    }

    public static void main(String[] args) {
        HoaDonRepository hoaDonRepository = new HoaDonRepository();
        System.out.println(hoaDonRepository.getDTThang().toString());
    }

    public List<HoaDon> getHDByDate(Date from, Date to) {
        List<HoaDon> list = new ArrayList<>();
        try {
            Query query = session.createQuery(" from HoaDon where ngayTT >= :from and ngayTT <= :to and tinhTrang = 1");
            query.setParameter("from", from);
            query.setParameter("to", to);
            if (query.getResultList() != null && !query.getResultList().isEmpty()) {
                list = query.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }




    public Boolean them(HoaDon hoaDon) {
        try {
            transaction = session.beginTransaction();

            session.save(hoaDon);

            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public Boolean sua(HoaDon hoaDon) {
        try {
            transaction = session.beginTransaction();

            session.update(hoaDon);

            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public Boolean xoa(HoaDon hoaDon) {
        try {
            transaction = session.beginTransaction();

            session.delete(hoaDon);

            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }
}
