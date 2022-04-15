package mx.edu.utez.integradora.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mx.edu.utez.integradora.service.EmailService;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class EmailServiceImpl implements EmailService{

	@Value("${sendgrid.api.key}")
	private String sendgridApiKey;
	
	@Value("${sendgrid.api.email}")
	private String emailFrom;

	@Override
	public boolean sendEmail(String emailTo, String emailSubject, String emailContent) {
		Email from = new Email(emailFrom);
		
		Email to = new Email(emailTo);
		Content content = new Content("text/html", emailContent); // text/plain or text/html
		Mail mail = new Mail(from, emailSubject, to, content);

		SendGrid sg = new SendGrid(sendgridApiKey);
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);

			return response.getStatusCode() == 202;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
