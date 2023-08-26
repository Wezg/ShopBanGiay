package edu.poly.shopbangiay.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
public class ChiTietSanPham implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "IDSP")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "IDNSX")
    private NSX nsx;

    @ManyToOne
    @JoinColumn(name = "IDMS")
    private MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "IDCL")
    private ChatLieu chatLieu;

    @ManyToOne
    @JoinColumn(name = "IDSize")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "IDLoai")
    private Loai loai;

    @Column
    private Integer soLuong;
    @Column
    private Float giaNhap;
    @Column
    private Float giaBan;
    @Column
    private String hinhAnh;
    @Column
    private Boolean tinhTrang;
    @Column
    private String moTa;

    @OneToMany(mappedBy = "chiTietSanPham", fetch = FetchType.LAZY)
    private List<ChiTietHoaDon> chiTietHoaDonList;

}
