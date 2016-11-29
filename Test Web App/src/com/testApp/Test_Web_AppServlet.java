package com.testApp;

import java.io.IOException;
import javax.servlet.http.*;

import mailing.MailingService;


@SuppressWarnings("serial")
public class Test_Web_AppServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		MailingService ms = new MailingService();
		ms.sendMail("pandiyan.selvam@a-cti.com", null, null, null, "hi this is test mail");
		
	}
}
