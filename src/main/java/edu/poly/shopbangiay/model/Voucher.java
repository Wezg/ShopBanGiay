package edu.poly.shopbangiay.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class Voucher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String ma;

    @Column
    @Nationalized
    private String ten;

    @Column
    private Integer phanTramGiam;

    @Column
    private Date ngayTao;

    @Column
    private Date ngayBD;

    @Column
    private Date ngayKT;

    @Column
    private String moTa;

    @Column
    private Boolean trangThai;

    @OneToMany(mappedBy = "voucher", fetch = FetchType.LAZY)
    private List<HoaDon> hoaDonList;

    @Override
    public String toString() {
        return ten;
    }
}
