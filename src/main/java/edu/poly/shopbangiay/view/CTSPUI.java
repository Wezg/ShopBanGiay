/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package edu.poly.shopbangiay.view;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import edu.poly.shopbangiay.model.*;
import edu.poly.shopbangiay.service.*;
import edu.poly.shopbangiay.service.impl.*;

import java.awt.Image;
import java.io.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import table.TableCustom;

/**
 * @author Quang
 */
public class CTSPUI extends javax.swing.JPanel {

    /**
     * Creates new form CTSPUI
     */
    private DefaultTableModel defaultTableModel;
    private DefaultComboBoxModel defaultComboBoxModel;
    private CTSPService ctspService = new CTSPServiceImpl();

    private SanPhamService sanPhamService = new SanPhamServiceImpl();
    private LoaiService loaiService = new LoaiServiceImpl();
    private SizeService sizeService = new SizeServiceImpl();
    private ChatLieuService chatLieuService = new ChatLieuServiceImpl();
    private MauSacService mauSacService = new MauSacServiceImpl();
    private NSXService nsxService = new NSXServiceImpl();
    String url = null;

    public CTSPUI() {
        initComponents();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
        groupTT();
        loadData(ctspService.timKiem(txtTim.getText()));
        loadCBX_Loai(ctspService.listLoai());
        loadCBX_Size(ctspService.listSize());
        loadCBX_CL(ctspService.listCL());
        loadCBX_MS(ctspService.listMS());
        loadCBX_NSX(ctspService.listNSX());
        loadCBX_SP(ctspService.listSP());
    }

    public void loadData(List<ChiTietSanPham> list) {
        defaultTableModel = (DefaultTableModel) tblCTSP.getModel();
        defaultTableModel.setRowCount(0);

        int stt = 1;
        for (ChiTietSanPham ctsp : list) {
            defaultTableModel.addRow(new Object[]{
                    stt++,
                    ctsp.getSanPham().getMa(),
                    ctsp.getSanPham().getTen(),
                    ctsp.getLoai().getTen(),
                    ctsp.getGiaNhap(),
                    ctsp.getGiaBan(),
                    ctsp.getSoLuong(),
                    ctsp.getTinhTrang() == true ? "Đang kinh doanh" : "ngừng kinh doanh"
            });
        }
    }

    public void clearForm() {
        lbAnh.setIcon(null);
        txtMota.setText("");
        txtGiaNhap.setText("");
        txtTen.setText("");
        cbxSP.setSelectedIndex(0);
        txtGiaBan.setText("");
        txtSoLuong.setText("");
        cbxCL.setSelectedIndex(0);
        cbxMS.setSelectedIndex(0);
        cbxSize.setSelectedIndex(0);
        cbxLoai.setSelectedIndex(0);
        cbxNSX.setSelectedIndex(0);
    }

    ButtonGroup rdoTrangThai = new ButtonGroup();

    public void groupTT() {
        rdoTrangThai.add(rdoOn);
        rdoTrangThai.add(rdoOff);
    }

    public void loadCBX_SP(List<SanPham> list) {
        defaultComboBoxModel = (DefaultComboBoxModel) cbxSP.getModel();
        defaultComboBoxModel.removeAllElements();
        defaultComboBoxModel.addElement("   ");

        for (SanPham sp : list) {
            defaultComboBoxModel.addElement(sp);
        }
    }


    public void loadCBX_Loai(List<Loai> list) {
        defaultComboBoxModel = (DefaultComboBoxModel) cbxLoai.getModel();
        defaultComboBoxModel.removeAllElements();

        for (Loai loai : list) {
            defaultComboBoxModel.addElement("   ");
            defaultComboBoxModel.addElement(loai);
        }
    }

    public void loadCBX_Size(List<Size> list) {
        defaultComboBoxModel = (DefaultComboBoxModel) cbxSize.getModel();
        defaultComboBoxModel.removeAllElements();

        for (Size size : list) {
            defaultComboBoxModel.addElement("   ");
            defaultComboBoxModel.addElement(size);
        }
    }

    public void loadCBX_CL(List<ChatLieu> list) {
        defaultComboBoxModel = (DefaultComboBoxModel) cbxCL.getModel();
        defaultComboBoxModel.removeAllElements();

        for (ChatLieu chatLieu : list) {
            defaultComboBoxModel.addElement("   ");
            defaultComboBoxModel.addElement(chatLieu);
        }
    }

