/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package edu.poly.shopbangiay.view.kcr;

import edu.poly.shopbangiay.model.ChatLieu;
import edu.poly.shopbangiay.model.ChiTietSanPham;
import edu.poly.shopbangiay.model.Loai;
import edu.poly.shopbangiay.model.MauSac;
import edu.poly.shopbangiay.model.NSX;
import edu.poly.shopbangiay.model.SanPham;
import edu.poly.shopbangiay.model.Size;
import edu.poly.shopbangiay.service.CTSPService;
import edu.poly.shopbangiay.service.ChatLieuService;
import edu.poly.shopbangiay.service.LoaiService;
import edu.poly.shopbangiay.service.MauSacService;
import edu.poly.shopbangiay.service.NSXService;
import edu.poly.shopbangiay.service.SanPhamService;
import edu.poly.shopbangiay.service.SizeService;
import edu.poly.shopbangiay.service.impl.CTSPServiceImpl;
import edu.poly.shopbangiay.service.impl.ChatLieuServiceImpl;
import edu.poly.shopbangiay.service.impl.LoaiServiceImpl;
import edu.poly.shopbangiay.service.impl.MauSacServiceImpl;
import edu.poly.shopbangiay.service.impl.NSXServiceImpl;
import edu.poly.shopbangiay.service.impl.SanPhamServiceImpl;
import edu.poly.shopbangiay.service.impl.SizeServiceImpl;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Quang
 */
public class UpdateSPUI extends javax.swing.JDialog {

    /**
     * Creates new form UpdateSPUI
     */
    private DefaultComboBoxModel defaultComboBoxModel;
    private CTSPService ctspService = new CTSPServiceImpl();

    private SanPhamService sanPhamService = new SanPhamServiceImpl();
    private LoaiService loaiService = new LoaiServiceImpl();
    private SizeService sizeService = new SizeServiceImpl();
    private ChatLieuService chatLieuService = new ChatLieuServiceImpl();
    private MauSacService mauSacService = new MauSacServiceImpl();
    private NSXService nsxService = new NSXServiceImpl();
    
    public UpdateSPUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        groupTT();

