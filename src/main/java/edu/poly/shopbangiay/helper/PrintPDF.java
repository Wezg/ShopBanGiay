package edu.poly.shopbangiay.helper;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Table;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import edu.poly.shopbangiay.model.HoaDon;
import edu.poly.shopbangiay.service.HoaDonService;
import edu.poly.shopbangiay.service.impl.HoaDonServiceImpl;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.util.Locale;

public class PrintPDF {
    HoaDonService hoaDonService = new HoaDonServiceImpl();
    Locale vn = new Locale("vi", "VN");

    //NumberFormat nbf =
    public void a(DefaultTableModel defaultTableModel, String mahd, int tienkh, int tienthoi) {
        HoaDon hoaDon = hoaDonService.getHDByMa(mahd);
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        try {
// anh em khi code muốn chạy được phần in hóa đơn thì đổi source file nơi lưu trữ( cái dưới) và font chữ ( 2 cái dưới, 
//front chữ đã tải và để trong file dự án rồi chỉ việc đổi link đường dẫn tới nó thôi) cre; Phong
            File newFile = new File("HDpdf/" + mahd + ".pdf");
            PdfWriter.getInstance(document, new FileOutputStream(newFile.getAbsoluteFile().getPath()));
            document.open();
            Font f = new Font(BaseFont.createFont("arialbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
            f.setSize(16);
            Font f2 = new Font(BaseFont.createFont("arialbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
            f2.setSize(14);
            //f.setStyle(Font.NORMAL);
            Paragraph title1 = new Paragraph("WegHiz Store", FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLDITALIC, new CMYKColor(0, 218, 64, 255)));
            //Chapter chapter1 = new Chapter(title1, 1);
            title1.setAlignment(Element.ALIGN_CENTER);
            document.add(title1);
            PdfPTable a = new PdfPTable(2);
            a.setSpacingBefore(35);

            PdfPCell a8 = new PdfPCell(new Phrase("Địa chỉ: Số 19A ngách 91/6, Kiều Mai, Từ Liêm, Hà Nội", f2));
            a.addCell(a8).setBorder(0);
            PdfPCell a7 = new PdfPCell(new Phrase("", f2));
            a.addCell(a7).setBorder(0);

            PdfPCell a6 = new PdfPCell(new Phrase("", f2));
            a.addCell(a6).setBorder(0);
            PdfPCell a2 = new PdfPCell(new Phrase("Mã đơn: " + hoaDon.getMa(), f2));
            a.addCell(a2).setBorder(0);
            PdfPCell a3 = new PdfPCell(new Phrase("", f2));
            a.addCell(a3).setBorder(0);

            PdfPCell a4 = new PdfPCell(new Phrase("Ngày tạo: " + hoaDon.getNgayTao(), f2));
            a.addCell(a4).setBorder(0);
            PdfPCell a5 = new PdfPCell(new Phrase(hoaDon.getNgayTao() + "", f2));
            a.addCell(a5).setBorder(0);

            PdfPTable t = new PdfPTable(5);
            t.setSpacingBefore(35);
            t.setSpacingAfter(35);

            PdfPCell c1 = new PdfPCell(new Phrase("Sản phẩm", f));
            t.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("Số lượng", f));
            t.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("Đơn giá", f));
            t.addCell(c3);
            PdfPCell c4 = new PdfPCell(new Phrase("Thành tiền", f));
            t.addCell(c4);

            for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
                if (mahd.equals(defaultTableModel.getValueAt(i, 1).toString())) {
                    PdfPCell c5 = new PdfPCell(new Phrase(defaultTableModel.getValueAt(i, 2).toString(), f2));
                    t.addCell(c5);
                    t.addCell(defaultTableModel.getValueAt(i, 3).toString());
                    t.addCell(defaultTableModel.getValueAt(i, 4).toString());
                    t.addCell(defaultTableModel.getValueAt(i, 7).toString());
                }
            }
            document.add(a);
            document.add(t);
            PdfPTable b = new PdfPTable(2);
            PdfPCell b1 = new PdfPCell(new Phrase("Tổng tiền: ", f2));
            b.addCell(b1).setBorder(0);
            PdfPCell b2 = new PdfPCell(new Phrase("" + NumberFormat.getInstance().format(hoaDon.getThanhTien()) + " VNĐ", f2));
            b.addCell(b2).setBorder(0);
            PdfPCell b3 = new PdfPCell(new Phrase("Tiền khách trả: ", f2));
            b.addCell(b3).setBorder(0);
            PdfPCell b4 = new PdfPCell(new Phrase("" + NumberFormat.getInstance().format(tienkh) + " VNĐ", f2));
            b.addCell(b4).setBorder(0);
//            PdfPCell b5 = new PdfPCell(new Phrase("Tiền thối: ", f2));
//            b.addCell(b5).setBorder(0);
//            PdfPCell b6 = new PdfPCell(new Phrase(""+tienthoi, f2));
//            b.addCell(b6).setBorder(0);
            document.add(b);
//            t.addCell("1.1");
//            t.addCell("1.2");
//            t.addCell("1.3");

            document.close();
            System.out.println("Write file succes!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