    public void loadCBX_MS(List<MauSac> list) {
        defaultComboBoxModel = (DefaultComboBoxModel) cbxMS.getModel();
        defaultComboBoxModel.removeAllElements();

        for (MauSac mauSac : list) {
            defaultComboBoxModel.addElement("   ");
            defaultComboBoxModel.addElement(mauSac);
        }
    }

    public void loadCBX_NSX(List<NSX> list) {
        defaultComboBoxModel = (DefaultComboBoxModel) cbxNSX.getModel();
        defaultComboBoxModel.removeAllElements();

        for (NSX nsx : list) {
            defaultComboBoxModel.addElement("   ");
            defaultComboBoxModel.addElement(nsx);
        }
    }

//    public String genMaSP() {
//        for (int i = 0; i < sanPhamService.getList().size() + 1; i++) {
//            String ma = "SP" + i;
//            if (sanPhamService.getSPByMa(ma) == null) {
//                return ma;
//            }
//        }
//        return null;
//    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel15 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lbAnh = new javax.swing.JLabel();
        txtTen = new textfield.TextField();
        txtSoLuong = new textfield.TextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtGiaNhap = new textfield.TextField();
        txtGiaBan = new textfield.TextField();
        cbxLoai = new combobox.Combobox();
        cbxNSX = new combobox.Combobox();
        cbxSize = new combobox.Combobox();
        cbxMS = new combobox.Combobox();
        cbxCL = new combobox.Combobox();
        textAreaScroll1 = new textarea.TextAreaScroll();
        txtMota = new textarea.TextArea();
        rdoOn = new radio_button.RadioButtonCustom();
        jLabel2 = new javax.swing.JLabel();
        rdoOff = new radio_button.RadioButtonCustom();
        btnThem = new edu.poly.shopbangiay.raven.button.Button();
        btnSua = new edu.poly.shopbangiay.raven.button.Button();
        btnXoa = new edu.poly.shopbangiay.raven.button.Button();
        btnQL = new edu.poly.shopbangiay.raven.button.Button();
        txtTim = new textfield.TextField();
        btnNhapFile = new edu.poly.shopbangiay.raven.button.Button();
        btnXuatFile = new edu.poly.shopbangiay.raven.button.Button();
        btnGenQR = new edu.poly.shopbangiay.raven.button.Button();
        btnThemSP = new edu.poly.shopbangiay.raven.button.Button();
        cbxSP = new combobox.Combobox();
        jPanel2 = new javax.swing.JPanel();
        tableScrollButton1 = new table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCTSP = new javax.swing.JTable();

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        lbAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbAnhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
        );

        txtTen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTen.setLabelText("Tên sản phẩm");

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSoLuong.setLabelText("Số lượng");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Thông tin sản phẩm");

        txtGiaNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtGiaNhap.setLabelText("Giá vốn");

        txtGiaBan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtGiaBan.setLabelText("Giá bán");

        cbxLoai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbxLoai.setLabeText("Loại");
        cbxLoai.setPreferredSize(new java.awt.Dimension(64, 46));

        cbxNSX.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbxNSX.setLabeText("Nhà sản xuất");
        cbxNSX.setPreferredSize(new java.awt.Dimension(64, 46));

        cbxSize.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbxSize.setLabeText("Size");
        cbxSize.setPreferredSize(new java.awt.Dimension(64, 46));

        cbxMS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbxMS.setLabeText("Màu sắc");
        cbxMS.setPreferredSize(new java.awt.Dimension(64, 46));

        cbxCL.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbxCL.setLabeText("Chất liệu");
        cbxCL.setPreferredSize(new java.awt.Dimension(64, 46));

        textAreaScroll1.setBackground(new java.awt.Color(255, 255, 255));
        textAreaScroll1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        textAreaScroll1.setLabelText("Mô tả");

        txtMota.setColumns(20);
        txtMota.setRows(5);
        txtMota.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        textAreaScroll1.setViewportView(txtMota);

        rdoOn.setText("Đang kinh doanh");
        rdoOn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Trạng thái:");

        rdoOff.setText("Ngừng kinh doanh");
        rdoOff.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        btnThem.setBackground(new java.awt.Color(153, 255, 153));
        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 255, 51));
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

        btnQL.setBackground(new java.awt.Color(181, 181, 181));
        btnQL.setText("Quản lý chi tiết");
        btnQL.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLActionPerformed(evt);
            }
        });

        txtTim.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTim.setLabelText("Tìm kiếm");
        txtTim.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimCaretUpdate(evt);
            }
        });

        btnNhapFile.setBackground(new java.awt.Color(153, 255, 153));
        btnNhapFile.setText("Nhập file");
        btnNhapFile.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNhapFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapFileActionPerformed(evt);
            }
        });

        btnXuatFile.setBackground(new java.awt.Color(153, 255, 153));
        btnXuatFile.setText("Xuất file");
        btnXuatFile.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileActionPerformed(evt);
            }
        });

        btnGenQR.setBackground(new java.awt.Color(0, 255, 255));
        btnGenQR.setText("Tạo mã QR");
        btnGenQR.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGenQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnGenQRActionPerformed(evt);
                } catch (WriterException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnThemSP.setText("+");
        btnThemSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        cbxSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbxSP.setLabeText("Mã sản phẩm");
        cbxSP.setPreferredSize(new java.awt.Dimension(64, 46));
        cbxSP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSPItemStateChanged(evt);
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
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(cbxSP, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(cbxSize, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(cbxMS, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(cbxLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(cbxNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addComponent(textAreaScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbxCL, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel1)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 28, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGenQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnQL, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNhapFile, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnThemSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(textAreaScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(rdoOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNhapFile, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQL, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenQR, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblCTSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblCTSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã", "Tên sản phẩm", "Loại", "Giá nhập", "Giá bán", "Tồn kho", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCTSP.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tblCTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCTSP);

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1332, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 835, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Thêm chi tiết sản phẩm cho " + cbxSP.getSelectedItem());
        if (confirm == 0) {
            ChiTietSanPham ctsp = new ChiTietSanPham();

            SanPham sanPham = sanPhamService.getList().get(cbxSP.getSelectedIndex() - 1);
            ctsp.setSanPham(sanPham);

            Loai loai = loaiService.getList().get(cbxLoai.getSelectedIndex() - 1);
            ctsp.setLoai(loai);

            Size size = sizeService.getList().get(cbxSize.getSelectedIndex() - 1);
            ctsp.setSize(size);

            ChatLieu chatLieu = chatLieuService.getList().get(cbxCL.getSelectedIndex() - 1);
            ctsp.setChatLieu(chatLieu);

            MauSac mauSac = mauSacService.getList().get(cbxMS.getSelectedIndex() - 1);
            ctsp.setMauSac(mauSac);

            NSX nsx = nsxService.getList().get(cbxNSX.getSelectedIndex() - 1);
            ctsp.setNsx(nsx);

            ctsp.setHinhAnh(url);
            ctsp.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
            ctsp.setGiaNhap(Float.parseFloat(txtGiaNhap.getText()));
            ctsp.setGiaBan(Float.parseFloat(txtGiaBan.getText()));
            ctsp.setMoTa(txtMota.getText());

            boolean tinhTrang = !rdoOff.isSelected();
            ctsp.setTinhTrang(tinhTrang);

            if (ctspService.them(ctsp)) {
                loadData(ctspService.timKiem(txtTim.getText()));
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblCTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTSPMouseClicked
        // TODO add your handling code here:

        int row = tblCTSP.getSelectedRow();
        ChiTietSanPham ctsp = ctspService.timKiem(txtTim.getText()).get(row);

        cbxSP.setSelectedItem(ctsp.getSanPham());
        txtTen.setText(ctsp.getSanPham().getTen());
        txtSoLuong.setText(ctsp.getSoLuong().toString());
        txtGiaNhap.setText(ctsp.getGiaNhap().toString());
        txtGiaBan.setText(ctsp.getGiaBan().toString());
        txtMota.setText(ctsp.getMoTa());
        cbxLoai.setSelectedItem(ctsp.getLoai());
        cbxNSX.setSelectedItem(ctsp.getNsx());
        cbxSize.setSelectedItem(ctsp.getSize());
        cbxMS.setSelectedItem(ctsp.getMauSac());
        cbxCL.setSelectedItem(ctsp.getChatLieu());
        if (ctsp.getTinhTrang() == true) {
            rdoOn.setSelected(true);
        } else {
            rdoOff.setSelected(true);
        }
        lbAnh.setIcon(ResizeImage(new ImageIcon("image/SP/" + ctsp.getHinhAnh()).toString()));

    }//GEN-LAST:event_tblCTSPMouseClicked

    private void btnNhapFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapFileActionPerformed
        // TODO add your handling code here:
//        List<SanPham> getListSP = sanPhamService.getList();
//        List<ChiTietSanPham> getListCTSP = ctspService.getList();
        List<ChiTietSanPham> list = new ArrayList<>();
        List<SanPham> listSP = new ArrayList<>();
        JFileChooser chonFile = new JFileChooser("/");
        chonFile.showOpenDialog(null);
        File file = chonFile.getSelectedFile();
        try {
            FileInputStream fis = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                if (currentRow.getRowNum() == 0) {
                    continue;
                }
                String maSP = currentRow.getCell(0).getStringCellValue();
                String tenSP = currentRow.getCell(1).getStringCellValue();
                Integer idLoai = (int) currentRow.getCell(2).getNumericCellValue();
                Integer idNSX = (int) currentRow.getCell(3).getNumericCellValue();
                Integer idSize = (int) currentRow.getCell(4).getNumericCellValue();
                Integer idMau = (int) currentRow.getCell(5).getNumericCellValue();
                Integer idCL = (int) currentRow.getCell(6).getNumericCellValue();
                Integer soLuong = (int) currentRow.getCell(7).getNumericCellValue();
                Double giaNhap = currentRow.getCell(8).getNumericCellValue();
                Double giaBan = currentRow.getCell(9).getNumericCellValue();
                String moTa = currentRow.getCell(10).getStringCellValue();
                String hinhAnh = currentRow.getCell(11).getStringCellValue();
                Boolean trangThai = currentRow.getCell(12).getBooleanCellValue();

                ChiTietSanPham ctsp = new ChiTietSanPham();
                SanPham sanPham = new SanPham();
                sanPham.setMa(maSP);
                sanPham.setTen(tenSP);

                ctsp.setSanPham(sanPham);

                Loai loai = new Loai();
                loai.setId(Integer.parseInt(String.valueOf(idLoai)));
                ctsp.setLoai(loai);

                Size size = new Size();
                size.setId(Integer.parseInt(String.valueOf(idSize)));
                ctsp.setSize(size);

                ChatLieu chatLieu = new ChatLieu();
                chatLieu.setId(Integer.parseInt(String.valueOf(idCL)));
                ctsp.setChatLieu(chatLieu);

                MauSac mauSac = new MauSac();
                mauSac.setId(Integer.parseInt(String.valueOf(idMau)));
                ctsp.setMauSac(mauSac);

                NSX nsx = new NSX();
                nsx.setId(Integer.parseInt(String.valueOf(idNSX)));
                ctsp.setNsx(nsx);

                ctsp.setSoLuong(Integer.parseInt(String.valueOf(soLuong)));
                ctsp.setGiaNhap(Float.parseFloat(String.valueOf(giaNhap)));
                ctsp.setGiaBan(Float.parseFloat(String.valueOf(giaBan)));
                ctsp.setMoTa(moTa);
                ctsp.setHinhAnh(hinhAnh);
                ctsp.setTinhTrang(Boolean.parseBoolean(String.valueOf(trangThai)));

                listSP.add(sanPham);
                list.add(ctsp);
            }

//            for (SanPham sp : listSP) {
//                for (SanPham s : getListSP) {
//                    if (sp.getMa().equals(s.getMa())) {
//                        sanPhamService.sua(sp);
//                    } else {
//                        sanPhamService.them(sp);
//                    }
//                }
//            }
//
//            for (ChiTietSanPham ctsp : list) {
//                for (ChiTietSanPham c : getListCTSP) {
//                    if (ctsp.getId().equals(c.getId())) {
//                        ctspService.sua(ctsp);
//                    } else {
//                        ctspService.them(ctsp);
//                    }
//                }
//                System.out.println(ctsp.toString());
//            }
            loadData(ctspService.timKiem(txtTim.getText()));
            JOptionPane.showMessageDialog(this, "Nhập file thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnNhapFileActionPerformed

    private void btnXuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileActionPerformed
        // TODO add your handling code here:
        List<ChiTietSanPham> list = ctspService.getList();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sản phẩm");
        int rowNum = 0;
        Row titleRow = sheet.createRow(rowNum);

        Cell maSPTT = titleRow.createCell(0);
        maSPTT.setCellValue("Mã SP");

        Cell tenSPTT = titleRow.createCell(1);
        tenSPTT.setCellValue("Tên SP");

        Cell loaiTT = titleRow.createCell(2);
        loaiTT.setCellValue("Loại");

        Cell NSXTT = titleRow.createCell(3);
        NSXTT.setCellValue("Nhà sản xuất");

        Cell sizeTT = titleRow.createCell(4);
        sizeTT.setCellValue("Size");

        Cell mauTT = titleRow.createCell(5);
        mauTT.setCellValue("Màu sắc");

        Cell chatLieuTT = titleRow.createCell(6);
        chatLieuTT.setCellValue("Chất liệu");

        Cell soLuongTT = titleRow.createCell(7);
        soLuongTT.setCellValue("Số lượng");

        Cell giaNhapTT = titleRow.createCell(8);
        giaNhapTT.setCellValue("Giá nhập");

        Cell giaBanTT = titleRow.createCell(9);
        giaBanTT.setCellValue("Giá bán");

        Cell moTaTT = titleRow.createCell(10);
        moTaTT.setCellValue("Mô tả");

        Cell hinhAnhTT = titleRow.createCell(11);
        hinhAnhTT.setCellValue("Hình ảnh");

        Cell trangThaiTT = titleRow.createCell(12);
        trangThaiTT.setCellValue("Trạng thái");

        for (ChiTietSanPham ctsp : list) {
            rowNum++;
            Row row = sheet.createRow(rowNum);
            Cell maSP = row.createCell(0);
            maSP.setCellValue(ctsp.getSanPham().getMa());

            Cell tenSP = row.createCell(1);
            tenSP.setCellValue(ctsp.getSanPham().getTen());

            Cell loai = row.createCell(2);
            loai.setCellValue(ctsp.getLoai().getTen());

            Cell NSX = row.createCell(3);
            NSX.setCellValue(ctsp.getNsx().getTen());

            Cell size = row.createCell(4);
            size.setCellValue(ctsp.getSize().getSoSize());

            Cell mau = row.createCell(5);
            mau.setCellValue(ctsp.getMauSac().getTen());

            Cell chatLieu = row.createCell(6);
            chatLieu.setCellValue(ctsp.getChatLieu().getTen());

            Cell soLuong = row.createCell(7);
            soLuong.setCellValue(ctsp.getSoLuong());

            Cell giaNhap = row.createCell(8);
            giaNhap.setCellValue(ctsp.getGiaNhap());

            Cell giaBan = row.createCell(9);
            giaBan.setCellValue(ctsp.getGiaBan());

            Cell moTa = row.createCell(10);
            moTa.setCellValue(ctsp.getMoTa());

            Cell hinhAnh = row.createCell(11);
            hinhAnh.setCellValue(ctsp.getHinhAnh());

            Cell trangThai = row.createCell(12);
            trangThai.setCellValue(ctsp.getTinhTrang());
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

    }//GEN-LAST:event_btnXuatFileActionPerformed

    private void btnQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLActionPerformed
        // TODO add your handling code here:
        QLCT dialog = new QLCT(new javax.swing.JFrame(), true);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
        dialog.setVisible(true);
        loadCBX_Loai(ctspService.listLoai());
        loadCBX_Size(ctspService.listSize());
        loadCBX_CL(ctspService.listCL());
        loadCBX_MS(ctspService.listMS());
        loadCBX_NSX(ctspService.listNSX());
    }//GEN-LAST:event_btnQLActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Cập nhật chi tiết cho sản phẩm: " + cbxSP.getSelectedItem());
        if (confirm == 0) {
            int row = tblCTSP.getSelectedRow();
            ChiTietSanPham ctsp = ctspService.timKiem(txtTim.getText()).get(row);

            SanPham sanPham = sanPhamService.getList().get(cbxSP.getSelectedIndex() - 1);
            ctsp.setSanPham(sanPham);

            Loai loai = loaiService.getList().get(cbxLoai.getSelectedIndex() - 1);
            ctsp.setLoai(loai);

            Size size = sizeService.getList().get(cbxSize.getSelectedIndex() - 1);
            ctsp.setSize(size);

            ChatLieu chatLieu = chatLieuService.getList().get(cbxCL.getSelectedIndex() - 1);
            ctsp.setChatLieu(chatLieu);

            MauSac mauSac = mauSacService.getList().get(cbxMS.getSelectedIndex() - 1);
            ctsp.setMauSac(mauSac);

            NSX nsx = nsxService.getList().get(cbxNSX.getSelectedIndex() - 1);
            ctsp.setNsx(nsx);

            ctsp.setHinhAnh(url);
            ctsp.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
            ctsp.setGiaNhap(Float.parseFloat(txtGiaNhap.getText()));
            ctsp.setGiaBan(Float.parseFloat(txtGiaBan.getText()));
            ctsp.setMoTa(txtMota.getText());

            boolean tinhTrang = !rdoOff.isSelected();
            ctsp.setTinhTrang(tinhTrang);

            if (ctspService.sua(ctsp)) {
                loadData(ctspService.timKiem(txtTim.getText()));
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Hệ thống vẫn sẽ lưu lịch sử giao dịch cho sản phẩm: " + cbxSP.getSelectedItem() + " nếu có");
        if (confirm == 0) {
            int row = tblCTSP.getSelectedRow();
            ChiTietSanPham ctsp = ctspService.timKiem(txtTim.getText()).get(row);
            ctsp.setSoLuong(0);
            ctsp.setTinhTrang(false);
            if (ctspService.sua(ctsp)) {
                loadData(ctspService.timKiem(txtTim.getText()));
                JOptionPane.showMessageDialog(this, "Thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Thất bại");
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtTimCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimCaretUpdate
        // TODO add your handling code here:
        String tim = txtTim.getText();
        List<ChiTietSanPham> list = ctspService.timKiem(tim);
        loadData(list);
    }//GEN-LAST:event_txtTimCaretUpdate

    private void lbAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAnhMouseClicked
        // TODO add your handling code here:
        try {
            JFileChooser chonFile = new JFileChooser("image/");
            chonFile.showOpenDialog(null);
            File anh = chonFile.getSelectedFile();
            url = anh.getAbsolutePath();

            lbAnh.setIcon(ResizeImage(url));

            File file = new File(url);
            file.renameTo(new File("image/SP/" + file.getName()));
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

    private void btnGenQRActionPerformed(java.awt.event.ActionEvent evt) throws WriterException, IOException {//GEN-FIRST:event_btnGenQRActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Tạo mã QR cho sản phẩm: " + cbxSP.getSelectedItem());
        if (confirm == 0) {
            int row = tblCTSP.getSelectedRow();
            ChiTietSanPham ctsp = ctspService.getList().get(row);
            String data = ctsp.getSanPham().getMa();
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix matrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200);

            // Write to file image
            String outputFile = "image/QR/" + data + ".png";
            Path path = FileSystems.getDefault().getPath(outputFile);
            MatrixToImageWriter.writeToPath(matrix, "PNG", path);
            JOptionPane.showMessageDialog(this, "Lưu QR thành công tại \n" + path);
        }
    }//GEN-LAST:event_btnGenQRActionPerformed

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        // TODO add your handling code here:
        SanPhamUI dialog = new SanPhamUI(new javax.swing.JFrame(), true);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
        dialog.setVisible(true);
        loadCBX_SP(ctspService.listSP());

    }//GEN-LAST:event_btnThemSPActionPerformed

    private void cbxSPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSPItemStateChanged
        // TODO add your handling code here:
        if (cbxSP.getSelectedIndex() == 0){
            txtTen.setText("");
        }else {
            SanPham sanPham = sanPhamService.getList().get(cbxSP.getSelectedIndex() - 1);
            txtTen.setText(sanPham.getTen());
        }
    }//GEN-LAST:event_cbxSPItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private edu.poly.shopbangiay.raven.button.Button btnGenQR;
    private edu.poly.shopbangiay.raven.button.Button btnNhapFile;
    private edu.poly.shopbangiay.raven.button.Button btnQL;
    private edu.poly.shopbangiay.raven.button.Button btnSua;
    private edu.poly.shopbangiay.raven.button.Button btnThem;
    private edu.poly.shopbangiay.raven.button.Button btnThemSP;
    private edu.poly.shopbangiay.raven.button.Button btnXoa;
    private edu.poly.shopbangiay.raven.button.Button btnXuatFile;
    private combobox.Combobox cbxCL;
    private combobox.Combobox cbxLoai;
    private combobox.Combobox cbxMS;
    private combobox.Combobox cbxNSX;
    private combobox.Combobox cbxSP;
    private combobox.Combobox cbxSize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbAnh;
    private radio_button.RadioButtonCustom rdoOff;
    private radio_button.RadioButtonCustom rdoOn;
    private table.TableScrollButton tableScrollButton1;
    private javax.swing.JTable tblCTSP;
    private textarea.TextAreaScroll textAreaScroll1;
    private textfield.TextField txtGiaBan;
    private textfield.TextField txtGiaNhap;
    private textarea.TextArea txtMota;
    private textfield.TextField txtSoLuong;
    private textfield.TextField txtTen;
    private textfield.TextField txtTim;
    // End of variables declaration//GEN-END:variables
}
