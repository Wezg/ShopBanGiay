/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package edu.poly.shopbangiay.view;

import edu.poly.shopbangiay.model.ChiTietHoaDon;
import edu.poly.shopbangiay.model.HoaDon;
import edu.poly.shopbangiay.model.Voucher;
import edu.poly.shopbangiay.raven.datechooser.DateChooser;
import edu.poly.shopbangiay.service.CTHDService;
import edu.poly.shopbangiay.service.HoaDonService;
import edu.poly.shopbangiay.service.VCService;
import edu.poly.shopbangiay.service.impl.CTHDServiceImpl;
import edu.poly.shopbangiay.service.impl.HoaDonServiceImpl;

import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import edu.poly.shopbangiay.service.impl.VCServiceImpl;
import java.awt.BorderLayout;
import table.TableCustom;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Quang
 */
public class HoaDonUI extends javax.swing.JPanel {

    /**
     * Creates new form HoaDonUIHoaDon
     */
    private HoaDonService hoaDonService = new HoaDonServiceImpl();
    private CTHDService cthdService = new CTHDServiceImpl();
    private DefaultTableModel defaultTableModel;
    private DefaultComboBoxModel defaultComboBoxModel;

    private DateChooser dateChooser = new DateChooser();
    private DateChooser dateChooser1 = new DateChooser();

    DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    public HoaDonUI() {
        initComponents();
        
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
        TableCustom.apply(jScrollPane2, TableCustom.TableType.DEFAULT);
        loadHD(hoaDonService.timKiem(txtTim.getText()));
        loadCBX_TT();

        dateChooser.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        dateChooser1.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        dateChooser.setTextField(txtTuNgay);
        dateChooser1.setTextField(txtDenNgay);
    }
    
    public void loadCBX_TT() {
        defaultComboBoxModel = (DefaultComboBoxModel) cbxTT.getModel();
        defaultComboBoxModel.removeAllElements();
        List<String> listTT = new ArrayList<>();
        listTT.add("Tất cả");
        listTT.add("Chưa thanh toán");
        listTT.add("Đã thanh toán");

        for (String s : listTT) {
            defaultComboBoxModel.addElement(s);
        }
    }

    public void loadHD(List<HoaDon> list){
        defaultTableModel = (DefaultTableModel) tblHD.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;
        for (HoaDon hd : list) {
            defaultTableModel.addRow(new Object[]{
                    stt++,
                    hd.getMa(),
                    hd.getNguoiDung().getTen(),
                    hd.getKhachHang().getTen(),
                    hd.getVoucher().getPhanTramGiam(),
                    hd.getNgayTao(),
                    hd.getTinhTrang() == 0 ? "Chưa thanh toán" : "Đã thanh toán",
                    hd.getNgayTT()
            });
        }
    }

    public void loadHD_Date(List<HoaDon> list){
        defaultTableModel = (DefaultTableModel) tblHD.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;
        for (HoaDon hd : list) {
            defaultTableModel.addRow(new Object[]{
                    stt++,
                    hd.getMa(),
                    hd.getNguoiDung().getTen(),
                    hd.getKhachHang().getTen(),
                    hd.getVoucher().getPhanTramGiam(),
                    hd.getNgayTao(),
                    hd.getTinhTrang() == 0 ? "Chưa thanh toán" : "Đã thanh toán",
                    hd.getNgayTT()
            });
        }
    }

    public void loadCT(List<ChiTietHoaDon> list){
        defaultTableModel = (DefaultTableModel) tblCT.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;
        for (ChiTietHoaDon cthd : list) {
            defaultTableModel.addRow(new Object[]{
                    stt++,
                    cthd.getChiTietSanPham().getSanPham().getMa(),
                    cthd.getChiTietSanPham().getSanPham().getTen(),
                    cthd.getSoLuong(),
                    formatter.format(cthd.getChiTietSanPham().getGiaBan()) + " VNĐ",
                    formatter.format(cthd.getSoLuong() * cthd.getChiTietSanPham().getGiaBan()) + " VNĐ"
            });
        }
    }
    
    public List<HoaDon> locHD() {
        List<HoaDon> list;
        int index = cbxTT.getSelectedIndex();
        if (index == 0) {
            list = hoaDonService.timKiem("");
        } else if (index == 1) {
            list = hoaDonService.locTT(0);
        } else {
            list = hoaDonService.locTT(1);
        }
        return list;
    }
    
