/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.shopbangiay.response;

import java.math.BigDecimal;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewThongKeNgay {
    private int thang;
    private Date ngayTao;
    private Float doanhThu;
    private Long soHD;

    public ViewThongKeNgay() {
    }

    public ViewThongKeNgay(Date ngayTao, Float doanhThu, Long soHD) {
        this.ngayTao = ngayTao;
        this.doanhThu = doanhThu;
        this.soHD = soHD;
    }

    public ViewThongKeNgay(Float doanhThu, Long soHD) {
        this.doanhThu = doanhThu;
        this.soHD = soHD;
    }

    public ViewThongKeNgay(int thang, Float doanhThu, Long soHD) {
        this.thang = thang;
        this.doanhThu = doanhThu;
        this.soHD = soHD;
    }
}
