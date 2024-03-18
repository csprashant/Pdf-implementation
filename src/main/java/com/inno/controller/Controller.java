package com.inno.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inno.config.TemplateService;
import com.inno.dto.Employee;
import com.inno.dto.Message;
import com.inno.service.DocumentGenerator;
import com.inno.service.OpenHtmlToPdfService;
import com.inno.service.PasswordProtctedPdfService;
import com.inno.service.PdfService;
import com.inno.service.XmlWorkerHelperServiceDONTLOOKFORFONTS;

@RestController
public class Controller {

	@Autowired
	private TemplateService templateService;
	@Autowired
	private DocumentGenerator documentGenerator;
	@Autowired
	PasswordProtctedPdfService passwordProtctedPdfService;
	@Autowired
	private PdfService service;
	@Autowired
	private XmlWorkerHelperServiceDONTLOOKFORFONTS xmlWorker;
	@Autowired private OpenHtmlToPdfService openHtmlToPdfService;
	private Logger log = LoggerFactory.getLogger(Controller.class);

	@GetMapping("string")
	public String generatePDFFromHtmlContentAndGetBase64EncodedString() {
		log.info("Request Received generatePDFFromHtmlContentAndGetBase64EncodedString");
		Map<String, Object> context = new HashMap<>();
		context.put("empsList", getDetails());
		String content = templateService.generateTemplate("emp", context);
		System.out.println(content);
		// return documentGenerator.htmlStringToPdf(content);
		return service.htmlStringToPdf(content);
	}

	@GetMapping("password")
	public String generateHtmlContentAndGetBase64EncodedStringPasswordProtected() {
		log.info("Request Received generateHtmlContentAndGetBase64EncodedStringPasswordProtected");
		Map<String, Object> context = new HashMap<>();
		context.put("empsList", getDetails());
		String content = templateService.generateTemplate("emp", context);
		System.out.println(content);
		return passwordProtctedPdfService.htmlStringToPdf(content);
	}

	@GetMapping("t")
	public String t() {
		log.info("Request Received t");
		Map<String, Object> context = new HashMap<>();
		context.put("msgList", getMessages());
		String content = templateService.generateTemplate("pdf_chat_transcript", context);
		System.out.println(content);
		return documentGenerator.htmlStringToPdf(content);
	}

	@GetMapping("ptxml")
	public String xmlWorker() {
		log.info("Request Received passwordtrasncrpt");
		Map<String, Object> context = new HashMap<>();
		context.put("msgList", getMessages());
		String content = templateService.generateTemplate("pdf_chat_transcript", context);
		return xmlWorker.htmlStringToPdf(content);
	}

	@GetMapping("pt")
	public String trasncrpt() {
		log.info("Request Received passwordtrasncrpt");
		Map<String, Object> context = new HashMap<>();
		context.put("msgList", getMessages());
		String content = templateService.generateTemplate("pdf_chat_transcript", context);
		return passwordProtctedPdfService.htmlStringToPdf(content);
	}

	@GetMapping("pdf")
	public String pdf() {
		log.info("Request Received pdf");
		Map<String, Object> context = new HashMap<>();
		context.put("msgList", getMessages());
		String content = templateService.generateTemplate("pdf_chat_transcript", context);
		// System.out.println(content);
		return service.htmlStringToPdf(content);
	}

	@GetMapping("test")
	public String test() {
		log.info("Request Received passwordtrasncrpt");
		Map<String, Object> context = new HashMap<>();
		context.put("customerName", "prashnat");
		String content = templateService.generateTemplate("demo", context);
		System.out.println(content);
		// return passwordProtctedPdfService.htmlStringToPdf(content);
		return service.htmlStringToPdf(content);
	}
	
	@GetMapping("open")
	public String openpdf() {
		log.info("Request Received pdf");
		Map<String, Object> context = new HashMap<>();
		context.put("msgList", getMessages());
		String content = templateService.generateTemplate("pdf_chat_transcript", context);
		// System.out.println(content);
		return openHtmlToPdfService.htmlStringToPdf(content);
	}

