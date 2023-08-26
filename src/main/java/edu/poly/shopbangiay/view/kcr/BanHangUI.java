/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package edu.poly.shopbangiay.view.kcr;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import edu.poly.shopbangiay.model.*;
import edu.poly.shopbangiay.service.*;
import edu.poly.shopbangiay.service.impl.*;
import edu.poly.shopbangiay.view.kcr.addKH;

import java.awt.*;
import java.awt.image.BufferedImage;

import table.TableCustom;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.netbeans.lib.awtextra.AbsoluteConstraints;

/**
 * @author Quang
 */
public class BanHangUI extends javax.swing.JPanel implements Runnable, ThreadFactory {

    /**
     * Creates new form BanHangUI
     */
    //cam
    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private Webcam webcam = null;
    private WebcamPanel panel = null;
    private JTextArea textarea = null;
    //

    private DefaultTableModel defaultTableModel;
    private DefaultComboBoxModel defaultComboBoxModel;
    private CTSPService ctspService = new CTSPServiceImpl();
    private HoaDonService hoaDonService = new HoaDonServiceImpl();
    private CTHDService cthdService = new CTHDServiceImpl();
    private KhachHangService khachHangService = new KhachHangServiceImpl();
    private NguoiDungService nguoiDungService = new NguoiDungServiceImpl();
    private VCService vcService = new VCServiceImpl();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    public BanHangUI() {
        initComponents();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
        TableCustom.apply(jScrollPane2, TableCustom.TableType.DEFAULT);
        TableCustom.apply(jScrollPane3, TableCustom.TableType.DEFAULT);
        loadSP(ctspService.timKiem(txtTimSP.getText()));
        loadHD(hoaDonService.getList());
        loadCBXKH(khachHangService.getList());
        loadCBXVC(vcService.getList(Date.valueOf(LocalDate.now())));
        loadCBX_TT();
//        initWebcam(Cam);
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

    public void loadSP(List<ChiTietSanPham> list) {
        defaultTableModel = (DefaultTableModel) tblSP.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;
        for (ChiTietSanPham ctsp : list) {
            defaultTableModel.addRow(new Object[]{
                    stt++,
                    ctsp.getSanPham().getMa(),
                    ctsp.getSanPham().getTen(),
                    ctsp.getLoai().getTen(),
                    ctsp.getNsx().getTen(),
                    ctsp.getSize().getSoSize(),
                    ctsp.getSoLuong(),
                    ctsp.getGiaBan()
            });
        }
    }

    public void loadGH(List<ChiTietHoaDon> list) {
        defaultTableModel = (DefaultTableModel) tblGH.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;
        for (ChiTietHoaDon cthd : list) {
            defaultTableModel.addRow(new Object[]{
                    stt++,
                    cthd.getChiTietSanPham().getSanPham().getMa(),
                    cthd.getChiTietSanPham().getSanPham().getTen(),
                    cthd.getSoLuong(),
                    cthd.getDonGia(),
                    cthd.tongTien()
            });
        }
    }

    public void loadHD(List<HoaDon> list) {
        defaultTableModel = (DefaultTableModel) tblHD.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;
        for (HoaDon hd : list) {
            defaultTableModel.addRow(new Object[]{
                    stt++,
                    hd.getMa(),
                    hd.getNguoiDung().getTen(),
                    hd.getNgayTao(),
                    hd.getTinhTrang() == 0 ? "Chưa thanh toán" : "Đã thanh toán"
            });
        }
    }

    public void loadCBXKH(List<KhachHang> list) {
        defaultComboBoxModel = (DefaultComboBoxModel) cbxKH.getModel();
        defaultComboBoxModel.removeAllElements();

        for (KhachHang kh : list) {
            defaultComboBoxModel.addElement(kh);
        }
    }

    public void loadCBXVC(List<Voucher> list) {
        defaultComboBoxModel = (DefaultComboBoxModel) cbxVC.getModel();
        defaultComboBoxModel.removeAllElements();

        for (Voucher vc : list) {
            defaultComboBoxModel.addElement(vc);
        }
    }

    public String genMaHD() {
        for (int i = 0; i < hoaDonService.getList().size() + 1; i++) {
            String ma = "HD" + i;
            if (hoaDonService.getHDByMa(ma) == null) {
                return ma;
            }
        }
        return null;
    }

    public Boolean checkTT() {
        int row = tblHD.getSelectedRow();
//        int index = cbxTT.getSelectedIndex();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng click lại hóa đơn");
            return false;
        }
        HoaDon hoaDon = locHD().get(row);
        if (hoaDon.getTinhTrang() == 1) {
            JOptionPane.showMessageDialog(this, "Hóa đơn đã thanh toám");
            return false;
        }
        return true;
    }

