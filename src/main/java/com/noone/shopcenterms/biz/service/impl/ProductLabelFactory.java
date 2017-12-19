package com.noone.shopcenterms.biz.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ProductLabelFactory {
	


	private static ProductLabelFactory FACTORY_INSTANCE = null;

	private ProductLabelFactory() {

	}

	public static ProductLabelFactory getInstance() {
		if (FACTORY_INSTANCE == null) {
			FACTORY_INSTANCE = new ProductLabelFactory();
		}
		return FACTORY_INSTANCE;
	}

	public String createProductLabel(ProductLabel productlabel,int count, String path) {
		String fileName = "";
		try {
			File tempFile = File.createTempFile("product", ".pdf", new File(path));
			fileName = tempFile.getName();
//			generatePDF(productlabel,count, tempFile.getAbsolutePath(),
//					new Rectangle(Float.parseFloat("1288.5"), Float.parseFloat("1198.45")));
			generatePDF(productlabel,count, tempFile.getAbsolutePath(),
					new Rectangle(Float.parseFloat("288.5"), Float.parseFloat("198.45")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileName;
	}

	private void generatePDF(ProductLabel productLabel ,int count, String pdfPath,Rectangle rect) {
		Document document = new Document(PageSize.A4, 0, 0, 0, 0);
		if (rect != null) {
			document.setPageSize(rect);
		}
		try {
			PdfWriter arg = PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
			document.open();
			document.newPage();
			
			PdfPTable table = new PdfPTable(1); // 1 columns.
            table.setWidthPercentage(100); // Width 100%

			for (int i =0; i < count; i ++) {
				
				Paragraph paragraph = getParagraph(productLabel);
				
				PdfPCell cell1 = new PdfPCell();
				cell1.setBorderColor(new BaseColor(255, 255, 255));
				cell1.setFixedHeight(200);
				
//				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
//	            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell1.addElement(paragraph);
				
				Image twoDBarcode = Image.getInstance("C:\\projects\\shopcenterms\\src\\main\\resources\\img\\2Dcode.jpg");
				twoDBarcode.scaleToFit(60,60);
				twoDBarcode.setAlignment(Image.RIGHT);
				
				cell1.addElement(twoDBarcode);
				
				table.addCell(cell1);
				
			}
			
			document.add(table);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
		}
	}

	private Paragraph getParagraph(ProductLabel productLabel) throws DocumentException, IOException {
		// 拼文字
		
		BaseFont bf = BaseFont.createFont( "C://Windows//Fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font fontHead = new Font(bf);  
		fontHead.setSize(16f);
		fontHead.setStyle("Blod");
		
		Font font = new Font(bf);  
		font.setSize(10f);
		
		
		Paragraph paragraph = new Paragraph();
		Chunk chunk0 = new Chunk(" 产品名称: " +productLabel.getName() +"\n");
		chunk0.setFont(fontHead);
		paragraph.add(chunk0);
		
		Chunk chunk1 = new Chunk(" 产品主成分: " +productLabel.getComposition() +"\n");
		chunk1.setFont(font);
		paragraph.add(chunk1);
		
		Chunk chunk2 = new Chunk(" 生产日期: " +productLabel.getProduceDate() +"\n");
		chunk2.setFont(font);
		paragraph.add(chunk2);
		
		Chunk chunk3 = new Chunk(" 尝鲜期: " +productLabel.getExpiredDate() +"\n");
		chunk3.setFont(font);
		paragraph.add(chunk3);
		
		Chunk chunk4 = new Chunk(" 生产商: " +productLabel.getProduceCompany() +"\n");
		chunk4.setFont(font);
		paragraph.add(chunk4);
		
		Chunk chunk5 = new Chunk(" 食品经营许可证: " +productLabel.getCompanyCode() +"\n\n");
		chunk5.setFont(font);
		paragraph.add(chunk5);
		
//		Chunk chunk6 = new Chunk(" 拎包加盟热线: " +productLabel.getCompanyMobile() +"\n\n");
//		chunk6.setFont(font);
//		paragraph.add(chunk6);
		
		Chunk chunk7 = new Chunk(" 售价 ￥  " +productLabel.getPrice());
		chunk7.setFont(fontHead);
		paragraph.add(chunk7);
		
		return paragraph;
	}



	
}
