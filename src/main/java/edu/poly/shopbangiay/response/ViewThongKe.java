/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poly.shopbangiay.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewThongKe {

    private Long soDH;
    private Float doanhThu;

    public ViewThongKe() {
    }

    public ViewThongKe(Long soDH, Float doanhThu) {
        this.soDH = soDH;
        this.doanhThu = doanhThu;
    }
}
