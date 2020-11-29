package yte.intern.project.application.MailService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;

import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class EmailServiceImpl{

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        System.out.println("Sending started");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
        System.out.println("Sending completed");

    }
    public void sendMessageWithAttachmentImage( String to, String subject, byte[] arr) throws Exception{

        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arr);
        BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);

        File file = new File("src/main/resources/static/myimage.png");
        ImageIO.write(bufferedImage, "png", file);

        helper.setFrom("noreply@baeldung.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(
                "<html>"
                        + "<body>"
                        + "<div><h1 style='color:red'>INVITATION</h1></div>"
                        + "<div><p><b>Please use this qr code when entering the event.</b></p></div>"
                        + "<div>"
                        + "<img src='cid:rightSideImage' style='float:left;width:200px;height:200px;'/>"
                        + "</div>"
                        + "</div></body>"
                        + "</html>", true);

        helper.addInline("rightSideImage",file);
        emailSender.send(message);
    }
}