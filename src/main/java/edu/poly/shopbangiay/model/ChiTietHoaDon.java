package edu.poly.shopbangiay.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import play.libs.F;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class ChiTietHoaDon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "IDCTSP")
    private ChiTietSanPham chiTietSanPham;

    @ManyToOne
    @JoinColumn(name = "IDHD")
    private HoaDon hoaDon;

    @Column
    private Float donGia;
    @Column
    private Integer soLuong;

    public Float tongTien(){
        return soLuong * donGia;
    }
}
