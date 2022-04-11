package mx.edu.utez.integradora.service;

public interface EmailService {
	boolean sendEmail(String emailTo, String emailSubject, String emailContent);
}
