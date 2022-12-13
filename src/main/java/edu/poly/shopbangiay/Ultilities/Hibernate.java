package edu.poly.shopbangiay.Ultilities;

import edu.poly.shopbangiay.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class Hibernate {
    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=shopBanGiay");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "18123");
        properties.put(Environment.SHOW_SQL, "true");
//        properties.put(Environment.HBM2DDL_AUTO, "update");
        conf.setProperties(properties);
        conf.addAnnotatedClass(ChatLieu.class);
        conf.addAnnotatedClass(ChiTietHoaDon.class);
        conf.addAnnotatedClass(ChiTietSanPham.class);
        conf.addAnnotatedClass(ChucVu.class);
        conf.addAnnotatedClass(HoaDon.class);
        conf.addAnnotatedClass(KhachHang.class);
        conf.addAnnotatedClass(Loai.class);
        conf.addAnnotatedClass(MauSac.class);
        conf.addAnnotatedClass(NguoiDung.class);
        conf.addAnnotatedClass(NSX.class);
        conf.addAnnotatedClass(SanPham.class);
        conf.addAnnotatedClass(Size.class);
        conf.addAnnotatedClass(Voucher.class);


        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        getFACTORY();
    }
}
