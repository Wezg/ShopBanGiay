package edu.poly.shopbangiay.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class HoaDon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "IDKH")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "IDND")
    private NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "IDVC")
    private Voucher voucher;

    @Column(unique = true, nullable = false)
    private String ma;
    @Column
    private Date ngayTao;
    @Column
    private Float tongTien;
    @Column
    private Float thanhTien;
    @Column
    private Date ngayTT;
    @Column
    private Integer tinhTrang;

    @OneToMany(mappedBy = "hoaDon", fetch = FetchType.LAZY)
    private List<ChiTietHoaDon> chiTietHoaDonList;

}
