package utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelUtility {

    public  FileInputStream fi;
    public  FileOutputStream fo;
    public  XSSFWorkbook workbookb;
    public  XSSFSheet sheet;
    public  XSSFRow row;
    public  XSSFCell cell;
    public CellStyle style;
    String path;

    public ExcelUtility(String path){
        this.path=path;

    }

    public int getRowCount(String sheetName) throws IOException {
        fi= new FileInputStream(path);
        workbookb =new XSSFWorkbook(fi);
        sheet = workbookb.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        workbookb.close();
        fi.close();
        return rowCount;
    }

    public int getCellCount(int rownum, String sheetName) throws IOException {
        fi = new FileInputStream(path);
        workbookb =new XSSFWorkbook(fi);
        sheet = workbookb.getSheet(sheetName);
        row = sheet.getRow(rownum);
        int cellcount = row.getLastCellNum();
        workbookb.close();
        fi.close();
        return cellcount;

    }

    public String getCellData(int rownum,int colnum, String sheetName) throws IOException {
        fi = new FileInputStream(path);
        workbookb =new XSSFWorkbook(fi);
        sheet = workbookb.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);
        workbookb.close();
        fi.close();

        DataFormatter formatter= new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(cell);
        } catch (Exception e) {
           data="";
        }
        workbookb.close();
        fi.close();
        return data;
    }

    public void setCellData(String data,int colnum,int rownum, String sheetName) throws IOException {
        File xlfile = new File(path);
        if (!xlfile.exists()) {
            workbookb = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            workbookb.write(fo);
        }
        fi = new FileInputStream(path);
        workbookb = new XSSFWorkbook(fi);

        if (workbookb.getSheetIndex(sheetName) == -1) workbookb.createSheet(sheetName);
        sheet = workbookb.getSheet(sheetName);

        if (sheet.getRow(rownum) == null) sheet.createRow(rownum);
        row = sheet.getRow(rownum);

        cell = row.createCell(colnum);
        cell.setCellValue(data);
        fo = new FileOutputStream(path);
        workbookb.write(fo);
        workbookb.close();
        fi.close();
        fo.close();


    }

    public void fillGreenColor(String sheetName,int rownum,int colnum) throws IOException {

        fi= new FileInputStream(path);
        workbookb=new XSSFWorkbook(fi);
        sheet=workbookb.getSheet(sheetName);

        row=sheet.getRow(rownum);
        cell=row.getCell(colnum);

         style =workbookb.createCellStyle();

         style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
         style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

         cell.setCellStyle(style);
         workbookb.write(fo);
         workbookb.close();
         fi.close();
         fo.close();

    }

    public void fillRedColor(String sheetName,int rownum,int colnum) throws IOException {

        fi= new FileInputStream(path);
        workbookb=new XSSFWorkbook(fi);
        sheet=workbookb.getSheet(sheetName);

        row=sheet.getRow(rownum);
        cell=row.getCell(colnum);

        style =workbookb.createCellStyle();

        style.setFillBackgroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        workbookb.write(fo);
        workbookb.close();
        fi.close();
        fo.close();

    }




}