	public List<Employee> getDetails() {
		List<Employee> list = new ArrayList<Employee>();
		list.add(new Employee(101, "Raj Name to suna hi hoga", 345450.00));
		list.add(new Employee(102, "Rajesh", 36000.00));
		list.add(new Employee(103, "Raja", 56000.00));
		list.add(new Employee(104, "Raju", 26000.00));
		list.add(new Employee(105, "Rajat", 96000.00));
		list.add(new Employee(107, "Golu", 76000.00));
		list.add(new Employee(108, "Rajiv", 16000.00));
		list.add(new Employee(109, "Sonu", 46000.00));
		list.add(new Employee(110, "Tanu", 56000.00));
		list.add(new Employee(101, "Raj Name to suna hi hoga", 345450.00));
		list.add(new Employee(102, "Rajesh", 36000.00));
		list.add(new Employee(103, "Raja", 56000.00));
		list.add(new Employee(104, "Raju", 26000.00));
		list.add(new Employee(105, "Rajat", 96000.00));
		list.add(new Employee(107, "Golu", 76000.00));
		list.add(new Employee(108, "Rajiv", 16000.00));
		list.add(new Employee(109, "Sonu", 46000.00));
		list.add(new Employee(110, "Tanu", 56000.00));
		list.add(new Employee(101, "Raj Name to suna hi hoga", 345450.00));
		list.add(new Employee(102, "Rajesh", 36000.00));
		list.add(new Employee(103, "Raja", 56000.00));
		list.add(new Employee(104, "Raju", 26000.00));
		list.add(new Employee(105, "Rajat", 96000.00));
		list.add(new Employee(107, "Golu", 76000.00));
		list.add(new Employee(108, "Rajiv", 16000.00));
		list.add(new Employee(109, "Sonu", 46000.00));
		list.add(new Employee(110, "Tanu", 56000.00));
		list.add(new Employee(101, "Raj Name to suna hi hoga", 345450.00));
		list.add(new Employee(102, "Rajesh", 36000.00));
		list.add(new Employee(103, "Raja", 56000.00));
		list.add(new Employee(104, "Raju", 26000.00));
		list.add(new Employee(105, "Rajat", 96000.00));
		list.add(new Employee(107, "Golu", 76000.00));
		list.add(new Employee(108, "Rajiv", 16000.00));
		list.add(new Employee(109, "Sonu", 46000.00));
		list.add(new Employee(110, "Tanu", 56000.00));
		list.add(new Employee(101, "Raj Name to suna hi hoga", 345450.00));
		list.add(new Employee(102, "Rajesh", 36000.00));
		list.add(new Employee(103, "Raja", 56000.00));
		list.add(new Employee(104, "Raju", 26000.00));
		list.add(new Employee(105, "Rajat", 96000.00));
		list.add(new Employee(107, "Golu", 76000.00));
		list.add(new Employee(108, "Rajiv", 16000.00));
		list.add(new Employee(109, "Sonu", 46000.00));
		list.add(new Employee(110, "Tanu", 56000.00));
		list.add(new Employee(101, "Raj Name to suna hi hoga", 345450.00));
		list.add(new Employee(102, "Rajesh", 36000.00));
		list.add(new Employee(103, "Raja", 56000.00));
		list.add(new Employee(104, "Raju", 26000.00));
		list.add(new Employee(105, "Rajat", 96000.00));
		list.add(new Employee(107, "Golu", 76000.00));
		list.add(new Employee(108, "Rajiv", 16000.00));
		list.add(new Employee(109, "Sonu", 46000.00));
		list.add(new Employee(110, "Tanu", 56000.00));
		list.add(new Employee(101, "Raj Name to suna hi hoga", 345450.00));
		list.add(new Employee(102, "Rajesh", 36000.00));
		list.add(new Employee(103, "Raja", 56000.00));
		list.add(new Employee(104, "Raju", 26000.00));
		list.add(new Employee(105, "Rajat", 96000.00));
		list.add(new Employee(107, "Golu", 76000.00));
		list.add(new Employee(108, "Rajiv", 16000.00));
		list.add(new Employee(109, "Sonu", 46000.00));
		list.add(new Employee(110, "Tanu", 56000.00));
		list.add(new Employee(101, "Raj Name to suna hi hoga", 345450.00));
		list.add(new Employee(102, "Rajesh", 36000.00));
		list.add(new Employee(103, "Raja", 56000.00));
		list.add(new Employee(104, "Raju", 26000.00));
		list.add(new Employee(105, "Rajat", 96000.00));
		list.add(new Employee(107, "Golu", 76000.00));
		list.add(new Employee(108, "Rajiv", 16000.00));
		list.add(new Employee(109, "Sonu", 46000.00));
		list.add(new Employee(110, "Tanu", 56000.00));
		list.add(new Employee(101, "Raj Name to suna hi hoga", 345450.00));
		list.add(new Employee(102, "Rajesh", 36000.00));
		list.add(new Employee(103, "Raja", 56000.00));
		list.add(new Employee(104, "Raju", 26000.00));
		list.add(new Employee(105, "Rajat", 96000.00));
		list.add(new Employee(107, "Golu", 76000.00));
		list.add(new Employee(108, "Rajiv", 16000.00));
		list.add(new Employee(109, "Sonu", 46000.00));
		list.add(new Employee(110, "Tanu", 56000.00));
		list.add(new Employee(101, "Raj Name to suna hi hoga", 345450.00));
		list.add(new Employee(102, "Rajesh", 36000.00));
		list.add(new Employee(103, "Raja", 56000.00));
		list.add(new Employee(104, "Raju", 26000.00));
		list.add(new Employee(105, "Rajat", 96000.00));
		list.add(new Employee(107, "Golu", 76000.00));
		list.add(new Employee(108, "Rajiv", 16000.00));
		list.add(new Employee(109, "Sonu", 46000.00));
		list.add(new Employee(110, "Tanu", 56000.00));
		return list;
	}

	public List<Message> getMessages() {
		List<Message> list = new ArrayList<Message>();
		for (int i = 0; i < 50; i++) {
			list.add(new Message("Agent -Raj", 1, new Date(),
					"<html><body><h1>This is a test</h1><p><b>This text is bold</b></p><p><i>This text is italic</i></p><p>This content is in html tag to render the html styled messages</p> <img src=\"https://pics.sbicard.com/SBICardsCEJ8/images/title-image.png\" height=\"20px\" width=\"100px\"/></body></html> "));
			list.add(new Message("Prashant", 2, new Date(), "Customer meessage1"));
			list.add(new Message("Agent -Raj", 1, new Date(), "Agent message2"));
			list.add(new Message("Prashant", 2, new Date(),
					"Customer meessage2 Customer meessage2 Customer meessage2 Customer meessage2 Customer meessage2 Customer meessage2 Customer meessage2 Customer meessage2"));
			list.add(new Message("Agent -Raj", 1, new Date(), "Agent message3"));
			list.add(new Message("Prashant", 2, new Date(), "Customer meessage3"));
			list.add(new Message("Agent -Raj", 1, new Date(), "Agent message4"));
			list.add(new Message("Prashant", 2, new Date(), "Customer meessage4"));
			list.add(new Message("Agent -Raj", 1, new Date(),
					"Agent message long message for testing Agent message long message for testing Agent message long message for testing Agent message long message for testing5"));
			list.add(new Message("Prashant", 2, new Date(), ""));

		}
		return list;
	}
}
