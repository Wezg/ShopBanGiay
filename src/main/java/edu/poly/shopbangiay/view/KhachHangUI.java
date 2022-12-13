/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package edu.poly.shopbangiay.view;

import edu.poly.shopbangiay.model.KhachHang;
import edu.poly.shopbangiay.raven.datechooser.DateChooser;
import edu.poly.shopbangiay.service.KhachHangService;
import edu.poly.shopbangiay.service.impl.KhachHangServiceImpl;
import table.TableCustom;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Quang
 */
public class KhachHangUI extends javax.swing.JPanel {

    /**
     * Creates new form KhachHangUI
     */
    private KhachHangService khachHangService = new KhachHangServiceImpl();
    private DefaultTableModel defaultTableModel;
    String url;
    private DateChooser dateChooser = new DateChooser();
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public KhachHangUI() {
        initComponents();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
        loadData(khachHangService.getList());
        groupGT();
        dateChooser.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        dateChooser.setTextField(txtNgaySinh);
    }

    public void loadData(List<KhachHang> list) {
        defaultTableModel = (DefaultTableModel) tblKH.getModel();
        defaultTableModel.setRowCount(0);

        int stt = 1;
        for (KhachHang kh : list) {
            defaultTableModel.addRow(new Object[]{
                    stt++,
                    kh.getMa(),
                    kh.getTen(),
                    kh.getGioiTinh() == true ? "Nam" : "Nữ",
                    kh.getNgaySinh(),
                    kh.getSdt(),
                    kh.getEmail(),
                    kh.getDiaChi()
            });
        }
    }


    public String genMa() {
        for (int i = 0; i < khachHangService.getList().size() + 1; i++) {
            String ma = "KH" + i;
            if (khachHangService.getKHByMa(ma) == null) {
                return ma;
            }
        }
        return null;
    }

    ButtonGroup rdoGT = new ButtonGroup();

    public void groupGT() {
        rdoGT.add(rdoNam);
        rdoGT.add(rdoNu);
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
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        lbAnh = new javax.swing.JLabel();
        txtTen = new textfield.TextField();
        txtMa = new textfield.TextField();
        txtSDT = new textfield.TextField();
        txtNgaySinh = new textfield.TextField();
        txtDiaChi = new textfield.TextField();
        txtEmail = new textfield.TextField();
        jLabel3 = new javax.swing.JLabel();
        rdoNam = new radio_button.RadioButtonCustom();
        rdoNu = new radio_button.RadioButtonCustom();
        btnThem = new edu.poly.shopbangiay.raven.button.Button();
        btnSua = new edu.poly.shopbangiay.raven.button.Button();
        btnXoa = new edu.poly.shopbangiay.raven.button.Button();
        txtTim = new textfield.TextField();
        jPanel3 = new javax.swing.JPanel();
        tableScrollButton1 = new table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKH = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("QUẢN LÝ KHÁCH HÀNG");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lbAnh.setBackground(new java.awt.Color(255, 255, 255));
        lbAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAnh.setToolTipText("");
        lbAnh.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lbAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbAnhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );

        txtTen.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtTen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTen.setLabelText("Tên khách hàng");

        txtMa.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtMa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMa.setLabelText("Mã khách hàng");

        txtSDT.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtSDT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSDT.setLabelText("Số điên thoại");

        txtNgaySinh.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtNgaySinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtNgaySinh.setLabelText("Ngày sinh");

        txtDiaChi.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtDiaChi.setLabelText("Địa chỉ");

        txtEmail.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtEmail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtEmail.setLabelText("Email");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Giới tính:");

