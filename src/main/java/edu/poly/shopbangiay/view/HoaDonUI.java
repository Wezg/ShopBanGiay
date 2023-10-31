/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package edu.poly.shopbangiay.view;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
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

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.border.EmptyBorder;

/**
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


    DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    DecimalFormat formatter = new DecimalFormat("###,###,###");

    public HoaDonUI() {
        initComponents();

        TableCustom.apply(jScrollPane1, TableCustom.TableType.DEFAULT);
        TableCustom.apply(jScrollPane2, TableCustom.TableType.DEFAULT);
        loadHD(hoaDonService.timKiem(txtTim.getText()));
        loadCBX_TT();



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

    public void loadHD(List<HoaDon> list) {
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

    public void loadHD_Date(List<HoaDon> list) {
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

    public void loadCT(List<ChiTietHoaDon> list) {
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
            list = hoaDonService.timKiem(txtTim.getText());
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

        for (int i = 0; i < tblCT.getRowCount(); ) {
            ChiTietHoaDon cthd = cthdService.getCTHDByMaHD(hoaDon.getMa()).get(i);

            sum += (cthd.getSoLuong() * cthd.getDonGia());
            i++;
        }
        return sum;
    }

    //in hoa don
    static Cell getHeaderTextCell(String textValue) {

        return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getHeaderTextCellValue(String textValue) {

        return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getBillingandShippingCell(String textValue) {

        return new Cell().add(textValue).setFontSize(30f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER);
    }

    static Cell getCell0fLeft(String textValue, Boolean isBold) {
        Cell myCell = new Cell().add(textValue).setFontSize(15f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);

        return isBold ? myCell.setBold() : myCell;
    }

    public void inHD() {
        int chon = tblHD.getSelectedRow();
        HoaDon hoaDon = hoaDonService.timKiem(txtTim.getText()).get(chon);
        List<ChiTietHoaDon> listCTHD = cthdService.getCTHDByMaHD(hoaDon.getMa());
//        List<HoaDon> lisHD = hoaDonService.timKiem(txtTim.getText());
        if (chon != -1) {
            if (hoaDon.getTinhTrang() == 1) {
                try {
                    String path = "HDpdf/" + hoaDon.getMa() + ".pdf";
                    PdfWriter pdfWrite = new PdfWriter(path);
                    PdfDocument pdfDocument = new PdfDocument(pdfWrite);
                    pdfDocument.setDefaultPageSize(PageSize.A4);
                    Document document = new Document(pdfDocument);
                    float threecol = 190f;
                    float twocol = 285f;
                    float twocol150 = twocol + 150f;
                    float twocolumnWidth[] = {twocol150, twocol};
                    float threeColumnWidth[] = {threecol, threecol, threecol};
                    float fullwidth[] = {threecol * 3};

                    Paragraph onesp = new Paragraph("\n");

                    Table table = new Table(fullwidth);
                    table.addCell(new Cell().add("WegHiz Store").setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontSize(50f));
                    Table nestedtabe = new Table(new float[]{twocol / 2, twocol / 2});
                    table.addCell(new Cell().add(nestedtabe).setBorder(Border.NO_BORDER));
                    document.add(table);

                    Border gb = new SolidBorder(Color.GRAY, 1f / 2f);
                    Table diveder = new Table(fullwidth);
                    diveder.setBorder(gb);

                    document.add(onesp);
                    document.add(diveder);

                    Table twoColTable = new Table(fullwidth);
                    twoColTable.addCell(getBillingandShippingCell("HOA DON BAN HANG")).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontSize(20f);
                    document.add(twoColTable.setMarginBottom(12f));

                    Table twoColTable2 = new Table(twocolumnWidth);
                    twoColTable2.addCell(getCell0fLeft("Ma HD:", true));
                    twoColTable2.addCell(getCell0fLeft("Khach hang:", true));
                    twoColTable2.addCell(getCell0fLeft(hoaDon.getMa(), false));//ma hoa don
                    twoColTable2.addCell(getCell0fLeft(hoaDon.getKhachHang().getTen(), false));// ten khach hang
                    document.add(twoColTable2);

                    Table twoCollTable3 = new Table(twocolumnWidth);
                    twoCollTable3.addCell(getCell0fLeft("Ngay thanh toan", true));
                    twoCollTable3.addCell(getCell0fLeft("Dien thoai", true));
                    twoCollTable3.addCell(getCell0fLeft(simpleDateFormat.format(hoaDon.getNgayTT()), false));//ten nhan vien
                    twoCollTable3.addCell(getCell0fLeft(hoaDon.getKhachHang().getSdt(), false));
                    document.add(twoCollTable3);

                    Table tabledivider2 = new Table(fullwidth);
                    Border dgb = new SolidBorder(Color.GRAY, 0.5f);
                    document.add(tabledivider2.setBorder(dgb));
                    Paragraph productPara = new Paragraph("San pham");

                    document.add(productPara.setBold().setFontSize(15f));
                    Table threeColTable1 = new Table(threeColumnWidth);
                    threeColTable1.setBackgroundColor(Color.BLACK, 0.7f);

                    threeColTable1.addCell(new Cell().add("Ten").setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT).setMarginLeft(10));
                    threeColTable1.addCell(new Cell().add("So luong").setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
                    threeColTable1.addCell(new Cell().add("Don gia").setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).setMarginRight(10));
                    document.add(threeColTable1);

                    Table threeColTable2 = new Table(threeColumnWidth);

                    float totalSum = 0;

                    for (ChiTietHoaDon cthd : listCTHD) {

                        float total = cthd.getSoLuong() * cthd.getDonGia();
                        totalSum += total;
                        threeColTable2.addCell(new Cell().add(cthd.getChiTietSanPham().getSanPham().getTen()).setBorder(Border.NO_BORDER)).setMarginLeft(10f);
                        threeColTable2.addCell(new Cell().add(String.valueOf(cthd.getSoLuong())).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
                        threeColTable2.addCell(new Cell().add(formatter.format(cthd.getDonGia()) + " VNĐ").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMarginRight(10f);
                    }

                    document.add(threeColTable2.setMarginBottom(20f));
//                    float onetwo[] = {threecol + 125f, threecol * 2};
//                    Table threeColTable4 = new Table(onetwo);
//                    threeColTable4.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
//                    threeColTable4.addCell(new Cell().add(tabledivider2).setBorder(Border.NO_BORDER));
//                    document.add(threeColTable4);
                    Table tabledivider3 = new Table(fullwidth);
                    Border dgb2 = new SolidBorder(Color.GRAY, 0.5f);
                    document.add(tabledivider3.setBorder(dgb2));

                    Table threeColTable3 = new Table(threeColumnWidth);
                    threeColTable3.addCell(new Cell().add("Tong tien hang").setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
                    threeColTable3.addCell(new Cell().add("").setBorder(Border.NO_BORDER)).setMarginLeft(10f).setTextAlignment(TextAlignment.CENTER);
                    threeColTable3.addCell(new Cell().add(formatter.format(totalSum) + " VNĐ").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMarginRight(15f);

                    Table threeColTable5 = new Table(threeColumnWidth);
                    threeColTable3.addCell(new Cell().add("Giam gia").setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
                    threeColTable3.addCell(new Cell().add("").setBorder(Border.NO_BORDER)).setMarginLeft(10f).setTextAlignment(TextAlignment.CENTER);
                    threeColTable3.addCell(new Cell().add(formatter.format(totalSum /100 * hoaDon.getVoucher().getPhanTramGiam()) + " VNĐ").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMarginRight(15f);

                    Table threeColTable6 = new Table(threeColumnWidth);
                    threeColTable3.addCell(new Cell().add("Khach phai tra").setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
                    threeColTable3.addCell(new Cell().add("").setBorder(Border.NO_BORDER)).setMarginLeft(10f).setTextAlignment(TextAlignment.CENTER);
                    threeColTable3.addCell(new Cell().add(formatter.format(totalSum - (totalSum /100 * hoaDon.getVoucher().getPhanTramGiam())) + " VNĐ").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMarginRight(15f);

                    Table threeColTable7 = new Table(threeColumnWidth);
                    threeColTable3.addCell(new Cell().add("Tien khach dua").setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
                    threeColTable3.addCell(new Cell().add("").setBorder(Border.NO_BORDER)).setMarginLeft(10f).setTextAlignment(TextAlignment.CENTER);
                    threeColTable3.addCell(new Cell().add(formatter.format(totalSum - (totalSum /100 * hoaDon.getVoucher().getPhanTramGiam())) + " VNĐ").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMarginRight(15f);


                    Table threeColTable8 = new Table(threeColumnWidth);
                    threeColTable3.addCell(new Cell().add("Tra lai").setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
                    threeColTable3.addCell(new Cell().add("").setBorder(Border.NO_BORDER)).setMarginLeft(10f).setTextAlignment(TextAlignment.CENTER);
                    threeColTable3.addCell(new Cell().add(formatter.format((totalSum - (totalSum /100 * hoaDon.getVoucher().getPhanTramGiam())) - (totalSum - (totalSum /100 * hoaDon.getVoucher().getPhanTramGiam())) ) + " VNĐ").setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMarginRight(15f);


                    document.add(threeColTable3);
                    document.add(threeColTable5);
                    document.add(threeColTable6);
                    document.add(threeColTable7);
                    document.add(threeColTable8);
                    document.add(tabledivider2);
                    document.add(new Paragraph("\n"));
                    Table tb = new Table(fullwidth);
                    tb.addCell(new Cell().add("Cam on quy khach, hen gap lai!").setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontSize(10f));

                    document.add(tb);

                    document.close();

                    JOptionPane.showMessageDialog(this, "Xuất hóa đơn thành công tại : \n" + path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Hóa đơn chưa thanh toán");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn cần in");
        }

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
        btnInHD = new edu.poly.shopbangiay.raven.button.Button();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtTim = new textfield.TextField();
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

        btnInHD.setBackground(new java.awt.Color(153, 255, 102));
        btnInHD.setText("In HD");
        btnInHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnInHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHDActionPerformed(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 576, Short.MAX_VALUE)
                .addComponent(btnInHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbTongTien)
                    .addComponent(jLabel4)
                    .addComponent(lbGG)
                    .addComponent(jLabel6)
                    .addComponent(lbTT)
                    .addComponent(btnInHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(16, Short.MAX_VALUE))
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
        lbTT.setText(formatter.format(tongTien() - (tongTien() / 100 * voucher.getPhanTramGiam())) + " VNĐ");
    }//GEN-LAST:event_tblHDMouseClicked

    private void txtTimCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimCaretUpdate
        // TODO add your handling code here:
        String tim = txtTim.getText();
        List<HoaDon> list = hoaDonService.timKiem(tim);
        loadHD(list);
    }//GEN-LAST:event_txtTimCaretUpdate

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

    private void btnInHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHDActionPerformed
        // TODO add your handling code here:
        inHD();
    }//GEN-LAST:event_btnInHDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private edu.poly.shopbangiay.raven.button.Button btnInHD;
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
    private textfield.TextField txtTim;
    // End of variables declaration//GEN-END:variables
}
