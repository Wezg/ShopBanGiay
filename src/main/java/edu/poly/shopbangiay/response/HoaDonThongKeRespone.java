package edu.poly.shopbangiay.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import edu.poly.shopbangiay.model.ChiTietHoaDon;
import edu.poly.shopbangiay.model.HoaDon;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class HoaDonThongKeRespone {

    private String ma;
    private int sl;
    private BigDecimal tongTien;
    private Date ngayMua;

    public HoaDonThongKeRespone(HoaDon h, List<ChiTietHoaDon> lsthdct) {
        this.ma = h.getMa();
        int soluong = 0;
        for (ChiTietHoaDon s : lsthdct) {
            soluong += s.getSoLuong();
        }
        this.sl = soluong;
        this.tongTien = BigDecimal.valueOf(h.getTongTien());
        this.ngayMua = h.getNgayTT();
    }
}
