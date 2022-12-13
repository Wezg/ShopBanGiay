/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package edu.poly.shopbangiay.view;

import edu.poly.shopbangiay.response.ViewThongKe;
import edu.poly.shopbangiay.response.ViewThongKeNgay;
import edu.poly.shopbangiay.service.HoaDonService;
import edu.poly.shopbangiay.service.ThongKeService;
import edu.poly.shopbangiay.service.impl.HoaDonServiceImpl;
import edu.poly.shopbangiay.service.impl.ThongKeServiceImpl;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import table.TableCustom;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Quang
 */
public class ThongKeUI extends javax.swing.JPanel {

    private ThongKeService service = new ThongKeServiceImpl();
    DefaultTableModel model = new DefaultTableModel();
    /**
     * Creates new form ThongKeUI
     */
    HoaDonService hoaDonService = new HoaDonServiceImpl();
    public ThongKeUI() {
        initComponents();
        model = (DefaultTableModel) tblTableThongKe.getModel();

        tongHoaDonNgay();
        tongHoaDonThang();
        tongHoaDonNam();
        fillTpBangTK();
        viewTableTKNgay();
    }

    private void tongHoaDonNgay() {
        txtDoanhThuNgay.setText("");
        txtHDBanNgay.setText("");

        ViewThongKeNgay viewThongKe = service.getTongHoaDonNgay(Date.valueOf(LocalDate.now()));
        txtDoanhThuNgay.setText(viewThongKe.getDoanhThu() + " đ");
        txtHDBanNgay.setText("Hoá đơn bán: " + viewThongKe.getSoHD());
    }

    private void tongHoaDonThang() {
        txtDoanhThuThang.setText("");
        txtHDBanThang.setText("");

        ViewThongKe viewThongKe = service.getTongHoaDonThang(Date.valueOf(LocalDate.now()));
        txtDoanhThuThang.setText(viewThongKe.getDoanhThu() + " đ");
        txtHDBanThang.setText("Hoá đơn bán: " + viewThongKe.getSoDH());
    }

    private void tongHoaDonNam() {
        txtDoanhThuNam.setText("");
        txtHDBanNam.setText("");

        ViewThongKe viewThongKe = service.getTongHoaDonNam(Date.valueOf(LocalDate.now()));
        txtDoanhThuNam.setText(viewThongKe.getDoanhThu() + " đ");
        txtHDBanNam.setText("Hoá đơn bán: " + viewThongKe.getSoDH());
    }

    private void resetError() {
        txtErrorNgayDen.setText("");
        txtErrorNgayTu.setText("");
    }

    private void viewTableTKNgay() {
        String header[] = {"Ngày", "Số hóa đơn", "Doanh thu"};
        model.setColumnIdentifiers(header);

        int thang = cboThang.getSelectedIndex() + 1;
        int nam = 2022;
        List<ViewThongKeNgay> listHDNgay = service.getViewNgayTrongThang(thang, nam);

        model.setRowCount(0);

        for (ViewThongKeNgay s : listHDNgay) {
            Object[] rowData = {
                s.getNgayTao(),
                s.getSoHD(),
                s.getDoanhThu()
            };

            model.addRow(rowData);
        }
    }

    private void viewTableTKThang() {
        String header[] = {"Tháng", "Số hóa đơn", "Doanh thu"};
        model.setColumnIdentifiers(header);

        int nam = 2022;
        List<ViewThongKeNgay> listHDNgay = service.getViewThangTrongNam(nam);

        model.setRowCount(0);

        for (ViewThongKeNgay s : listHDNgay) {
            Object[] rowData = {
                s.getThang(),
                s.getSoHD(),
                s.getDoanhThu()
            };

            model.addRow(rowData);
        }
    }

    private void viewTableTKNam() {
        String header[] = {"Năm", "Số hóa đơn", "Doanh thu"};
        model.setColumnIdentifiers(header);

        int nam = 2022;
        List<ViewThongKeNgay> listHDNgay = service.getViewTKNam(nam);

        model.setRowCount(0);

        for (ViewThongKeNgay s : listHDNgay) {
            Object[] rowData = {
                s.getThang(),
                s.getSoHD(),
                s.getDoanhThu()
            };

            model.addRow(rowData);
        }
    }
    
    
    
    
    private void BieuDoTKNgay() {
        int thang = cboThang.getSelectedIndex() + 1;
        int nam = 2022;
        List<ViewThongKeNgay> listBD = service.getViewNgayTrongThang(thang, nam);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (listBD != null) {
            for (ViewThongKeNgay i : listBD) {
                dataset.addValue(i.getDoanhThu(), "Doanh thu", i.getNgayTao());
            }
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê doanh thu bán giày".toUpperCase(),
                "Thời gian", "Doanh thu",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(PanelBieuDo.getWidth(), 320));

        PanelBieuDo.removeAll();
        PanelBieuDo.setLayout(new CardLayout());
        PanelBieuDo.add(chartPanel);
        PanelBieuDo.validate();
        PanelBieuDo.repaint();
    }

