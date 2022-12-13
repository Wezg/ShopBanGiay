/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edu.poly.shopbangiay.view;

import com.formdev.flatlaf.FlatIntelliJLaf;
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
import edu.poly.shopbangiay.helper.Auth;
import edu.poly.shopbangiay.model.ChiTietHoaDon;
import edu.poly.shopbangiay.model.ChiTietSanPham;
import edu.poly.shopbangiay.model.HoaDon;
import edu.poly.shopbangiay.model.KhachHang;
import edu.poly.shopbangiay.model.NguoiDung;
import edu.poly.shopbangiay.model.Voucher;
import edu.poly.shopbangiay.service.CTHDService;
import edu.poly.shopbangiay.service.CTSPService;
import edu.poly.shopbangiay.service.HoaDonService;
import edu.poly.shopbangiay.service.KhachHangService;
import edu.poly.shopbangiay.service.NguoiDungService;
import edu.poly.shopbangiay.service.VCService;
import edu.poly.shopbangiay.service.impl.CTHDServiceImpl;
import edu.poly.shopbangiay.service.impl.CTSPServiceImpl;
import edu.poly.shopbangiay.service.impl.HoaDonServiceImpl;
import edu.poly.shopbangiay.service.impl.KhachHangServiceImpl;
import edu.poly.shopbangiay.service.impl.NguoiDungServiceImpl;
import edu.poly.shopbangiay.service.impl.VCServiceImpl;
import edu.poly.shopbangiay.view.kcr.addKH;
import java.awt.BorderLayout;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import table.TableCustom;

public class Main extends javax.swing.JFrame implements Runnable, ThreadFactory {

    /**
     * Creates new form Main
     */
    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private Webcam webcam = null;
    private WebcamPanel panel = null;
    private JTextArea textarea = null;

    private DefaultTableModel defaultTableModel;
    private DefaultComboBoxModel defaultComboBoxModel;
    private CTSPService ctspService = new CTSPServiceImpl();
    private HoaDonService hoaDonService = new HoaDonServiceImpl();
    private CTHDService cthdService = new CTHDServiceImpl();
    private KhachHangService khachHangService = new KhachHangServiceImpl();
    private NguoiDungService nguoiDungService = new NguoiDungServiceImpl();
    private VCService vcService = new VCServiceImpl();

    DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    DecimalFormat formatter = new DecimalFormat("###,###,###");
    CardLayout cardLayout;

    public Main() {
        initComponents();
        setTitle("Phần mềm quản lý bán giày");
        cardLayout = (CardLayout) jPanel3.getLayout();
        cardLayout.show(jPanel3, "TrangChu");
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
        TableCustom.apply(jScrollPane2, TableCustom.TableType.DEFAULT);
        TableCustom.apply(jScrollPane3, TableCustom.TableType.DEFAULT);
        loadSP(ctspService.timKiem(txtTimSP.getText()));
        loadHD(hoaDonService.getList());
        loadCBXKH(khachHangService.getList());
        loadCBXVC(vcService.getList(Date.valueOf(LocalDate.now())));
        loadCBX_TT();

        initWebcam(Cam);
        if (Auth.isLogin()){
            lbName.setText(Auth.user.getTen());
            lbCV.setText(Auth.user.getChucVu() ? "Quản lý" : "Nhân viên");
        }
        phanQuyen();
    }