    public Double tongTien() {
        double sum = 0.0;
        int row = tblHD.getSelectedRow();
        HoaDon hoaDon = locHD().get(row);
        
        for (int i = 0; i < tblCT.getRowCount() ; ) {
            ChiTietHoaDon cthd = cthdService.getCTHDByMaHD(hoaDon.getMa()).get(i);

            sum += (cthd.getSoLuong() * cthd.getDonGia());
            i++;
        }
        return sum;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tableScrollButton2 = new table.TableScrollButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHD = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        tableScrollButton1 = new table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCT = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbGG = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbTT = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtTim = new textfield.TextField();
        txtTuNgay = new textfield.TextField();
        txtDenNgay = new textfield.TextField();
        cbxTT = new combo_suggestion.ComboBoxSuggestion();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tableScrollButton2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã HD", "Nhân viên", "Khách hàng", "Giảm giá (%)", "Ngày tạo", "Tình trạng", "Ngày thanh toán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHD);

        tableScrollButton2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tableScrollButton1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblCT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblCT);

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Tổng tiền:");

        lbTongTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTongTien.setForeground(new java.awt.Color(255, 0, 102));
        lbTongTien.setText("0");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Giảm giá:");

        lbGG.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbGG.setForeground(new java.awt.Color(255, 0, 102));
        lbGG.setText("0");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Thành tiền:");

        lbTT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTT.setForeground(new java.awt.Color(255, 0, 102));
        lbTT.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(lbGG, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(lbTT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(643, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbTongTien)
                    .addComponent(jLabel4)
                    .addComponent(lbGG)
                    .addComponent(jLabel6)
                    .addComponent(lbTT))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("QUẢN LÝ HÓA ĐƠN");

        txtTim.setLabelText("Tìm kiếm hóa đơn");
        txtTim.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimCaretUpdate(evt);
            }
        });

        txtTuNgay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTuNgay.setLabelText("Từ ngày");
        txtTuNgay.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTuNgayCaretUpdate(evt);
            }
        });

        txtDenNgay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtDenNgay.setLabelText("Đến ngày");
        txtDenNgay.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtDenNgayCaretUpdate(evt);
            }
        });

        cbxTT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTTItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbxTT, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(14, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbxTT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDMouseClicked
        // TODO add your handling code here:
        int row = tblHD.getSelectedRow();
        HoaDon hoaDon = hoaDonService.timKiem(txtTim.getText()).get(row);
        loadCT(cthdService.getCTHDByMaHD(hoaDon.getMa()));
        
        lbTongTien.setText(formatter.format(tongTien()) + " VNĐ");
        Voucher voucher = hoaDon.getVoucher();
        lbGG.setText(formatter.format(tongTien() / 100 * voucher.getPhanTramGiam()) + " VNĐ");
        lbTT.setText(formatter.format(tongTien() - (tongTien() /100 * voucher.getPhanTramGiam())) + " VNĐ");
    }//GEN-LAST:event_tblHDMouseClicked

    private void txtTimCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimCaretUpdate
        // TODO add your handling code here:
        String tim = txtTim.getText();
        List<HoaDon> list = hoaDonService.timKiem(tim);
        loadHD(list);
    }//GEN-LAST:event_txtTimCaretUpdate

    private void txtTuNgayCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTuNgayCaretUpdate
        // TODO add your handling code here:
        if (!txtDenNgay.getText().isEmpty() && !txtTuNgay.getText().isEmpty()){
            Date date = Date.valueOf(txtTuNgay.getText());
            Date toDate = Date.valueOf(txtDenNgay.getText());
            loadHD_Date(hoaDonService.locDate(date, toDate));
        }
    }//GEN-LAST:event_txtTuNgayCaretUpdate

    private void txtDenNgayCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDenNgayCaretUpdate
        // TODO add your handling code here:
        if (!txtDenNgay.getText().isEmpty() && !txtTuNgay.getText().isEmpty()){
            Date date = Date.valueOf(txtTuNgay.getText());
            Date toDate = Date.valueOf(txtDenNgay.getText());
            loadHD_Date(hoaDonService.locDate(date, toDate));
        }
    }//GEN-LAST:event_txtDenNgayCaretUpdate

    private void cbxTTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTTItemStateChanged
        // TODO add your handling code here:
        if (cbxTT.getSelectedIndex() == 0) {
            loadHD(hoaDonService.getList());
        } else if (cbxTT.getSelectedIndex() == 1) {
            List<HoaDon> list = hoaDonService.locTT(0);
            loadHD(list);
        } else {
            List<HoaDon> list = hoaDonService.locTT(1);
            loadHD(list);
        }
    }//GEN-LAST:event_cbxTTItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private combo_suggestion.ComboBoxSuggestion cbxTT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbGG;
    private javax.swing.JLabel lbTT;
    private javax.swing.JLabel lbTongTien;
    private table.TableScrollButton tableScrollButton1;
    private table.TableScrollButton tableScrollButton2;
    private javax.swing.JTable tblCT;
    private javax.swing.JTable tblHD;
    private textfield.TextField txtDenNgay;
    private textfield.TextField txtTim;
    private textfield.TextField txtTuNgay;
    // End of variables declaration//GEN-END:variables
}