        rdoNam.setBackground(new java.awt.Color(204, 204, 204));
        rdoNam.setForeground(new java.awt.Color(102, 102, 102));
        rdoNam.setText("Nam");
        rdoNam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        rdoNu.setBackground(new java.awt.Color(204, 204, 204));
        rdoNu.setForeground(new java.awt.Color(102, 102, 102));
        rdoNu.setText("Nữ");
        rdoNu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        btnThem.setBackground(new java.awt.Color(153, 255, 153));
        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 255, 102));
        btnSua.setText("Sửa");
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 51, 153));
        btnXoa.setText("Xóa");
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        txtTim.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTim.setLabelText("Tìm kiếm");
        txtTim.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 419, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        tblKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã ", "Tên", "Giới tính", "Ngày sinh", "SDT", "Email", "Địa chỉ"
            }
        ));
        tblKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKH);

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        KhachHang khachHang = new KhachHang();
        khachHang.setMa(genMa());
        khachHang.setTen(txtTen.getText());
        boolean gioiTinh = true;
        if (rdoNu.isSelected()) {
            gioiTinh = false;
        }
        khachHang.setGioiTinh(gioiTinh);
        khachHang.setNgaySinh(Date.valueOf(txtNgaySinh.getText()));
        khachHang.setSdt(txtSDT.getText());
        khachHang.setEmail(txtEmail.getText());
        khachHang.setDiaChi(txtDiaChi.getText());
        khachHang.setAnh(url);
        if (khachHangService.them(khachHang)) {
            loadData(khachHangService.getList());
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int row = tblKH.getSelectedRow();
        KhachHang khachHang = khachHangService.getList().get(row);

        khachHang.setMa(txtMa.getText());
        khachHang.setTen(txtTen.getText());
        boolean gioiTinh = true;
        if (rdoNu.isSelected()) {
            gioiTinh = false;
        }
        khachHang.setGioiTinh(gioiTinh);
        khachHang.setNgaySinh(Date.valueOf(txtNgaySinh.getText()));
        khachHang.setSdt(txtSDT.getText());
        khachHang.setEmail(txtEmail.getText());
        khachHang.setDiaChi(txtDiaChi.getText());
        khachHang.setAnh(url);
        if (khachHangService.sua(khachHang)) {
            loadData(khachHangService.getList());
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int row = tblKH.getSelectedRow();
        KhachHang khachHang = khachHangService.getList().get(row);

        if (khachHangService.xoa(khachHang)) {
            loadData(khachHangService.getList());
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtTimCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimCaretUpdate
        // TODO add your handling code here:
        String timKH = txtTim.getText();
        List<KhachHang> list = khachHangService.timKiem(timKH);
        loadData(list);
    }//GEN-LAST:event_txtTimCaretUpdate

    private void tblKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKHMouseClicked
        // TODO add your handling code here:
        int row = tblKH.getSelectedRow();
        KhachHang khachHang = khachHangService.getList().get(row);
        txtMa.setText(khachHang.getMa());
        txtTen.setText(khachHang.getTen());
        if (khachHang.getGioiTinh()) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtNgaySinh.setText(khachHang.getNgaySinh().toString());
        txtSDT.setText(khachHang.getSdt());
        txtEmail.setText(khachHang.getEmail());
        txtDiaChi.setText(khachHang.getDiaChi());
        lbAnh.setIcon(ResizeImage(new ImageIcon("image/KH/" + khachHang.getAnh()).toString()));
    }//GEN-LAST:event_tblKHMouseClicked

    private void lbAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAnhMouseClicked
        // TODO add your handling code here:
        try {
            JFileChooser chonFile = new JFileChooser("image/");
            chonFile.showOpenDialog(null);
            File anh = chonFile.getSelectedFile();
            url = anh.getAbsolutePath();

            lbAnh.setIcon(ResizeImage(url));

            File file = new File(url);
            file.renameTo(new File("image/KH/" + file.getName()));
            url = file.getName();

        } catch (Exception e) {
        }
    }//GEN-LAST:event_lbAnhMouseClicked

    public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon myImage = new ImageIcon(ImagePath);
        Image img = myImage.getImage();
        Image newImg = img.getScaledInstance(lbAnh.getWidth(), lbAnh.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private edu.poly.shopbangiay.raven.button.Button btnSua;
    private edu.poly.shopbangiay.raven.button.Button btnThem;
    private edu.poly.shopbangiay.raven.button.Button btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbAnh;
    private radio_button.RadioButtonCustom rdoNam;
    private radio_button.RadioButtonCustom rdoNu;
    private table.TableScrollButton tableScrollButton1;
    private javax.swing.JTable tblKH;
    private textfield.TextField txtDiaChi;
    private textfield.TextField txtEmail;
    private textfield.TextField txtMa;
    private textfield.TextField txtNgaySinh;
    private textfield.TextField txtSDT;
    private textfield.TextField txtTen;
    private textfield.TextField txtTim;
    // End of variables declaration//GEN-END:variables
}