        loadCBX_Loai(loaiService.getList());
        loadCBX_Size(sizeService.getList());
        loadCBX_CL(chatLieuService.getList());
        loadCBX_MS(mauSacService.getList());
        loadCBX_NSX(nsxService.getList());
        loadUpdate();
    }

    public void loadUpdate() {
        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
        txtMaSP.setText(chiTietSanPham.getSanPham().getMa());
        txtTenSP.setText(chiTietSanPham.getSanPham().getTen());
        txtMaVach.setText("");
        txtSoLuong.setText(chiTietSanPham.getSoLuong().toString());
        txtGiaNhap.setText(chiTietSanPham.getGiaNhap().toString());
        txtGiaBan.setText(chiTietSanPham.getGiaBan().toString());
        cbxLoai.setSelectedItem(chiTietSanPham.getLoai());
        cbxMauSac.setSelectedItem(chiTietSanPham.getMauSac());
        cbxSize.setSelectedItem(chiTietSanPham.getSize());
        cbxChatLieu.setSelectedItem(chiTietSanPham.getChatLieu());
        cbxNSX.setSelectedItem(chiTietSanPham.getNsx());
        txtMoTa.setText(chiTietSanPham.getMoTa());
        if (chiTietSanPham.getTinhTrang().toString().equalsIgnoreCase("True")) {
            rdoOnl.setSelected(true);
        } else {
            rdoOff.setSelected(true);
        }
    }
    
    public void loadCBX_Loai(List<Loai> list) {
        defaultComboBoxModel = (DefaultComboBoxModel) cbxLoai.getModel();
        defaultComboBoxModel.removeAllElements();

        for (Loai loai : list) {
            defaultComboBoxModel.addElement(loai);
        }
    }

    public void loadCBX_Size(List<Size> list) {
        defaultComboBoxModel = (DefaultComboBoxModel) cbxSize.getModel();
        defaultComboBoxModel.removeAllElements();

        for (Size size : list) {
            defaultComboBoxModel.addElement(size);
        }
    }

    public void loadCBX_CL(List<ChatLieu> list) {
        defaultComboBoxModel = (DefaultComboBoxModel) cbxChatLieu.getModel();
        defaultComboBoxModel.removeAllElements();

        for (ChatLieu chatLieu : list) {
            defaultComboBoxModel.addElement(chatLieu);
        }
    }

    public void loadCBX_MS(List<MauSac> list) {
        defaultComboBoxModel = (DefaultComboBoxModel) cbxMauSac.getModel();
        defaultComboBoxModel.removeAllElements();

        for (MauSac mauSac : list) {
            defaultComboBoxModel.addElement(mauSac);
        }
    }

    public void loadCBX_NSX(List<NSX> list) {
        defaultComboBoxModel = (DefaultComboBoxModel) cbxNSX.getModel();
        defaultComboBoxModel.removeAllElements();

        for (NSX nsx : list) {
            defaultComboBoxModel.addElement(nsx);
        }
    }

    ButtonGroup rdoTrangThai = new ButtonGroup();
    public void groupTT() {
        rdoTrangThai.add(rdoOnl);
        rdoTrangThai.add(rdoOff);
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
        txtGiaNhap = new textfield.TextField();
        txtMaVach = new textfield.TextField();
        txtTenSP = new textfield.TextField();
        txtGiaBan = new textfield.TextField();
        cbxLoai = new combobox.Combobox();
        txtMaSP = new textfield.TextField();
        cbxSize = new combobox.Combobox();
        cbxChatLieu = new combobox.Combobox();
        cbxMauSac = new combobox.Combobox();
        cbxNSX = new combobox.Combobox();
        txtHinhAnh = new textfield.TextField();
        btnSave = new edu.poly.shopbangiay.raven.button.Button();
        btnChonAnh = new edu.poly.shopbangiay.raven.button.Button();
        btnClose = new edu.poly.shopbangiay.raven.button.Button();
        txtSoLuong = new textfield.TextField();
        btnThemSize = new edu.poly.shopbangiay.raven.button.Button();
        btnThemLoai = new edu.poly.shopbangiay.raven.button.Button();
        btnThemChatLieu = new edu.poly.shopbangiay.raven.button.Button();
        btnThemMau = new edu.poly.shopbangiay.raven.button.Button();
        btnThemNSX = new edu.poly.shopbangiay.raven.button.Button();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        rdoOnl = new radio_button.RadioButtonCustom();
        rdoOff = new radio_button.RadioButtonCustom();
        textAreaScroll = new textarea.TextAreaScroll();
        txtMoTa = new textarea.TextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 153), 1, true));

        txtGiaNhap.setLabelText("Giá nhập");

        txtMaVach.setLabelText("Mã vạch");

        txtTenSP.setLabelText("Tên sản phẩm");

        txtGiaBan.setLabelText("Giá bán");

        cbxLoai.setLabeText("Loại");
        cbxLoai.setPreferredSize(new java.awt.Dimension(64, 46));

        txtMaSP.setLabelText("Mã sản phẩm");

        cbxSize.setLabeText("Size");
        cbxSize.setPreferredSize(new java.awt.Dimension(64, 46));

        cbxChatLieu.setLabeText("Chất liệu");
        cbxChatLieu.setPreferredSize(new java.awt.Dimension(64, 46));

        cbxMauSac.setLabeText("Màu sắc");
        cbxMauSac.setPreferredSize(new java.awt.Dimension(64, 46));

        cbxNSX.setLabeText("Nhà sản xuất");
        cbxNSX.setPreferredSize(new java.awt.Dimension(64, 46));

        txtHinhAnh.setLabelText("Hình ảnh");

        btnSave.setBackground(new java.awt.Color(153, 255, 153));
        btnSave.setText("Lưu");
        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnChonAnh.setText("Chọn");

        btnClose.setBackground(new java.awt.Color(255, 51, 153));
        btnClose.setText("Đóng");
        btnClose.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        txtSoLuong.setLabelText("Số Lượng");

        btnThemSize.setText("+");
        btnThemSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSizeActionPerformed(evt);
            }
        });

        btnThemLoai.setText("+");
        btnThemLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLoaiActionPerformed(evt);
            }
        });

        btnThemChatLieu.setText("+");
        btnThemChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemChatLieuActionPerformed(evt);
            }
        });

        btnThemMau.setText("+");
        btnThemMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMauActionPerformed(evt);
            }
        });

        btnThemNSX.setText("+");
        btnThemNSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNSXActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Cập nhật sản phẩm");

        rdoOnl.setText("Đang kinh doanh");

        rdoOff.setText("Ngừng kinh doanh");

        textAreaScroll.setBackground(new java.awt.Color(255, 255, 255));
        textAreaScroll.setLabelText("Mô tả");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        textAreaScroll.setViewportView(txtMoTa);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textAreaScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                        .addGap(100, 100, 100)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdoOnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdoOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnChonAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(txtMaVach, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtMaSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTenSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(100, 100, 100)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(cbxNSX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnThemNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(cbxLoai, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnThemLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(cbxSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnThemSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(cbxChatLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnThemChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(cbxMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnThemMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThemLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaVach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThemMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThemNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(textAreaScroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 70, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoOnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        SanPham sanPham = sanPhamService.getSPByMa(txtMaSP.getText());
        sanPham.setMa(txtMaSP.getText());
        sanPham.setTen(txtTenSP.getText());
        sanPhamService.sua(sanPham);
        ChiTietSanPham ctsp = ctspService.getCTSPByMaSP(txtMaSP.getText());
        ctsp.setSanPham(sanPham);

        Loai loai = loaiService.getList().get(cbxLoai.getSelectedIndex());
        ctsp.setLoai(loai);

        Size size = sizeService.getList().get(cbxSize.getSelectedIndex());
        ctsp.setSize(size);

        ChatLieu chatLieu = chatLieuService.getList().get(cbxChatLieu.getSelectedIndex());
        ctsp.setChatLieu(chatLieu);

        MauSac mauSac = mauSacService.getList().get(cbxMauSac.getSelectedIndex());
        ctsp.setMauSac(mauSac);

        NSX nsx = nsxService.getList().get(cbxNSX.getSelectedIndex());
        ctsp.setNsx(nsx);

        ctsp.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        ctsp.setGiaNhap(Float.parseFloat(txtGiaNhap.getText()));
        ctsp.setGiaBan(Float.parseFloat(txtGiaBan.getText()));
        ctsp.setMoTa(txtMoTa.getText());

        boolean tinhTrang = !rdoOff.isSelected();
        ctsp.setTinhTrang(tinhTrang);
        ctsp.setHinhAnh(null);

        if (ctspService.sua(ctsp)) {
            JOptionPane.showMessageDialog(this, "Cập nhật thành công");

            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnThemSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemSizeActionPerformed

    private void btnThemLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemLoaiActionPerformed

    private void btnThemChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemChatLieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemChatLieuActionPerformed

    private void btnThemMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemMauActionPerformed

    private void btnThemNSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNSXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemNSXActionPerformed

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
            java.util.logging.Logger.getLogger(UpdateSPUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateSPUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateSPUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateSPUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UpdateSPUI dialog = new UpdateSPUI(new javax.swing.JFrame(), true);
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
    private edu.poly.shopbangiay.raven.button.Button btnChonAnh;
    private edu.poly.shopbangiay.raven.button.Button btnClose;
    private edu.poly.shopbangiay.raven.button.Button btnSave;
    private edu.poly.shopbangiay.raven.button.Button btnThemChatLieu;
    private edu.poly.shopbangiay.raven.button.Button btnThemLoai;
    private edu.poly.shopbangiay.raven.button.Button btnThemMau;
    private edu.poly.shopbangiay.raven.button.Button btnThemNSX;
    private edu.poly.shopbangiay.raven.button.Button btnThemSize;
    private combobox.Combobox cbxChatLieu;
    private combobox.Combobox cbxLoai;
    private combobox.Combobox cbxMauSac;
    private combobox.Combobox cbxNSX;
    private combobox.Combobox cbxSize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private radio_button.RadioButtonCustom rdoOff;
    private radio_button.RadioButtonCustom rdoOnl;
    private textarea.TextAreaScroll textAreaScroll;
    private textfield.TextField txtGiaBan;
    private textfield.TextField txtGiaNhap;
    private textfield.TextField txtHinhAnh;
    private textfield.TextField txtMaSP;
    private textfield.TextField txtMaVach;
    private textarea.TextArea txtMoTa;
    private textfield.TextField txtSoLuong;
    private textfield.TextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
