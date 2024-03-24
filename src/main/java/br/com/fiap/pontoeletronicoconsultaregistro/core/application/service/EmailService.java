package br.com.fiap.pontoeletronicoconsultaregistro.core.application.service;

import br.com.fiap.pontoeletronicoconsultaregistro.core.domain.RelatorioPontoEntradaSaida;
import br.com.fiap.pontoeletronicoconsultaregistro.core.domain.RelatorioPontoMes;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${mail.from}")
    private String emailFrom;

    public void sendEmail(String to, String titulo, RelatorioPontoMes relatorioPontoMes) throws MessagingException {
        MimeMessage mensagem = mailSender.createMimeMessage();
        mensagem.setFrom(new InternetAddress(this.emailFrom));
        mensagem.setRecipients(MimeMessage.RecipientType.TO, to);
        mensagem.setSubject(titulo);
        String htmlTemplate = this.readFile("relatorioemailtemplate.html"); //get the template from the file
        String htmlTable = relatorioPontoMes.createHtmlTable();
        htmlTemplate = htmlTemplate.replace("{{tabela}}", htmlTable); //replace the placeholder with the table (htmlTable
        mensagem.setContent(htmlTemplate, "text/html; charset=utf-8");
        mailSender.send(mensagem);
    }

    public String readFile(String templateName) {
        String content = "";
        try {
            Resource resource = resourceLoader.getResource("classpath:templates/"+templateName);
            content = new String(Files.readAllBytes(Paths.get(resource.getURI())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

}