    private void BieuDoTKThang() {
        int nam = 2022;
        List<ViewThongKeNgay> listBD = service.getViewThangTrongNam(nam);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (listBD != null) {
            for (ViewThongKeNgay i : listBD) {
                dataset.addValue(i.getDoanhThu(), "Doanh thu", i.getNgayTao());
            }
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê doanh thu bán giày".toUpperCase(),
                "Tháng", "Doanh thu",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(PanelBieuDo.getWidth(), 320));

        PanelBieuDo.removeAll();
        PanelBieuDo.setLayout(new CardLayout());
        PanelBieuDo.add(chartPanel);
        PanelBieuDo.validate();
        PanelBieuDo.repaint();
    }

    private void BieuDoTKNam() {
        int nam = 2022;
        List<ViewThongKeNgay> listBD = service.getViewTKNam(nam);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (listBD != null) {
            for (ViewThongKeNgay i : listBD) {
                dataset.addValue(i.getDoanhThu(), "Doanh thu", i.getNgayTao());
            }
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê doanh thu bán giày".toUpperCase(),
                "Năm", "Doanh thu",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(320, 320));

        PanelBieuDo.removeAll();
        PanelBieuDo.setLayout(new CardLayout());
        PanelBieuDo.add(chartPanel);
        PanelBieuDo.validate();
        PanelBieuDo.repaint();
    }

    private void fillTpBangTK() {
        int row = cboThoiGian.getSelectedIndex();
        if (row == 0) {
            viewTableTKNgay();
            BieuDoTKNgay();
        } else if (row == 1) {
            viewTableTKThang();
            BieuDoTKThang();
        } else {
            viewTableTKNam();
            BieuDoTKNam();
        }
    }

    private void xuatFileNgay() {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Doanh thu");

            XSSFRow row = null;
            Cell cell = null;

            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Báo cáo doanh thu");

            row = spreadsheet.createRow((short) 3);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Ngày");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Số hoá đơn");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Doanh thu");

            int thang = cboThang.getSelectedIndex() + 1;
            int nam = 2022;
            List<ViewThongKeNgay> list = service.getViewNgayTrongThang(thang, nam);

            for (int i = 0; i < list.size(); i++) {
                ViewThongKeNgay viewThongKeNgay = list.get(i);
                row = spreadsheet.createRow((short) 4 + i);
                row.setHeight((short) 400);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(viewThongKeNgay.getNgayTao());
                row.createCell(2).setCellValue(viewThongKeNgay.getSoHD().toString());
                row.createCell(3).setCellValue(viewThongKeNgay.getDoanhThu().toString());

            }

            FileOutputStream out = new FileOutputStream(new File("D:\\tam\\doanhThuNgay.xlsx"));
            workbook.write(out);
            JOptionPane.showMessageDialog(this, "Xuất file thành công");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void xuatFileThang() {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Doanh thu");

            XSSFRow row = null;
            Cell cell = null;

            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Báo cáo doanh thu");

            row = spreadsheet.createRow((short) 3);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Tháng");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Số hoá đơn");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Doanh thu");

            int nam = 2022;
            List<ViewThongKeNgay> list = service.getViewThangTrongNam(nam);

            for (int i = 0; i < list.size(); i++) {
                ViewThongKeNgay viewThongKeNgay = list.get(i);
                row = spreadsheet.createRow((short) 4 + i);
                row.setHeight((short) 400);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(viewThongKeNgay.getThang());
                row.createCell(2).setCellValue(viewThongKeNgay.getSoHD().toString());
                row.createCell(3).setCellValue(viewThongKeNgay.getDoanhThu().toString());

            }

            FileOutputStream out = new FileOutputStream(new File("D:/doanhThuThang.xlsx"));
            workbook.write(out);
            JOptionPane.showMessageDialog(this, "Xuất file thành công");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void xuatFileNam() {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Doanh thu");

            XSSFRow row = null;
            Cell cell = null;

            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Báo cáo doanh thu");

            row = spreadsheet.createRow((short) 3);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Năm");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Số hoá đơn");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Doanh thu");

            int nam = 2022;
            List<ViewThongKeNgay> list = service.getViewTKNam(nam);