    public void phanQuyen(){
        if (Auth.isManager()){

        }
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
                formatter.format(ctsp.getGiaBan()) + " VNĐ"
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
                formatter.format(cthd.getDonGia()) + " VNĐ",
                formatter.format(cthd.tongTien()) + " VNĐ"
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
                simpleDateFormat.format(hd.getNgayTao()),
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
            JOptionPane.showMessageDialog(this, "Hóa đơn đã thanh toán");
            return false;
        }
        return true;
    }

    public Double tongTien() {
        double sum = 0.0;
        for (int i = 0; i < tblGH.getRowCount(); i++) {
            ChiTietHoaDon cthd = cthdService.getCTHDByMaHD(txtMaHD.getText()).get(i);
            sum += (cthd.getSoLuong() * cthd.getDonGia());
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

        if (tongTien() <= 0) {
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

        if ((khachTT() - thanhTien()) < 0) {
            String tienThieu = formatter.format(thanhTien() - khachTT()) + " VNĐ";
            System.out.println(thanhTien());
            System.out.println(khachTT());
            JOptionPane.showMessageDialog(this, "Chưa dủ tiền thanh toán, còn thiếu: " + tienThieu);
            return false;
        } else {
            JOptionPane.showMessageDialog(this, "Tiền thừa: " + formatter.format(Math.abs(thanhTien() - khachTT())) + "VNĐ");
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
                            cthd.setDonGia(ctsp.getGiaBan());

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
        txtTongTien.setText(formatter.format(tongTien()) + " VNĐ");
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
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        lbName = new javax.swing.JLabel();
        lbCV = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        tableScrollButton2 = new table.TableScrollButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGH = new javax.swing.JTable();
        btnXoaAll = new edu.poly.shopbangiay.raven.button.Button();
        Cam = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        txtTimSP = new textfield.TextField();
        tableScrollButton3 = new table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        jPanel25 = new javax.swing.JPanel();
        btnThanhToan = new edu.poly.shopbangiay.raven.button.Button();
        cbxKH = new combo_suggestion.ComboBoxSuggestion();
        txtMaHD = new textfield.TextField();
        txtTongTien = new textfield.TextField();
        txtGiamGia = new textfield.TextField();
        txtThanhTien = new textfield.TextField();
        txtKhachTT = new textfield.TextField();
        txtTienThua = new textfield.TextField();
        jLabel12 = new javax.swing.JLabel();
        btnThemKH = new edu.poly.shopbangiay.raven.button.Button();
        cbxVC = new combo_suggestion.ComboBoxSuggestion();
        jLabel15 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        btnThemHD = new edu.poly.shopbangiay.raven.button.Button();
        cbxTT = new combo_suggestion.ComboBoxSuggestion();
        tableScrollButton1 = new table.TableScrollButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHD = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        sanPhamUI1 = new edu.poly.shopbangiay.view.CTSPUI();
        jPanel16 = new javax.swing.JPanel();
        nhanVienUI1 = new edu.poly.shopbangiay.view.NhanVienUI();
        jPanel17 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        khachHangUI1 = new edu.poly.shopbangiay.view.KhachHangUI();
        jPanel18 = new javax.swing.JPanel();
        thongKeUI1 = new edu.poly.shopbangiay.view.ThongKeUI();
        jPanel22 = new javax.swing.JPanel();
        voucherUI1 = new edu.poly.shopbangiay.view.VoucherUI();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 153), 2, true));

        jPanel4.setBackground(new java.awt.Color(224, 224, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 102, 255), 1, true));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Trang chủ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 102, 255), 1, true));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Bán hàng");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));
        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 102, 255), 1, true));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Sản phẩm");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(204, 204, 255));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 102, 255), 1, true));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Nhân viên");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(204, 204, 255));
        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 102, 255), 1, true));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Hóa đơn");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(204, 204, 255));
        jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 102, 255), 1, true));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Khách hàng");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel8)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(204, 204, 255));
        jPanel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 102, 255), 1, true));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 153, 153));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Đăng xuất");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel9)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(204, 204, 255));
        jPanel11.setPreferredSize(new java.awt.Dimension(228, 228));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/avt.png"))); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel12.setBackground(new java.awt.Color(204, 204, 255));
        jPanel12.setPreferredSize(new java.awt.Dimension(228, 228));

        lbName.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lbName.setForeground(new java.awt.Color(255, 0, 0));
        lbName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbName.setText("Chu Văn Quang");

        lbCV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbCV.setForeground(new java.awt.Color(204, 102, 255));
        lbCV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCV.setText("-Admin-");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addComponent(lbCV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbCV)
                .addContainerGap())
        );

        jPanel19.setBackground(new java.awt.Color(204, 204, 255));
        jPanel19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 102, 255), 1, true));
        jPanel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel19MouseClicked(evt);
            }
        });

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Thống kê");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel13)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel21.setBackground(new java.awt.Color(204, 204, 255));
        jPanel21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 102, 255), 1, true));
        jPanel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel21MouseClicked(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Voucher");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel14)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.CardLayout());

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 153), 2, true));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 153));
        jLabel2.setText("ABC.........");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Welcome.png"))); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(550, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(219, 219, 219))
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(0, 271, Short.MAX_VALUE)
                .addComponent(jLabel1))
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel3.add(jPanel13, "TrangChu");

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 153), 2, true));

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

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

        btnXoaAll.setBackground(new java.awt.Color(255, 236, 236));
        btnXoaAll.setText("Xóa tất cả");
        btnXoaAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(809, Short.MAX_VALUE)
                .addComponent(btnXoaAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tableScrollButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoaAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Cam.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Webcam", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        Cam.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

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
                "STT", "Mã", "Tên", "Loại", "NSX", "Size", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(594, Short.MAX_VALUE))
            .addComponent(tableScrollButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableScrollButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
        );

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thanh toán", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

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
                try {
                    txtKhachTTCaretUpdate(evt);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        txtTienThua.setEditable(false);
        txtTienThua.setBackground(new java.awt.Color(255, 255, 255));
        txtTienThua.setText("0.0");
        txtTienThua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTienThua.setLabelText("Tiền thừa cho khách");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Khách hàng:");

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

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Khuyến mại");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cbxKH, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxVC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel25Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtKhachTT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxKH, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

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

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbxTT, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(tableScrollButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(cbxTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1304, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Cam, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(4, 4, 4)))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 974, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Cam, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(2, 2, 2)))
        );

        jPanel3.add(jPanel14, "BanHang");

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 153), 2, true));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sanPhamUI1, javax.swing.GroupLayout.DEFAULT_SIZE, 1304, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sanPhamUI1, javax.swing.GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel15, "QLSP");

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 153), 2, true));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nhanVienUI1, javax.swing.GroupLayout.DEFAULT_SIZE, 1304, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nhanVienUI1, javax.swing.GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel16, "QLNV");

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 153), 2, true));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1304, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 974, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel17, "QLHD");

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 153), 2, true));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(khachHangUI1, javax.swing.GroupLayout.DEFAULT_SIZE, 1304, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(khachHangUI1, javax.swing.GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel20, "KhachHang");

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 153), 2, true));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(thongKeUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 1304, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(thongKeUI1, javax.swing.GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel18, "ThongKe");

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 153), 2, true));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(voucherUI1, javax.swing.GroupLayout.DEFAULT_SIZE, 1304, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(voucherUI1, javax.swing.GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel22, "Voucher");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
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

    
    public void showPanel(Component form){
        removeAll();
        add(form);
        repaint();
        revalidate();
    }
       
    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here:
        cardLayout.show(jPanel3, "TrangChu");
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        // TODO add your handling code here:
//        jPanel14.setLayout(new BorderLayout());
//        jPanel14.removeAll();
//        jPanel14.add(new Main());
//        jPanel14.repaint();
//        jPanel14.revalidate();
        cardLayout.show(jPanel3, "BanHang");
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        // TODO add your handling code here:
        jPanel15.setLayout(new BorderLayout());
        jPanel15.removeAll();
        jPanel15.add(new CTSPUI());
        jPanel15.repaint();
        jPanel15.revalidate();
        cardLayout.show(jPanel3, "QLSP");
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        // TODO add your handling code here:
        jPanel16.setLayout(new BorderLayout());
        jPanel16.removeAll();
        jPanel16.add(new NhanVienUI());
        jPanel16.repaint();
        jPanel16.revalidate();
        cardLayout.show(jPanel3, "QLNV");
    }//GEN-LAST:event_jPanel7MouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
