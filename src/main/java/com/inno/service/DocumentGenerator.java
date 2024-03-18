package com.inno.service;


import java.io.FileOutputStream;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfWriter;


@Service
public class DocumentGenerator {

	private Logger log = LoggerFactory.getLogger(DocumentGenerator.class);

	public String htmlStringToPdf(String content) {
		String encodeBase64String = null;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			PdfWriter pdfwriter = new PdfWriter(byteArrayOutputStream);
			DefaultFontProvider defaultFont = new DefaultFontProvider(false, true, false);
			ConverterProperties converterProperties = new ConverterProperties();
			converterProperties.setFontProvider(defaultFont);
		
			HtmlConverter.convertToPdf(content, pdfwriter, converterProperties);
			FileOutputStream fout = new FileOutputStream("C:\\Users\\acer\\Desktop\\employee_HtmlConvertor.pdf");
			byteArrayOutputStream.writeTo(fout);
			byteArrayOutputStream.close();
			byteArrayOutputStream.flush();
			log.info("file created");
			fout.close();
			encodeBase64String = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
			log.info("Successfully encoded to base64 String");
		} catch (Exception ex) {
			log.error("Exception := "+ex.getMessage());
		}
		return encodeBase64String;
	}
}
