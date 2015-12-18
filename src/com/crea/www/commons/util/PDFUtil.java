package com.crea.www.commons.util;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.crea.www.commons.util.vo.PdfEntity;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfOutline;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 操作pdf
 *
 * @author moon
 *
 */
public class PDFUtil {

    static Logger logger = Logger.getLogger(PDFUtil.class);
    static PDDocument document = null;
    static PDFTextStripper stripper = null;

    /**
     * 生成pdf文件
     *
     * @param pdfEntity
     *            参数实体类
     * @param dataList
     * @param row
     * @return
     * @throws IOException
     */
    public static OutputStream writePDF(PdfEntity pdfEntity, List dataList) throws IOException {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    pdfEntity.getOs());
            document.addTitle(pdfEntity.getTitle());
            document.addAuthor(pdfEntity.getAuthor());
            document.addSubject(pdfEntity.getSubject());
            document.addCreator(pdfEntity.getCreator());
            document.addKeywords(pdfEntity.getKeywords());
            document.addHeader(pdfEntity.getPageHeader(),
                    pdfEntity.getPageFooter());
            document.open();
            createDir(document, writer, pdfEntity.getChapter());
            BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",
                    "UniGB-UCS2-H", false);
            Font fontChinese = new Font(bfChinese, 12, Font.NORMAL, Color.GREEN);
            document.add(new Paragraph("第一个测试文档.", new Font(BaseFont
                    .createFont("STSong-Light", "UniGB-UCS2-H",
                            BaseFont.NOT_EMBEDDED), 10, Font.BOLD, Color.RED)));
            // createTable(dataList, row, fontChinese);
            // 5.关闭文档
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return pdfEntity.getOs();

    }

    /**
     * 创建目录
     *
     * @param document
     * @param writer
     * @throws DocumentException
     */
    private static void createDir(Document document, PdfWriter writer,
                                  List<Map<String, String>> Dir) throws DocumentException {
        PdfContentByte cb = writer.getDirectContent();
        PdfOutline root = cb.getRootOutline();
        for (int i = 0; i < Dir.size(); i++) {
            Map<String, String>  map=  Dir.get(i);
            document.newPage();
            document.add(new Chunk("第 "+i+" 章").setLocalDestination(i+""));
            @SuppressWarnings("unused")
            PdfOutline oline1 = new PdfOutline(root, PdfAction.gotoLocalPage(i+"", false), "第 "+i+" 章");
            if(map.size()>1){
                oline1.setOpen(false);
                for (String key : map.keySet()) {
                    document.add(new Paragraph(new Chunk(map.get(key)).setLocalDestination(key)));
                    document.add(new Paragraph(new Chunk(map.get(key)).setLocalDestination(key)));
                    @SuppressWarnings("unused")
                    PdfOutline oline2_1 = new PdfOutline(oline1, PdfAction.gotoLocalPage(key, false), key);
                }
            }
        }


    }

    private static void createTable(List dataList, int row, Font fontChinese)
            throws BadElementException {
        Table aTable = new Table(dataList.size());
        // aTable.setWidths();
        // 设置表格属性
        aTable.setWidth(80); // 占页面宽度 80%
        aTable.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
        aTable.setDefaultVerticalAlignment(Element.ALIGN_MIDDLE);
        aTable.setAutoFillEmptyCells(true); // 自动填满
        aTable.setPadding(1);
        aTable.setSpacing(1);
        aTable.setDefaultCellBorder(1);
        aTable.setBorder(1);
        Cell cell = new Cell(new Phrase("这是一个列数 " + row + "表格", fontChinese));
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setRowspan(3);
        aTable.addCell(cell);
    }

    /**
     * 读取pdf
     *
     * @return
     * @throws IOException
     */
    public static StringBuffer readPDF(File file, Pager pager)
            throws IOException {
        StringBuffer sBuffer = new StringBuffer();
        int startPage = pager.getStart();
        int endPage = pager.getPageSize();// 相当于读取多少页
        try {
            document = PDDocument.load(file);
            stripper = new PDFTextStripper();
            stripper.setSortByPosition(false);// 是否排序
            stripper.setStartPage(startPage);// 开始读取的页数
            stripper.setEndPage(endPage);
            sBuffer.append(stripper.getText(document));
            return sBuffer;
        } catch (IOException e) {
            logger.error("pdf读取失败!PDFUtil->readPDF:" + e);
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
        }
        return null;
    }

    public static void main(String[] args) {
		/*
		 * File file=new File("C:\\Users\\moon\\Desktop\\《HTTP权威指南》高清中文版.pdf");
		 * try { System.out.println(PDFUtil.readPDF(file,new
		 * Pager()).toString()); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 */
        try {
            writePDF(null,null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
