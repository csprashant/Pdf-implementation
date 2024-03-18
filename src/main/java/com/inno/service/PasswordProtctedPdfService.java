package com.inno.service;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
@Service
public class PasswordProtctedPdfService {
	private Logger log = LoggerFactory.getLogger(PasswordProtctedPdfService.class);

	public String htmlStringToPdf(String content) {
		String encodeBase64String = null;
		Document document = new Document();
		try {
			ByteArrayOutputStream byteArrayOutputStream =  new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);
			writer.setEncryption("12345".getBytes(), "1234".getBytes(), PdfWriter.ALLOW_COPY, PdfWriter.STANDARD_ENCRYPTION_40);
			document.open();
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, new StringReader(content));
			 document.close();
			//for demo only 
			FileOutputStream fout = new FileOutputStream("C:\\Users\\acer\\Desktop\\PasswordProtctedPdfService.pdf");
			byteArrayOutputStream.writeTo(fout);
			byteArrayOutputStream.close();
			byteArrayOutputStream.flush();
			log.info("file created");
			//fout.close();
			encodeBase64String = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
			log.info("Successfully encoded to base64 String");
			System.out.println(byteArrayOutputStream.toByteArray().length);
		} catch (Exception ex) {
			log.error("Exception := "+ex.getMessage());
		}
		System.out.println(countBase64Size(encodeBase64String));
		return encodeBase64String;
	}
	
	int countBase64Size(String in) {
		  int count = 0;
		  int pad = 0;
		  for (int i = 0; i < in.length(); i++) {
		    char c = in.charAt(i);
		    if (c == '=') pad++;
		    if (!Character.isWhitespace(c)) count++;
		  }
		  return (count * 3 / 4) - pad;
		}
}