            for (int i = 0; i < list.size(); i++) {
                ViewThongKeNgay viewThongKeNgay = list.get(i);
                row = spreadsheet.createRow((short) 4 + i);
                row.setHeight((short) 400);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(viewThongKeNgay.getThang());
                row.createCell(2).setCellValue(viewThongKeNgay.getSoHD().toString());
                row.createCell(3).setCellValue(viewThongKeNgay.getDoanhThu().toString());

            }

            FileOutputStream out = new FileOutputStream(new File("D:/doanhThuNam.xlsx"));
            workbook.write(out);
            JOptionPane.showMessageDialog(this, "Xuất file thành công");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
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
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtDoanhThuNgay = new javax.swing.JLabel();
        txtHDBanNgay = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDoanhThuThang = new javax.swing.JLabel();
        txtHDBanThang = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtDoanhThuNam = new javax.swing.JLabel();
        txtHDBanNam = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtDoanhThuTuyChon = new javax.swing.JLabel();
        txtHDBanTuyChon = new javax.swing.JLabel();
        txtNgayChonDen = new com.toedter.calendar.JDateChooser();
        btnChon = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtNgayChonTu = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txtErrorNgayDen = new javax.swing.JLabel();
        txtErrorNgayTu = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cboThoiGian = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cboThang = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cboNam = new javax.swing.JComboBox<>();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        PanelBieuDo = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTableThongKe = new javax.swing.JTable();
        btnXuatFile = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 800));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Doanh thu hôm nay");

        txtDoanhThuNgay.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtDoanhThuNgay.setForeground(new java.awt.Color(255, 255, 255));
        txtDoanhThuNgay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDoanhThuNgay.setText("000 đ");

        txtHDBanNgay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtHDBanNgay.setForeground(new java.awt.Color(255, 255, 255));
        txtHDBanNgay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtHDBanNgay.setText("Hoá đơn bán: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                    .addComponent(txtDoanhThuNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtHDBanNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtDoanhThuNgay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(txtHDBanNgay)
                .addGap(22, 22, 22))
        );

        jPanel3.setBackground(new java.awt.Color(255, 102, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Doanh thu tháng này");

        txtDoanhThuThang.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtDoanhThuThang.setForeground(new java.awt.Color(255, 255, 255));
        txtDoanhThuThang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDoanhThuThang.setText("000 đ");

        txtHDBanThang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtHDBanThang.setForeground(new java.awt.Color(255, 255, 255));
        txtHDBanThang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtHDBanThang.setText("Hoá đơn bán: ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHDBanThang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                            .addComponent(txtDoanhThuThang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtDoanhThuThang)
                .addGap(18, 18, 18)
                .addComponent(txtHDBanThang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 204, 153));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Doanh thu năm nay");

        txtDoanhThuNam.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtDoanhThuNam.setForeground(new java.awt.Color(255, 255, 255));
        txtDoanhThuNam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDoanhThuNam.setText("000 đ");

        txtHDBanNam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtHDBanNam.setForeground(new java.awt.Color(255, 255, 255));
        txtHDBanNam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtHDBanNam.setText("Hoá đơn bán: ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addComponent(txtDoanhThuNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtHDBanNam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtDoanhThuNam)
                .addGap(18, 18, 18)
                .addComponent(txtHDBanNam)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(51, 255, 153));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Tùy chọn");

        txtDoanhThuTuyChon.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtDoanhThuTuyChon.setForeground(new java.awt.Color(255, 255, 255));
        txtDoanhThuTuyChon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDoanhThuTuyChon.setText("000 đ");

        txtHDBanTuyChon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtHDBanTuyChon.setForeground(new java.awt.Color(255, 255, 255));
        txtHDBanTuyChon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtHDBanTuyChon.setText("Hoá đơn bán: ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtDoanhThuTuyChon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addComponent(txtHDBanTuyChon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(txtDoanhThuTuyChon)
                .addGap(18, 18, 18)
                .addComponent(txtHDBanTuyChon)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtNgayChonDen.setDateFormatString("yyyy-MM-dd");
        txtNgayChonDen.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtNgayChonDenPropertyChange(evt);
            }
        });

        btnChon.setText("Chọn");
        btnChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonActionPerformed(evt);
            }
        });

        jLabel5.setText("Đến: ");

        txtNgayChonTu.setDateFormatString("yyyy-MM-dd");
        txtNgayChonTu.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtNgayChonTuPropertyChange(evt);
            }
        });

        jLabel6.setText("Từ:");

        txtErrorNgayDen.setForeground(new java.awt.Color(255, 51, 51));

        txtErrorNgayTu.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtErrorNgayTu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgayChonTu, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtErrorNgayDen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgayChonDen, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnChon))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayChonDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChon))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtErrorNgayDen))
                    .addComponent(txtNgayChonTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtErrorNgayTu)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("Thời gian:");

        cboThoiGian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ngày trong tháng", "Tháng trong năm", "Năm" }));
        cboThoiGian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThoiGianActionPerformed(evt);
            }
        });

        jLabel8.setText("Tháng:");

        cboThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cboThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThangActionPerformed(evt);
            }
        });

        jLabel9.setText("Năm:");

        cboNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2022", "2021", "2020", "2019" }));
        cboNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamActionPerformed(evt);
            }
        });

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        PanelBieuDo.setBackground(new java.awt.Color(255, 255, 255));
        PanelBieuDo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout PanelBieuDoLayout = new javax.swing.GroupLayout(PanelBieuDo);
        PanelBieuDo.setLayout(PanelBieuDoLayout);
        PanelBieuDoLayout.setHorizontalGroup(
            PanelBieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1272, Short.MAX_VALUE)
        );
        PanelBieuDoLayout.setVerticalGroup(
            PanelBieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 434, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelBieuDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelBieuDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Biểu đồ", jPanel9);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        tblTableThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblTableThongKe);

        btnXuatFile.setText("Xuất File");
        btnXuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1276, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(209, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Bảng", jPanel8);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(214, 214, 214)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboThang, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(164, 164, 164)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cboThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cboThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1316, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNgayChonDenPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtNgayChonDenPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayChonDenPropertyChange

    private void btnChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonActionPerformed

        java.util.Date batDau = txtNgayChonTu.getDate();
        java.util.Date ketThuc = txtNgayChonDen.getDate();

        int error = 0;

        if (batDau.toString().length() == 0) {
            txtErrorNgayTu.setText("Không được để trống ngày bắt đầu!!");
            error++;
        }

        if (ketThuc.toString().length() == 0) {
            txtErrorNgayDen.setText("Không được để trống ngày kết thúc!!");
            error++;
        }

        if (error == 0) {
            resetError();
            txtDoanhThuTuyChon.setText("");
            txtHDBanTuyChon.setText("");

            ViewThongKe viewThongKe = service.getTongHoaDonTuyChon(batDau, ketThuc);
            txtDoanhThuTuyChon.setText(viewThongKe.getDoanhThu() + " đ");
            txtHDBanTuyChon.setText("Hoá đơn bán: " + viewThongKe.getSoDH());

        }
    }//GEN-LAST:event_btnChonActionPerformed

    private void txtNgayChonTuPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtNgayChonTuPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayChonTuPropertyChange

    private void cboThoiGianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThoiGianActionPerformed

        fillTpBangTK();
    }//GEN-LAST:event_cboThoiGianActionPerformed

    private void cboThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThangActionPerformed
        fillTpBangTK();
    }//GEN-LAST:event_cboThangActionPerformed

    private void cboNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamActionPerformed
        fillTpBangTK();
    }//GEN-LAST:event_cboNamActionPerformed

    private void btnXuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileActionPerformed
        int row = cboThoiGian.getSelectedIndex();

        if (row == 0) {
            xuatFileNgay();
        } else if (row == 1) {
            xuatFileThang();
        } else {
            xuatFileNam();
        }
    }//GEN-LAST:event_btnXuatFileActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelBieuDo;
    private javax.swing.JButton btnChon;
    private javax.swing.JButton btnXuatFile;
    private javax.swing.JComboBox<String> cboNam;
    private javax.swing.JComboBox<String> cboThang;
    private javax.swing.JComboBox<String> cboThoiGian;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblTableThongKe;
    private javax.swing.JLabel txtDoanhThuNam;
    private javax.swing.JLabel txtDoanhThuNgay;
    private javax.swing.JLabel txtDoanhThuThang;
    private javax.swing.JLabel txtDoanhThuTuyChon;
    private javax.swing.JLabel txtErrorNgayDen;
    private javax.swing.JLabel txtErrorNgayTu;
    private javax.swing.JLabel txtHDBanNam;
    private javax.swing.JLabel txtHDBanNgay;
    private javax.swing.JLabel txtHDBanThang;
    private javax.swing.JLabel txtHDBanTuyChon;
    private com.toedter.calendar.JDateChooser txtNgayChonDen;
    private com.toedter.calendar.JDateChooser txtNgayChonTu;
    // End of variables declaration//GEN-END:variables
}
