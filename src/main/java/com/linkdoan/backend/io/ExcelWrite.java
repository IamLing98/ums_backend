package com.linkdoan.backend.io;

import com.linkdoan.backend.model.SheetData;
import com.linkdoan.backend.util.CommonUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * excel operation public class - provide excel according to template output
 *
 * @author IamLing98
 */

public class ExcelWrite {

    /**
     * Sheet copy
     *
     * @param fromSheet
     * @param toSheet
     * @param copyValueFlag
     */

    public static void copySheet(Workbook wb, Sheet fromSheet, Sheet toSheet,
                                 boolean copyValueFlag) {

        mergerRegion(fromSheet, toSheet);
        int index = 0;
        for (Iterator<Row> rowIt = fromSheet.rowIterator(); rowIt.hasNext(); ) {
            Row tmpRow = rowIt.next();
            Row newRow = toSheet.createRow(tmpRow.getRowNum());

            CellStyle style = tmpRow.getRowStyle();
            if (style != null)
                newRow.setRowStyle(tmpRow.getRowStyle());

            newRow.setHeight(tmpRow.getHeight());
            if (index == 0) {
                int first = tmpRow.getFirstCellNum();
                int last = tmpRow.getLastCellNum();
                for (int i = first; i < last; i++) {
                    int w = fromSheet.getColumnWidth(i);
                    toSheet.setColumnWidth(i, w + 1);
                }
                toSheet.setDefaultColumnWidth(fromSheet.getDefaultColumnWidth());
            }

            //row copy
            copyRow(wb, tmpRow, newRow, copyValueFlag);

            index++;
        }
    }

    /**
     * Line copy function
     *
     * @param fromRow
     * @param toRow
     */
    static void copyRow(Workbook wb, Row fromRow, Row toRow, boolean copyValueFlag) {
        for (Iterator<Cell> cellIt = fromRow.cellIterator(); cellIt.hasNext(); ) {
            Cell tmpCell = cellIt.next();
            Cell newCell = toRow.createCell(tmpCell.getColumnIndex());
            copyCell(wb, tmpCell, newCell, copyValueFlag);
        }
    }

