package edu.poly.shopbangiay.Ultilities;

import edu.poly.shopbangiay.model.SanPham;
import edu.poly.shopbangiay.service.SanPhamService;
import edu.poly.shopbangiay.service.impl.SanPhamServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadExcel {
    public static final int COLUMN_INDEX_MaSP = 0;
    public static final int COLUMN_INDEX_TenSP = 1;



    public static void main(String[] args) throws IOException {
        SanPhamService sanPhamService = new SanPhamServiceImpl();

        JFileChooser chonFile = new JFileChooser("/");
        chonFile.showOpenDialog(null);
        File file = chonFile.getSelectedFile();
        final String excelFilePath = file.getPath();
        final List<SanPham> listSP = readExcel(excelFilePath);
        for (SanPham sanPham : listSP) {
            sanPhamService.them(sanPham);
        }
    }

    public static List<SanPham> readExcel(String excelFilePath) throws IOException {
        List<SanPham> listSP = new ArrayList<>();

        // Get file
        InputStream inputStream = new FileInputStream(new File(excelFilePath));

        // Get workbook
        Workbook workbook = getWorkbook(inputStream, excelFilePath);

        // Get sheet
        Sheet sheet = workbook.getSheetAt(0);

        // Get all rows
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }
            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            // Read cells and set value for book object
            SanPham sanPham = new SanPham();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case COLUMN_INDEX_MaSP:
                        sanPham.setMa((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_TenSP:
                        sanPham.setTen((String) getCellValue(cell));
                        break;
                    default:
                        break;
                }

            }
            listSP.add(sanPham);
        }

        workbook.close();
        inputStream.close();

        return listSP;
    }

    // Get Workbook
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("File được chọn không phải là file excel");
        }

        return workbook;
    }

    // Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
    }
}
