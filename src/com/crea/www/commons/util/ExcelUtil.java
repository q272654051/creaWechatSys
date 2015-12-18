package com.crea.www.commons.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 操作excel 工具包
 *
 * @author moon
 *
 */
public class ExcelUtil {

    private static Logger logger = Logger.getLogger(ExcelUtil.class);
    private XSSFWorkbook wb = null;
    private XSSFSheet sheet = null;

    public ExcelUtil() {

    }

    public ExcelUtil(XSSFWorkbook wb, XSSFSheet sheet) {
        this.wb = wb;
        this.sheet = sheet;
    }

    /**
     * 读取excel 注意二种格式
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public List excelPutIn(String filePath) throws Exception {
        String suffix = filePath.substring(filePath.lastIndexOf(".") + 1,
                filePath.length());
        if ("xls".equals(suffix)) {
            return parseExcel2003(filePath);
        } else if ("xlsx".equals(suffix)) {
            return parseExcel2007(filePath);
        } else {
            logger.error("ExcelUtil--excelPutIn方法传入文件后缀不正确，文件名为：" + filePath);
        }
        return null;
    }

    /**
     * 操作2007以上版本,读取(理解成把数据从excel整理后导入到数据库)
     *
     * @param filePath
     * @param object
     * @return
     */
    private List<Map> parseExcel2007(String filePath) {
        List<Map> list = new ArrayList<Map>();
        XSSFWorkbook wbHssfWorkbook = null;
        try {
            wbHssfWorkbook = new XSSFWorkbook(new FileInputStream(filePath));
            // 打开工作薄
            XSSFSheet sheet = wbHssfWorkbook.getSheetAt(0);// 打开工作表
            XSSFRow row = null;

            for (int i = 0; i <= sheet.getLastRowNum(); i++) {// 循环读取每一行
                row = sheet.getRow(i);
                int callNum = row.getLastCellNum();// 获取列数是实际的列数,有空格的也算一行
                // row.getPhysicalNumberOfCells()这是获取的出去空的,长度
                Map map = new HashMap<String, String>();
                for (int j = 0; j < callNum; j++) {
                    if (row.getCell(j) == null || "".equals(row.getCell(j))) {
                        // System.out.print("  |  ");
                        map.put("call" + i + "_" + j, " ");
                    } else {
                        row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                        // 用string的话不好处理数据,干脆直接使用string
                        // System.out.print(row.getCell(j).getStringCellValue()+
                        // "  |  ");
                        map.put("call" + i + "_" + j, row.getCell(j)
                                .getStringCellValue());
                    }
                }
                list.add(map);// 装进集合
            }
        } catch (FileNotFoundException e) {
            logger.error("excel2007读取失败", e);
        } catch (IOException e) {
            logger.error("excel2007读取失败", e);
        }
        return list;
    }

