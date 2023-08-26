package edu.poly.shopbangiay.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class KhachHang implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String ma;

    @Column(length = 50)
    @Nationalized
    private String ten;

    @Column
    private Boolean gioiTinh;

    @Column
    private Date ngaySinh;
    @Column
    private String sdt;

    @Column
    @Nationalized
    private String diaChi;

    @Column
    private String email;

    @Column
    private String anh;

    @OneToMany(mappedBy = "khachHang", fetch = FetchType.LAZY)
    private List<HoaDon> hoaDonList;

    @Override
    public String toString() {
        return sdt + '-' + ten;
    }
}