//        jPanel17.setOpaque(false);
//        jPanel17.setBorder(new EmptyBorder(10, 20, 10, 10));
        jPanel17.setLayout(new BorderLayout());
        jPanel17.removeAll();
        jPanel17.add(new HoaDonUI());
        jPanel17.repaint();
        jPanel17.revalidate();
        cardLayout.show(jPanel3, "QLHD");

    }//GEN-LAST:event_jPanel8MouseClicked

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        // TODO add your handling code here:
        jPanel20.setLayout(new BorderLayout());
        jPanel20.removeAll();
        jPanel20.add(new KhachHangUI());
        jPanel20.repaint();
        jPanel20.revalidate();
        cardLayout.show(jPanel3, "KhachHang");
    }//GEN-LAST:event_jPanel9MouseClicked

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        // TODO add your handling code here:
        dispose();
        new Login().setVisible(true);

    }//GEN-LAST:event_jPanel10MouseClicked

    private void jPanel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MouseClicked
        // TODO add your handling code here:
        jPanel18.setLayout(new BorderLayout());
        jPanel18.removeAll();
        jPanel18.add(new ThongKeUI());
        jPanel18.repaint();
        jPanel18.revalidate();
        cardLayout.show(jPanel3, "ThongKe");
    }//GEN-LAST:event_jPanel19MouseClicked

    private void jPanel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel21MouseClicked
        // TODO add your handling code here:
        jPanel22.setLayout(new BorderLayout());
        jPanel22.removeAll();
        jPanel22.add(new VoucherUI());
        jPanel22.repaint();
        jPanel22.revalidate();
        cardLayout.show(jPanel3, "Voucher");
    }//GEN-LAST:event_jPanel21MouseClicked

    private void btnThemHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHDActionPerformed
        // TODO add your handling code here:
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMa(genMaHD());
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setId(2);
        hoaDon.setNguoiDung(nguoiDung);
        hoaDon.setNgayTao(new java.util.Date());
        hoaDon.setTinhTrang(0);
        KhachHang khachHang = khachHangService.getList().get(0);
        hoaDon.setKhachHang(khachHang);
        Voucher voucher = vcService.getList(Date.valueOf(LocalDate.now())).get(0);
        hoaDon.setVoucher(voucher);

        hoaDonService.them(hoaDon);
        loadHD(locHD());

    }//GEN-LAST:event_btnThemHDActionPerformed

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

    private void tblHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDMouseClicked
        // TODO add your handling code here:
        int row = tblHD.getSelectedRow();
        HoaDon hoaDon = locHD().get(row);
        txtMaHD.setText(hoaDon.getMa());