    /**
     * 操作2003版 读取
     *
     * @param filePath
     * @param object
     * @return
     */
    private List parseExcel2003(String filePath) {
        List<Map> list = new ArrayList<Map>();
        try {
            HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(filePath));
            HSSFSheet sheet = hwb.getSheetAt(0);
            HSSFRow row;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {// 循环读取每一行
                row = sheet.getRow(i);
                int callNum = row.getLastCellNum();// 获取列数是实际的列数,有空格的也算一行
                // row.getPhysicalNumberOfCells()这是获取的出去空的,长度
                Map map = new HashMap<String, String>();
                for (int j = 0; j < callNum; j++) {
                    if (row.getCell(j) == null || "".equals(row.getCell(j))) {
                        // System.out.print("  |  ");
                        map.put("call" + i + "_" + j, " ");
                    } else {
                        row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                        map.put("call" + i + "_" + j, row.getCell(j)
                                .getStringCellValue());
                    }
                }
                list.add(map);// 装进集合
            }
        } catch (FileNotFoundException e) {
            logger.error("excel2003读取失败", e);
        } catch (IOException e) {
            logger.error("excel2003读取失败", e);
        }
        return list;
    }

    /**
     * 保存excel ,直接生成excel并且提供下载,返回一个url连接 这里的map可以和你自己创建的bean转
     *
     * @param filePath(空白excel表格),list(数据集合),tableHead(表头集合,String数组),os(输出流，例如：response.getOutputStream(),response.printWriter()),sheetName(工作簿名称)
     * @param list
     * @param tableHead
     */
    public static OutputStream saveExcel(String filePath,List<Map<String, Object>> list, String[] tableHead,OutputStream os, String sheetName) {
        if (list.size() == 0) {
            return null;
        }
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);// 可以添加多个
        ExcelUtil excelUtil = new ExcelUtil(workbook, sheet);
        XSSFCellStyle headStyle = excelUtil.getHeadStyle();
        XSSFCellStyle bodyStyle = excelUtil.getBodyStyle();
        // 创建表头
        XSSFRow headRow = sheet.createRow(0);
        XSSFCell cell = null;
        for (int i = 0; i < tableHead.length; i++) {
            cell = headRow.createCell(i);
            cell.setCellStyle(headStyle);
            cell.setCellValue(tableHead[i]);
        }
        for (int j = 0; j < list.size(); j++) {
            XSSFRow bodyRow = sheet.createRow(j + 1); // 创建当前行
            for (int i = 0; i < list.get(j).size(); i++) {
                cell = bodyRow.createCell(i);
                sheet.autoSizeColumn((short) i);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(list.get(j).get("call"+j+"_"+i).toString());
            }
        }
        try {
            workbook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return os;

    }

    /**
     * 合并单元格后给合并后的单元格加边框
     *
     * @param region
     * @param cs
     */
    public void setRegionStyle(CellRangeAddress region, XSSFCellStyle cs) {

        int toprowNum = region.getFirstRow();
        for (int i = toprowNum; i <= region.getLastRow(); i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
                XSSFCell cell = row.getCell(j);// XSSFCellUtil.getCell(row,
                // (short) j);
                cell.setCellStyle(cs);
            }
        }
    }

    /**
     * 设置表头的单元格样式
     *
     * @return
     */
    public XSSFCellStyle getHeadStyle() {
        // 创建单元格样式
        XSSFCellStyle cellStyle = wb.createCellStyle();
        // 设置单元格的背景颜色为淡蓝色
        cellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        // 设置单元格居中对齐
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 设置单元格垂直居中对齐
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 创建单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);
        // 设置单元格字体样式
        XSSFFont font = wb.createFont();
        // 设置字体加粗
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);
        // 设置单元格边框为细线条
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        return cellStyle;
    }

    /**
     * 设置表体的单元格样式
     *
     * @return
     */
    public XSSFCellStyle getBodyStyle() {
        // 创建单元格样式
        XSSFCellStyle cellStyle = wb.createCellStyle();
        // 设置单元格居中对齐
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 设置单元格垂直居中对齐
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 创建单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);
        // 设置单元格字体样式
        XSSFFont font = wb.createFont();
        // 设置字体加粗
        // font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);
        // 设置单元格边框为细线条
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        return cellStyle;
    }

    
    public static void download(OutputStream os,InputStream is){
    	try {
			byte b[] = new byte[1024];
			while(is.read(b) != -1){
				os.write(b);
			}
		} catch (IOException e) {
		}
    }
    public static void main(String[] args) {
		/*
		 * String path = "C:\\Users\\moon\\Desktop\\class\\test.xlsx"; // String
		 * path="C:\\Users\\moon\\Desktop\\test.xls";
		 * 
		 * try { ExcelUtil excelUtil = new ExcelUtil(); List<Map<String,
		 * String>> list = excelUtil.excelPutIn(path); for (int i = 0; i <
		 * list.size(); i++) { Map map = list.get(i); for (int j = 0; j <
		 * map.size(); j++) { String pram = map.get("call" + i + "_" +
		 * j).toString(); System.out.print(pram + "|"); } System.out.println();
		 * } } catch (Exception e) { e.printStackTrace(); }
		 */
		/*
		 * List list=new ArrayList(); for (int i = 0; i < 7; i++) { Map map=new
		 * HashMap<String, String>(); for (int j = 0; j < 3; j++) {
		 * map.put("call"+i+"_"+j, UUID.randomUUID().toString()); }
		 * list.add(map);
		 * 
		 * } String[] tableHead=new String[]{"t1","t2","t3"};
		 * ExcelUtil.saveExcel(null, list, tableHead);
		 */
    }
}
