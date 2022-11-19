package com.practica.profesionalizante.negocio.service.impl;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.practica.profesionalizante.negocio.dto.MailRequest;
import com.practica.profesionalizante.negocio.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	private String mailNuestro;
	private String password;

	private void sendMail(Session sesion, String para, String asunto, String cuerpo) {
		try {
			MimeMessage msg = new MimeMessage(sesion);
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress(mailNuestro, "No-Reply"));
			msg.setSubject(asunto, "UTF-8");
			msg.setContent(cuerpo, "text/html; charset=utf-8");
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para, false));

			Transport.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enviarMail(MailRequest request) {
		mailNuestro = System.getenv("MAIL_USERNAME");
		password = System.getenv("MAIL_PASSWORD");

		String para = "rodriikc@gmail.com";
		String cuerpo = "Nombre: " + request.getNombre() + " - Correo electrónico: " + request.getEmail() + " - Mensaje: "
				+ request.getMensaje();

		Thread t = new Thread(() -> {
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			Authenticator auth = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(mailNuestro, password);
				}
			};

			Session session = Session.getDefaultInstance(props, auth);

			this.sendMail(session, para, request.getAsunto(), cuerpo);
		});

		t.start();
	}
}
