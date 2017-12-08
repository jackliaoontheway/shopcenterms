package com.noone.shopcenterms.biz.service.impl;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

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

		try {
			File tempFile = File.createTempFile("product", ".pdf", new File(path));
			path = tempFile.getAbsolutePath();
			generatePDF(productlabel,count, tempFile.getAbsolutePath(), 1, 1, 16f, 16f, 190f, 8.5f,
					new Rectangle(Float.parseFloat("283.5"), Float.parseFloat("198.45")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	private void generatePDF(ProductLabel productLabel ,int count, String pdfPath, int columns, int rows, float padding, float spacing,
			float width, float fontSize, com.lowagie.text.Rectangle rect) {
		Document document = new Document(PageSize.A4, 0, 0, 0, 0);
		if (rect != null) {
			document.setPageSize(rect);
		}
		try {
			PdfWriter arg = PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
			document.open();
			document.newPage();
			Table table = new Table(columns, rows);
			table.setWidth(width);
			// table.setBorderWidth(Element.ALIGN_CENTER);// 测试用
			table.setBorderColor(new Color(255, 255, 255));
			table.setPadding(padding);
			table.setSpacing(spacing);

			for (int i =0; i < count; i ++) {

				Paragraph nameG = getParagraph(fontSize, productLabel.getName());
				Paragraph compositionG = getParagraph(fontSize, productLabel.getComposition());
				Paragraph produceG = getParagraph(fontSize, productLabel.getProduceDate());
				Paragraph expireG = getParagraph(fontSize, productLabel.getExpiredDate());
				Paragraph produceCompanyG = getParagraph(fontSize, productLabel.getProduceCompany());
				Paragraph companyCodeG = getParagraph(fontSize, productLabel.getCompanyCode());
				Paragraph companyMobileG = getParagraph(fontSize, productLabel.getCompanyMobile());
				Paragraph priceG = getParagraph(fontSize, productLabel.getPrice());
				
				Cell cell = new Cell();
				cell.add(nameG);
				cell.add(compositionG);
				cell.add(produceG);
				cell.add(expireG);
				cell.add(produceCompanyG);
				cell.add(companyCodeG);
				cell.add(companyMobileG);
				cell.add(priceG);
				cell.setBorderColor(new Color(255, 255, 255));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				
				table.addCell(cell);
				
			}
			document.add(table);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
		}
	}

	private Paragraph getParagraph(float fontSize, String num1) {
		// 拼文字
		Font toFont = FontFactory.getFont(FontFactory.COURIER);
		toFont.setSize(fontSize);
		Paragraph bunum = new Paragraph();
		Chunk chunk1 = new Chunk(num1);
		chunk1.setFont(toFont);
		bunum.add(chunk1);
		return bunum;
	}


}