    public Double tongTien() {
        double sum = 0.0;
        for (int i = 0; i < tblGH.getRowCount(); i++) {
            sum += Float.parseFloat(tblGH.getValueAt(i, 5).toString());
        }
        return sum;
    }

    public Boolean checkForm() {
        if (txtMaHD.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn");
            return false;
        }
        if (txtKhachTT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập tiền khách thanh toán");
            return false;
        }

        if (Float.parseFloat(txtTongTien.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Hóa đơn trống");
            return false;
        }

        try {
            Integer.parseInt(txtKhachTT.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Nhập lại");
            txtKhachTT.setText("");
            return false;
        }

        if (Float.parseFloat(txtTienThua.getText()) < 0) {
            float tienThieu = Float.parseFloat(txtTongTien.getText()) - Integer.parseInt(txtKhachTT.getText());
            JOptionPane.showMessageDialog(this, "Chưa dủ tiền thanh toán, còn thiếu: " + tienThieu + "VND");
            return false;
        } else {
            JOptionPane.showMessageDialog(this, "Tiền thừa: " + Float.parseFloat(txtTienThua.getText()));
        }

        return true;
    }

    public void clearFrom() {
        cbxKH.setSelectedIndex(0);
        cbxVC.setSelectedIndex(0);
        txtMaHD.setText("");
        txtTongTien.setText("0.0");
        txtGiamGia.setText("0");
        txtThanhTien.setText("0.0");
        txtKhachTT.setText("");
        txtTienThua.setText("0.0");

    }

    public List<HoaDon> locHD() {
        List<HoaDon> list;
        int index = cbxTT.getSelectedIndex();
        if (index == 0) {
            list = hoaDonService.getList();
        } else if (index == 1) {
            list = hoaDonService.locTT(0);
        } else {
            list = hoaDonService.locTT(1);
        }
        return list;
    }

    private void addToGioHang() {

        if (txtMaHD.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chọn hóa đơn trước");
        } else {
            HoaDon hoaDon = hoaDonService.getHDByMa(txtMaHD.getText());
            if (checkTT()) {
                int rowSP = tblSP.getSelectedRow();
                ChiTietSanPham ctsp = ctspService.getList().get(rowSP);
                if (ctsp.getSoLuong() == 0) {
                    JOptionPane.showMessageDialog(this, "Hết hàng");
                    return;
                } else {
                    String input = JOptionPane.showInputDialog("Nhập số lượng sản phẩm: ");
                    try {
                        Integer.parseInt(input);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Nhập lại số lượng");
                        return;
                    }
                    if (Integer.parseInt(input) > ctsp.getSoLuong()) {
                        JOptionPane.showMessageDialog(this, "Không đủ, số lượng còn lại: " + ctsp.getSoLuong());
                        return;
                    }
                    if (Integer.parseInt(input) <= 0) {
                        JOptionPane.showMessageDialog(this, "Số lượng nhập vào phải lớn hơn 0");
                        return;
                    } else {
                        if (cthdService.getCTHD(hoaDon.getId(), ctsp.getId()) == null) {
                            ChiTietHoaDon cthd = new ChiTietHoaDon();
                            cthd.setHoaDon(hoaDonService.getHDByMa(txtMaHD.getText()));
                            cthd.setChiTietSanPham(ctspService.getList().get(rowSP));

                            cthd.setSoLuong(Integer.parseInt(input));
                            cthd.setDonGia(Float.parseFloat(tblSP.getValueAt(rowSP, 7).toString()));

                            cthdService.them(cthd);
                        } else {
                            ChiTietHoaDon cthd = cthdService.getCTHD(hoaDon.getId(), ctsp.getId());
                            cthd.setSoLuong(cthd.getSoLuong() + Integer.parseInt(input));
                            cthdService.sua(cthd);
                        }

                        loadGH(cthdService.getCTHDByMaHD(txtMaHD.getText()));
                        txtTongTien.setText(tongTien().toString());
                        ctsp.setSoLuong(ctsp.getSoLuong() - Integer.parseInt(input));
                        ctspService.sua(ctsp);
                    }
                }
            }

        }
        loadSP(ctspService.timKiem(txtTimSP.getText()));
        txtTongTien.setText(tongTien().toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnThemHD = new edu.poly.shopbangiay.raven.button.Button();
        cbxTT = new combo_suggestion.ComboBoxSuggestion();
        tableScrollButton1 = new table.TableScrollButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHD = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnHuyHD = new edu.poly.shopbangiay.raven.button.Button();
        btnThanhToan = new edu.poly.shopbangiay.raven.button.Button();
        cbxKH = new combo_suggestion.ComboBoxSuggestion();
        txtMaHD = new textfield.TextField();
        txtTongTien = new textfield.TextField();
        txtGiamGia = new textfield.TextField();
        txtThanhTien = new textfield.TextField();
        txtKhachTT = new textfield.TextField();
        txtTienThua = new textfield.TextField();
        jLabel2 = new javax.swing.JLabel();
        btnThemKH = new edu.poly.shopbangiay.raven.button.Button();
        cbxVC = new combo_suggestion.ComboBoxSuggestion();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        tableScrollButton2 = new table.TableScrollButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGH = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        txtTimSP = new textfield.TextField();
        tableScrollButton3 = new table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        Cam = new javax.swing.JPanel();
        btnOnCam = new edu.poly.shopbangiay.raven.button.Button();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 102), 2, true));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        btnThemHD.setBackground(new java.awt.Color(204, 255, 255));
        btnThemHD.setText("Tạo hóa đơn mới");
        btnThemHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHDActionPerformed(evt);
            }
        });

        cbxTT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTTItemStateChanged(evt);
            }
        });

        jScrollPane3.setFont(new java.awt.Font(".VnArial", 0, 12)); // NOI18N

        tblHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã", "Người tạo", "Ngày tạo", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
        jScrollPane3.setViewportView(tblHD);

        tableScrollButton1.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbxTT, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(tableScrollButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(cbxTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thanh toán", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        btnHuyHD.setBackground(new java.awt.Color(255, 51, 153));
        btnHuyHD.setText("Hủy hóa đơn");
        btnHuyHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        btnThanhToan.setBackground(new java.awt.Color(153, 255, 153));
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        cbxKH.setToolTipText("");
        cbxKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        txtMaHD.setEditable(false);
        txtMaHD.setBackground(new java.awt.Color(255, 255, 255));
        txtMaHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMaHD.setLabelText("Mã hóa đơn");

        txtTongTien.setEditable(false);
        txtTongTien.setBackground(new java.awt.Color(255, 255, 255));
        txtTongTien.setText("0.0");
        txtTongTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTongTien.setLabelText("Tổng tiền");
        txtTongTien.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTongTienCaretUpdate(evt);
            }
        });

        txtGiamGia.setEditable(false);
        txtGiamGia.setBackground(new java.awt.Color(255, 255, 255));
        txtGiamGia.setText("0.0");
        txtGiamGia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtGiamGia.setLabelText("Giảm giá (%):");
        txtGiamGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGiamGiaMouseClicked(evt);
            }
        });

        txtThanhTien.setEditable(false);
        txtThanhTien.setBackground(new java.awt.Color(255, 255, 255));
        txtThanhTien.setText("0.0");
        txtThanhTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtThanhTien.setLabelText("Thành tiền");

        txtKhachTT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtKhachTT.setLabelText("Khách thanh toán");
        txtKhachTT.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtKhachTTCaretUpdate(evt);
            }
        });

        txtTienThua.setEditable(false);
        txtTienThua.setBackground(new java.awt.Color(255, 255, 255));
        txtTienThua.setText("0.0");
        txtTienThua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTienThua.setLabelText("Tiền thừa cho khách");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Khách hàng:");

        btnThemKH.setText("+");
        btnThemKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHActionPerformed(evt);
            }
        });

        cbxVC.setToolTipText("");
        cbxVC.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbxVC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxVCItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Khuyến mại");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cbxKH, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxVC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnHuyHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtKhachTT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxKH, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxVC, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtKhachTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHuyHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jScrollPane2.setFont(new java.awt.Font(".VnArial", 0, 12)); // NOI18N

        tblGH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblGH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã", "Tên", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGHMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblGH);

        tableScrollButton2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tableScrollButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        txtTimSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTimSP.setLabelText("Tìm kiếm");
        txtTimSP.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimSPCaretUpdate(evt);
            }
        });

        jScrollPane1.setFont(new java.awt.Font(".VnArial", 0, 12)); // NOI18N

        tblSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã", "Tên", "Loại", "NSX", "Size", "Số lượng tồn", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSP);

        tableScrollButton3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(594, Short.MAX_VALUE))
            .addComponent(tableScrollButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableScrollButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        Cam.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Webcam", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        Cam.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnOnCam.setText("On Cam");
        btnOnCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOnCamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Cam, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnOnCam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Cam, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOnCam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked
        addToGioHang();
    }//GEN-LAST:event_tblSPMouseClicked

    private void tblGHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGHMouseClicked
        // TODO add your handling code here:

        String inputString = JOptionPane.showInputDialog("Nhập lại số lượng sản phẩm");

        try {
            Integer.parseInt(inputString);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Nhập lại");
            return;
        }

        Integer intput = Integer.parseInt(inputString);

        if (intput < 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
            return;
        }

        int rowGH = tblGH.getSelectedRow();
        ChiTietHoaDon cthd = cthdService.getCTHDByMaHD(txtMaHD.getText()).get(rowGH);
        if (intput == cthd.getSoLuong()) {
            return;
        }

        ChiTietSanPham ctsp = cthd.getChiTietSanPham();

        if (intput == 0) {
            ctsp.setSoLuong(ctsp.getSoLuong() + cthd.getSoLuong());
            ctspService.sua(ctsp);
            cthdService.xoa(cthd);
        }
        int soLuongSP = cthd.getSoLuong();
        if (intput > 0) {
            if (ctsp.getSoLuong() == 0) {
                JOptionPane.showMessageDialog(this, "Sản phẩm đã hết hàng");
                return;
            }
            if (intput < cthd.getSoLuong()) {
                ctsp.setSoLuong(ctsp.getSoLuong() + (soLuongSP - intput));
            } else {
                ctsp.setSoLuong(ctsp.getSoLuong() - (intput - soLuongSP));
            }
            cthd.setSoLuong(intput);
            cthdService.sua(cthd);
            loadGH(cthdService.getCTHDByMaHD(txtMaHD.getText()));
            ctspService.sua(ctsp);

        }
        loadGH(cthdService.getCTHDByMaHD(txtMaHD.getText()));
        loadSP(ctspService.timKiem(txtTimSP.getText()));
        txtTongTien.setText(tongTien().toString());
    }//GEN-LAST:event_tblGHMouseClicked

    private void tblHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDMouseClicked
        // TODO add your handling code here:
        int row = tblHD.getSelectedRow();
        HoaDon hoaDon = locHD().get(row);
        txtMaHD.setText(hoaDon.getMa());
        if (hoaDon.getTinhTrang() == 0){
            cbxKH.setSelectedIndex(0);
            cbxVC.setSelectedIndex(0);
        }else {
            cbxKH.setSelectedItem(hoaDon.getKhachHang().getTen());
            cbxVC.setSelectedItem(hoaDon.getVoucher().getTen());
        }

        loadGH(cthdService.getCTHDByMaHD(txtMaHD.getText()));
        txtTongTien.setText(tongTien().toString());
    }//GEN-LAST:event_tblHDMouseClicked

    private void btnThemHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHDActionPerformed
        // TODO add your handling code here:
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMa(genMaHD());
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setId(2);
        hoaDon.setNguoiDung(nguoiDung);
        hoaDon.setNgayTao(Date.valueOf(simpleDateFormat.format(new java.util.Date())));
        hoaDon.setTinhTrang(0);
        KhachHang khachHang = khachHangService.getList().get(0);
        hoaDon.setKhachHang(khachHang);
        Voucher voucher = vcService.getList(Date.valueOf(LocalDate.now())).get(0);
        hoaDon.setVoucher(voucher);

        hoaDonService.them(hoaDon);
        loadHD(locHD());


    }//GEN-LAST:event_btnThemHDActionPerformed

    private void txtKhachTTCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtKhachTTCaretUpdate
        // TODO add your handling code here:
        if (!txtKhachTT.getText().equals("")) {
            txtTienThua.setText(String.valueOf((Integer.parseInt(txtKhachTT.getText())) - Float.parseFloat(txtThanhTien.getText())));
        }
    }//GEN-LAST:event_txtKhachTTCaretUpdate

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        if (checkTT()) {
            if (checkForm()) {
                HoaDon hoaDon = hoaDonService.getHDByMa(txtMaHD.getText());
                hoaDon.setTongTien(Float.parseFloat(txtTongTien.getText()));
                hoaDon.setThanhTien(Float.parseFloat(txtThanhTien.getText()));
                NguoiDung nguoiDung = nguoiDungService.getList().get(0);
                hoaDon.setNguoiDung(nguoiDung);
                KhachHang khachHang = khachHangService.getList().get(cbxKH.getSelectedIndex() + 1);
                hoaDon.setKhachHang(khachHang);
                hoaDon.setTinhTrang(1);
                hoaDon.setNgayTT(Date.valueOf(LocalDate.now()));
                Voucher voucher = vcService.getList(Date.valueOf(LocalDate.now())).get(cbxVC.getSelectedIndex());
                hoaDon.setVoucher(voucher);

                hoaDonService.sua(hoaDon);

                JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                clearFrom();
                loadHD(locHD());
                loadGH(cthdService.getCTHDByMaHD(txtMaHD.getText()));
                loadSP(ctspService.getList());
            }
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtTongTienCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTongTienCaretUpdate
        // TODO add your handling code here:
        txtThanhTien.setText(txtTongTien.getText());

    }//GEN-LAST:event_txtTongTienCaretUpdate

    private void txtTimSPCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimSPCaretUpdate
        // TODO add your handling code here:
        String timSP = txtTimSP.getText();
        List<ChiTietSanPham> list = ctspService.timKiem(timSP);
        loadSP(list);

    }//GEN-LAST:event_txtTimSPCaretUpdate

    private void txtGiamGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGiamGiaMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_txtGiamGiaMouseClicked

    private void cbxVCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxVCItemStateChanged
        // TODO add your handling code here:
        Voucher voucher = vcService.getList(Date.valueOf(LocalDate.now())).get(cbxVC.getSelectedIndex());
        txtGiamGia.setText(voucher.getPhanTramGiam().toString());

        txtThanhTien.setText(String.valueOf(Float.parseFloat(txtTongTien.getText()) - (Float.parseFloat(txtTongTien.getText()) / 100 * Integer.parseInt(txtGiamGia.getText()))));
    }//GEN-LAST:event_cbxVCItemStateChanged

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

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
        // TODO add your handling code here:
        addKH dialog = new addKH(new javax.swing.JFrame(), true);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
        dialog.setVisible(true);
        loadCBXKH(khachHangService.getList());
    }//GEN-LAST:event_btnThemKHActionPerformed

    private void btnOnCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOnCamActionPerformed
        // TODO add your handling code here:
        initWebcam(Cam);
    }//GEN-LAST:event_btnOnCamActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel Cam;
    private edu.poly.shopbangiay.raven.button.Button btnHuyHD;
    private edu.poly.shopbangiay.raven.button.Button btnOnCam;
    private edu.poly.shopbangiay.raven.button.Button btnThanhToan;
    private edu.poly.shopbangiay.raven.button.Button btnThemHD;
    private edu.poly.shopbangiay.raven.button.Button btnThemKH;
    private combo_suggestion.ComboBoxSuggestion cbxKH;
    private combo_suggestion.ComboBoxSuggestion cbxTT;
    private combo_suggestion.ComboBoxSuggestion cbxVC;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private table.TableScrollButton tableScrollButton1;
    private table.TableScrollButton tableScrollButton2;
    private table.TableScrollButton tableScrollButton3;
    private javax.swing.JTable tblGH;
    private javax.swing.JTable tblHD;
    private javax.swing.JTable tblSP;
    private textfield.TextField txtGiamGia;
    private textfield.TextField txtKhachTT;
    private textfield.TextField txtMaHD;
    private textfield.TextField txtThanhTien;
    private textfield.TextField txtTienThua;
    private textfield.TextField txtTimSP;
    private textfield.TextField txtTongTien;
    // End of variables declaration//GEN-END:variables

    public void initWebcam(JPanel panelShow) {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); //0 is default webcam
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        panel.setMirrored(true);
        panelShow.add(panel, new AbsoluteConstraints(0, 0, panelShow.getWidth(), panelShow.getHeight()));

        setVisible(true);
        executor.execute(this);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {

                if ((image = webcam.getImage()) == null) {
                    continue;
                }

                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                try {
                    result = new MultiFormatReader().decode(bitmap);
                } catch (NotFoundException e) {
                    // fall thru, it means there is no QR code in image
                }
            }
            if (result != null) {
                String maSP = result.getText();
                for (int i = 0; i < tblSP.getRowCount(); i++) {
                    if (tblSP.getValueAt(i, 1).equals(maSP)) {
                        if (txtMaHD.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Chọn hóa đơn trước");
                        } else {
                            HoaDon hoaDon = hoaDonService.getHDByMa(txtMaHD.getText());
                            if (checkTT()) {
                                int rowSP = tblSP.getSelectedRow();
                                ChiTietSanPham ctsp = ctspService.getCTSPByMaSP(result.getText());
                                if (ctsp.getSoLuong() == 0) {
                                    JOptionPane.showMessageDialog(this, "Hết hàng");
                                    return;
                                } else {
                                    String input = JOptionPane.showInputDialog("Nhập số lượng sản phẩm: ");
                                    try {
                                        Integer.parseInt(input);
                                    } catch (Exception e) {
                                        JOptionPane.showMessageDialog(this, "Nhập lại số lượng");
                                        return;
                                    }
                                    if (Integer.parseInt(input) > ctsp.getSoLuong()) {
                                        JOptionPane.showMessageDialog(this, "Không đủ, số lượng còn lại: " + ctsp.getSoLuong());
                                        return;
                                    }
                                    if (Integer.parseInt(input) <= 0) {
                                        JOptionPane.showMessageDialog(this, "Số lượng nhập vào phải lớn hơn 0");
                                        return;
                                    } else {
                                        if (cthdService.getCTHD(hoaDon.getId(), ctsp.getId()) == null) {
                                            ChiTietHoaDon cthd = new ChiTietHoaDon();
                                            cthd.setHoaDon(hoaDonService.getHDByMa(txtMaHD.getText()));
                                            cthd.setChiTietSanPham(ctspService.getCTSPByMaSP(result.getText()));

                                            cthd.setSoLuong(Integer.parseInt(input));
                                            cthd.setDonGia(Float.parseFloat(ctsp.getGiaBan().toString()));

                                            cthdService.them(cthd);
                                        } else {
                                            ChiTietHoaDon cthd = cthdService.getCTHD(hoaDon.getId(), ctsp.getId());
                                            cthd.setSoLuong(cthd.getSoLuong() + Integer.parseInt(input));
                                            cthdService.sua(cthd);
                                        }

                                        loadGH(cthdService.getCTHDByMaHD(txtMaHD.getText()));
                                        txtTongTien.setText(tongTien().toString());
                                        ctsp.setSoLuong(ctsp.getSoLuong() - Integer.parseInt(input));
                                        ctspService.sua(ctsp);
                                    }
                                }
                            }

                        }
                        loadSP(ctspService.timKiem(txtTimSP.getText()));
                        txtTongTien.setText(tongTien().toString());
                    }
                }
            }
        }
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "example-runner");
        t.setDaemon(true);
        return t;
    }
}
