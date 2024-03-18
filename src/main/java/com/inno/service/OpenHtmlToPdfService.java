package com.inno.service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.openhtmltopdf.pdfboxout.PdfBoxRenderer;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
@Service
public class OpenHtmlToPdfService {

	private Logger log = LoggerFactory.getLogger(OpenHtmlToPdfService.class);

	public String htmlStringToPdf(String content) {
		String encodeBase64String = null;
		String ownerPassword = "abc";
		String adminPassword = "12345";
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			PDDocument document = new PDDocument();
			PdfRendererBuilder builder = new PdfRendererBuilder();
			builder.useFastMode();
			builder.withHtmlContent(content, null);
			builder.usePDDocument(document);
			PdfBoxRenderer renderer = builder.buildPdfRenderer();
			renderer.createPDFWithoutClosing();
			document.protect(createSecurityPolicy(ownerPassword,adminPassword));
			document.save(byteArrayOutputStream);
			encodeBase64String = saveFile(byteArrayOutputStream);
			log.info("Successfully encoded to base64 String");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return encodeBase64String;
	}
	
	private StandardProtectionPolicy createSecurityPolicy(String ownerPassword,String adminPassword) {
		AccessPermission ap = new AccessPermission();
		//add what ever perms you need blah blah...
		ap.setCanModify(false);
		ap.setCanExtractContent(false);
		ap.setCanPrint(false);
		ap.setCanPrintDegraded(false);
		ap.setReadOnly();
		return new StandardProtectionPolicy(ownerPassword, adminPassword, ap);
	}

	private String saveFile(ByteArrayOutputStream byteArrayOutputStream) throws FileNotFoundException, IOException {
		// for creating file in physical location 
		FileOutputStream fout = new FileOutputStream("C:\\Users\\acer\\Desktop\\New folder\\openhtmlto.pdf");
		byteArrayOutputStream.writeTo(fout);
	
		byteArrayOutputStream.flush();
		byteArrayOutputStream.close();
		log.info("file created");
		fout.close();
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