    static void mergerRegion(Sheet fromSheet, Sheet toSheet) {
        int sheetMergerCount = fromSheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergerCount; i++) {

            CellRangeAddress cra = fromSheet.getMergedRegion(i);

            toSheet.addMergedRegion(cra);
        }
    }

    public static void copyCell(Workbook wb, Cell srcCell, Cell distCell,
                                boolean copyValueFlag) {

        CellStyle newstyle = wb.createCellStyle();
        //copyCellStyle(srcCell.getCellStyle(), newstyle);
        //distCell.setEncoding(srcCell.);
        newstyle.cloneStyleFrom(srcCell.getCellStyle());
        //style
        distCell.setCellStyle(newstyle);
        //Comment
        if (srcCell.getCellComment() != null) {
            distCell.setCellComment(srcCell.getCellComment());
        }
        // different data type processing
        CellType srcCellType = srcCell.getCellTypeEnum();
        distCell.setCellType(srcCellType);


        if (copyValueFlag) {
            if (srcCellType == CellType.NUMERIC) {
                if (DateUtil.isCellDateFormatted(srcCell)) {
                    distCell.setCellValue(srcCell.getDateCellValue());
                } else {
                    distCell.setCellValue(srcCell.getNumericCellValue());
                }
            } else if (srcCellType == CellType.STRING) {
                distCell.setCellValue(srcCell.getRichStringCellValue());
            } else if (srcCellType == CellType.BLANK) {
                // nothing21
            } else if (srcCellType == CellType.BOOLEAN) {
                distCell.setCellValue(srcCell.getBooleanCellValue());
            } else if (srcCellType == CellType.ERROR) {
                distCell.setCellErrorValue(srcCell.getErrorCellValue());

            } else if (srcCellType == CellType.FORMULA) {
                distCell.setCellFormula(srcCell.getCellFormula());
            } else { // nothing29
            }
        }
    }

    static void writeDataToCell(SheetData sheetData, String keyWind, Cell cell, Sheet sheet) {
        String key = keyWind.substring(2, keyWind.length() - 1);

        if (keyWind.startsWith("#")) {
            Object value = sheetData.get(key);
            // is empty and replaced with an empty string
            if (value == null)
                value = "";

            String cellValue = cell.getStringCellValue();
            cellValue = cellValue.replace(keyWind, value.toString());

            cell.setCellValue(cellValue);

        } else if (keyWind.startsWith("$")) {
            int rowindex = cell.getRowIndex();
            int columnindex = cell.getColumnIndex();

            List<? extends Object> listdata = sheetData.getDatas();

            //When it is not empty, it starts to fill.
            if (listdata != null && !listdata.isEmpty()) {
                for (Object o : listdata) {
                    Object cellValue = CommonUtils.getValue(o, key);

                    Row row = sheet.getRow(rowindex);
                    if (row == null) {
                        row = sheet.createRow(rowindex);
                    }
                    Cell c = row.getCell(columnindex);
                    if (c == null)
                        c = row.createCell(columnindex);
                    if (cell.getCellStyle() != null) {
                        c.setCellStyle(cell.getCellStyle());

                    }

                    if (cell.getCellTypeEnum() != null) {
                        c.setCellType(cell.getCellTypeEnum());

                    }

                    if (cellValue != null) {
                        if (cellValue instanceof Number || CommonUtils.isNumber(cellValue))
                            c.setCellValue(Double.valueOf(cellValue.toString()));
                        else if (cellValue instanceof Boolean)
                            c.setCellValue((Boolean) cellValue);
                        else if (cellValue instanceof Date)
                            c.setCellValue((Date) cellValue);
                        else
                            c.setCellValue(cellValue.toString());
                    } else {

                        //The data is empty. If the current cell already has data, it will be reset to empty.
                        if (c.getStringCellValue() != null) {
                            c.setCellValue("");
                        }
                    }
                    rowindex++;
                }
            } else {
                //list data is empty, then replace all empty strings with $
                String cellValue = "";

                cell.setCellValue(cellValue);

            }
        }

    }

    public static void writeDataToSheet(SheetData sheetData, Sheet sheet) {
        for (Iterator<Row> rowIt = sheet.rowIterator(); rowIt.hasNext(); ) {
            Row row = rowIt.next();
            for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                if (cell != null && cell.getCellTypeEnum() == CellType.STRING && cell.getStringCellValue() != null
                        && (cell.getStringCellValue().contains("$") || cell.getStringCellValue().contains("#"))) {
                    // peeling # $
                    String[] winds = CommonUtils.getWildcard(cell.getStringCellValue().trim());

                    for (String wind : winds) {
                        writeDataToCell(sheetData, wind, cell, sheet);
                    }
                }
            }

        }
    }

    /**
     * Write excel data
     *
     * @param model      The template used is located under src/model/ The first sheet page of the template must be the template sheet
     * @param sheetDatas template data
     */
    public static void writeData(String model, OutputStream out, SheetData... sheetDatas) {

        Workbook wb = null;
        try {

            InputStream input = ExcelWrite.class.getClassLoader().getResourceAsStream(model);

            if (input == null) {
                throw new RuntimeException("model excel file load error :/model/" + model + " , check model file is exists !");
            }

            if (model.endsWith(".xlsx"))
                wb = new XSSFWorkbook(input);
            else if (model.endsWith(".xls"))
                wb = new HSSFWorkbook(input);
            else
                throw new RuntimeException("model file format is not valid , this : " + model + " , eg:.xlsx or xls");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();

            throw new RuntimeException("model excel file load error :/model/" + model);
        }

        Sheet source = wb.getSheetAt(0);

        //If you use one, use the template directly.
        int size = sheetDatas.length;
        for (int i = 0; i < size; i++) {

            if (i == 0) {
                wb.setSheetName(0, sheetDatas[0].getName());

            } else {
                Sheet toSheet = wb.createSheet(sheetDatas[i].getName());
                copySheet(wb, source, toSheet, true);
            }
        }

        for (int i = 0; i < size; i++) {
            writeDataToSheet(sheetDatas[i], wb.getSheetAt(i));
        }

        try {
            wb.write(out);
            out.flush();
            wb.close();
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
