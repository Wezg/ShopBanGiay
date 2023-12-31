/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package edu.poly.shopbangiay.view;

import edu.poly.shopbangiay.Ultilities.ReadExcel;
import edu.poly.shopbangiay.model.*;
import edu.poly.shopbangiay.service.SanPhamService;
import edu.poly.shopbangiay.service.impl.SanPhamServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import table.TableCustom;

/**
 * @author Quang
 */
public class SanPhamUI extends javax.swing.JDialog {

    /**
     * Creates new form SanPhamUI
     */
    private SanPhamService sanPhamService = new SanPhamServiceImpl();
    private DefaultTableModel defaultTableModel;

    public SanPhamUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Sản phẩm");
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        loadData(sanPhamService.timKiem(txtTim.getText()));
    }

    public String genMaSP() {
        for (int i = 0; i < sanPhamService.getList().size() + 1; i++) {
            String ma = "SP" + i;
            if (sanPhamService.getSPByMa(ma) == null) {
                return ma;
            }
        }
        return null;
    }

    public void loadData(List<SanPham> list) {
        defaultTableModel = (DefaultTableModel) tblSP.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;

        for (SanPham sp : list) {
            defaultTableModel.addRow(new Object[]{
                    stt++,
                    sp.getMa(),
                    sp.getTen()
            });
        }
    }

    public void loadData_(List<SanPham> list) {
        defaultTableModel = (DefaultTableModel) tblSP.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;

        for (SanPham sp : list) {
            defaultTableModel.addRow(new Object[]{
                    stt++,
                    sp.getMa(),
                    sp.getTen()
            });
        }
    }

    public boolean check() {
        if (txtTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên sản phẩm");
            return false;
        }
        return true;
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
        txtMa = new textfield.TextField();
        txtTen = new textfield.TextField();
        btnThem = new edu.poly.shopbangiay.raven.button.Button();
        btnSua = new edu.poly.shopbangiay.raven.button.Button();
        btnXoa = new edu.poly.shopbangiay.raven.button.Button();
        txtTim = new textfield.TextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnClose = new edu.poly.shopbangiay.raven.button.Button();
        btnNhap = new edu.poly.shopbangiay.raven.button.Button();
        btnXuat = new edu.poly.shopbangiay.raven.button.Button();
        tableScrollButton1 = new table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSP = new dynamic_subjtable.TableCustom();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 153), 1, true));

        txtMa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMa.setLabelText("Mã");

        txtTen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTen.setLabelText("Tên");

        btnThem.setBackground(new java.awt.Color(153, 255, 51));
        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 255, 153));
        btnSua.setText("Sửa");
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 0, 153));
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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("SẢN PHẨM");

        btnClose.setBackground(new java.awt.Color(204, 204, 204));
        btnClose.setText("Close");
        btnClose.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnNhap.setBackground(new java.awt.Color(204, 204, 255));
        btnNhap.setText("Nhập");
        btnNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnNhapActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnXuat.setBackground(new java.awt.Color(204, 204, 255));
        btnXuat.setText("Xuất");
        btnXuat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatActionPerformed(evt);
            }
        });

        tblSP.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "STT", "Mã", "Tên"
                }
        ));
        tblSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSP);

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(42, 42, 42)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 14, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked
        // TODO add your handling code here:
        int row = tblSP.getSelectedRow();
        SanPham sanPham = sanPhamService.timKiem(txtTim.getText()).get(row);
        txtMa.setText(sanPham.getMa());
        txtTen.setText(sanPham.getTen());
    }//GEN-LAST:event_tblSPMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (check()) {
            for (SanPham sp : sanPhamService.getList()) {
                if (txtTen.getText().equals(sp.getTen())) {
                    JOptionPane.showMessageDialog(this, "Tên sản phẩm đã tồn tại");
                    return;
                } else {
                    SanPham sanPham = new SanPham();
                    sanPham.setMa(genMaSP());
                    sanPham.setTen(txtTen.getText());

                    if (sanPhamService.them(sanPham)) {
                        JOptionPane.showMessageDialog(this, "Thêm thành công sản phẩm " + sanPham.getMa());
                        loadData(sanPhamService.timKiem(txtTim.getText()));
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm thất bại");
                    }
                }
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (check()) {
            int row = tblSP.getSelectedRow();
            SanPham sanPham = sanPhamService.timKiem(txtTim.getText()).get(row);
            sanPham.setMa(txtMa.getText());
            sanPham.setTen(txtTen.getText());
            int input = JOptionPane.showConfirmDialog(this, "Cập nhật sản phẩm này?");
            if (input == 0) {
                if (sanPhamService.sua(sanPham)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                    loadData(sanPhamService.timKiem(txtTim.getText()));
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
                }
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int row = tblSP.getSelectedRow();
        SanPham sanPham = sanPhamService.timKiem(txtTim.getText()).get(row);
        int input = JOptionPane.showConfirmDialog(this, "Xóa sản phẩm " + sanPham.getTen() + "?");
        if (input == 0) {
            if (sanPhamService.xoa(sanPham)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
                loadData(sanPhamService.timKiem(txtTim.getText()));
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại");
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtTimCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimCaretUpdate
        // TODO add your handling code here:
        String tim = txtTim.getText();
        List<SanPham> list = sanPhamService.timKiem(tim);
        loadData(list);
    }//GEN-LAST:event_txtTimCaretUpdate

    private void btnNhapActionPerformed(java.awt.event.ActionEvent evt) throws IOException {//GEN-FIRST:event_btnNhapActionPerformed
        // TODO add your handling code here:
        JFileChooser chonFile = new JFileChooser("/");
        chonFile.showOpenDialog(null);
        File file = chonFile.getSelectedFile();

        final String excelFilePath = file.getPath();
        final List<SanPham> listSP = ReadExcel.readExcel(excelFilePath);
        for (SanPham sanPham : listSP) {
            sanPhamService.them(sanPham);
        }
        JOptionPane.showMessageDialog(this, "Nhập file thành công");
        loadData(sanPhamService.timKiem(""));
    }//GEN-LAST:event_btnNhapActionPerformed

    private void btnXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(this, "Xuất danh sách sản phẩm này ?");
        if (input == 0) {
            List<SanPham> list = sanPhamService.getList();
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Sản phẩm");
            int rowNum = 0;
            Row titleRow = sheet.createRow(rowNum);

            Cell maSPTT = titleRow.createCell(0);
            maSPTT.setCellValue("Mã SP");

            Cell tenSPTT = titleRow.createCell(1);
            tenSPTT.setCellValue("Tên SP");

            for (SanPham sp : list) {
                rowNum++;
                Row row = sheet.createRow(rowNum);

                Cell maSP = row.createCell(0);
                maSP.setCellValue(sp.getMa());

                Cell tenSP = row.createCell(1);
                tenSP.setCellValue(sp.getTen());

            }

            FileOutputStream fos;
            try {
                fos = new FileOutputStream("sanPham.xlsx");
                workbook.write(fos);
                JOptionPane.showMessageDialog(this, "Ghi file thành công");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnXuatActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SanPhamUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SanPhamUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SanPhamUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SanPhamUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SanPhamUI dialog = new SanPhamUI(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private edu.poly.shopbangiay.raven.button.Button btnClose;
    private edu.poly.shopbangiay.raven.button.Button btnNhap;
    private edu.poly.shopbangiay.raven.button.Button btnSua;
    private edu.poly.shopbangiay.raven.button.Button btnThem;
    private edu.poly.shopbangiay.raven.button.Button btnXoa;
    private edu.poly.shopbangiay.raven.button.Button btnXuat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private table.TableScrollButton tableScrollButton1;
    private dynamic_subjtable.TableCustom tblSP;
    private textfield.TextField txtMa;
    private textfield.TextField txtTen;
    private textfield.TextField txtTim;
    // End of variables declaration//GEN-END:variables
}