//        if (hoaDon.getTinhTrang() == 0) {
//            cbxKH.setSelectedIndex(0);
//            cbxVC.setSelectedIndex(0);
//        } else {
//            cbxKH.setSelectedItem(hoaDon.getKhachHang().getTen());
//            cbxVC.setSelectedItem(hoaDon.getVoucher().getTen());
//        }

        loadGH(cthdService.getCTHDByMaHD(txtMaHD.getText()));

        txtTongTien.setText(formatter.format(tongTien()) + " VNĐ");
    }//GEN-LAST:event_tblHDMouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        if (checkTT()) {
            if (checkForm()) {
                HoaDon hoaDon = hoaDonService.getHDByMa(txtMaHD.getText());
                hoaDon.setTongTien(Float.parseFloat(tongTien().toString()));
                hoaDon.setThanhTien(Float.parseFloat(thanhTien().toString()));
                NguoiDung nguoiDung = nguoiDungService.getList().get(0);
                hoaDon.setNguoiDung(nguoiDung);
                KhachHang khachHang = khachHangService.getList().get(cbxKH.getSelectedIndex() + 1);
                hoaDon.setKhachHang(khachHang);
                hoaDon.setTinhTrang(1);
                hoaDon.setNgayTT(new java.util.Date());
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

    private void txtGiamGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGiamGiaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiamGiaMouseClicked

    private void txtKhachTTCaretUpdate(javax.swing.event.CaretEvent evt) throws ParseException {//GEN-FIRST:event_txtKhachTTCaretUpdate
        // TODO add your handling code here:
        if (checkTT()) {
            if (!txtKhachTT.getText().equals("")) {
                txtTienThua.setText(formatter.format((Integer.parseInt(txtKhachTT.getText())) - thanhTien()) + " VNĐ");
            }
        }

    }//GEN-LAST:event_txtKhachTTCaretUpdate
    public Integer khachTT() {
        return Integer.parseInt(txtKhachTT.getText());
    }
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

    private void cbxVCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxVCItemStateChanged
        // TODO add your handling code here:
        Voucher voucher = vcService.getList(Date.valueOf(LocalDate.now())).get(cbxVC.getSelectedIndex());
        txtGiamGia.setText(voucher.getPhanTramGiam().toString());

        txtThanhTien.setText(formatter.format(thanhTien()) + " VNĐ");
    }//GEN-LAST:event_cbxVCItemStateChanged
    public Double thanhTien() {
        return tongTien() - tongTien() / 100 * Integer.parseInt(txtGiamGia.getText());
    }
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
        txtTongTien.setText(formatter.format(tongTien()) + " VNĐ");
    }//GEN-LAST:event_tblGHMouseClicked

    private void txtTimSPCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimSPCaretUpdate
        // TODO add your handling code here:
        String timSP = txtTimSP.getText();
        List<ChiTietSanPham> list = ctspService.timKiem(timSP);
        loadSP(list);
    }//GEN-LAST:event_txtTimSPCaretUpdate

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked
        addToGioHang();
    }//GEN-LAST:event_tblSPMouseClicked

    private void btnXoaAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaAllActionPerformed
        // TODO add your handling code here:
        if (checkTT()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bỏ tất cả sản phẩm ra khỏi giỏ hàng?");
            if (confirm == 0) {
                for (int i = 0; i < tblGH.getRowCount(); i++) {
                    ChiTietHoaDon cthd = cthdService.getCTHDByMaHD(txtMaHD.getText()).get(0);
                    ChiTietSanPham ctsp = cthd.getChiTietSanPham();
                    ctsp.setSoLuong(ctsp.getSoLuong() + cthd.getSoLuong());
                    ctspService.sua(ctsp);
                    cthdService.xoa(cthd);
                }
            }
        }
        loadSP(ctspService.timKiem(txtTimSP.getText()));
        loadGH(cthdService.getCTHDByMaHD(txtMaHD.getText()));

    }//GEN-LAST:event_btnXoaAllActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("FlatLaf Light".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel Cam;
    private edu.poly.shopbangiay.raven.button.Button btnThanhToan;
    private edu.poly.shopbangiay.raven.button.Button btnThemHD;
    private edu.poly.shopbangiay.raven.button.Button btnThemKH;
    private edu.poly.shopbangiay.raven.button.Button btnXoaAll;
    private combo_suggestion.ComboBoxSuggestion cbxKH;
    private combo_suggestion.ComboBoxSuggestion cbxTT;
    private combo_suggestion.ComboBoxSuggestion cbxVC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private edu.poly.shopbangiay.view.KhachHangUI khachHangUI1;
    private javax.swing.JLabel lbCV;
    private javax.swing.JLabel lbName;
    private edu.poly.shopbangiay.view.NhanVienUI nhanVienUI1;
    private edu.poly.shopbangiay.view.CTSPUI sanPhamUI1;
    private table.TableScrollButton tableScrollButton1;
    private table.TableScrollButton tableScrollButton2;
    private table.TableScrollButton tableScrollButton3;
    private javax.swing.JTable tblGH;
    private javax.swing.JTable tblHD;
    private javax.swing.JTable tblSP;
    private edu.poly.shopbangiay.view.ThongKeUI thongKeUI1;
    private textfield.TextField txtGiamGia;
    private textfield.TextField txtKhachTT;
    private textfield.TextField txtMaHD;
    private textfield.TextField txtThanhTien;
    private textfield.TextField txtTienThua;
    private textfield.TextField txtTimSP;
    private textfield.TextField txtTongTien;
    private edu.poly.shopbangiay.view.VoucherUI voucherUI1;
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
                                            cthd.setDonGia(ctsp.getGiaBan());

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
