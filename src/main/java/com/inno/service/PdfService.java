package com.inno.service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java. util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.pdf.PDFEncryption;

import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfService {
	private Logger log = LoggerFactory.getLogger(PdfService.class);

	public String htmlStringToPdf(String content) {
		String encodeBase64String = null;
		byte[] owner = "1234".getBytes();
		byte[] userPass = "12345".getBytes();
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(content);
			renderer.layout();
			renderer.setPDFEncryption(new PDFEncryption(owner, userPass, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_40));
			renderer.createPDF(byteArrayOutputStream,false);
			renderer.finishPDF();
			encodeBase64String= saveFile(byteArrayOutputStream);
			log.info("Successfully encoded to base64 String");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return encodeBase64String;
	}

	private String saveFile(ByteArrayOutputStream byteArrayOutputStream) throws FileNotFoundException, IOException {
		
		//FileOutputStream fout = new FileOutputStream("C:\\Users\\acer\\Desktop\\New folder\\pdfservice.pdf");
		//byteArrayOutputStream.writeTo(fout);
		
		byteArrayOutputStream.flush();
		byteArrayOutputStream.close();
		log.info("file created");
		//fout.close();
		String encodeBase64String =  Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
		
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
